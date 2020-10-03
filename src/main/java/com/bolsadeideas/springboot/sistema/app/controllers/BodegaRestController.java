package com.bolsadeideas.springboot.sistema.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.sistema.app.entity.Bodega;
import com.bolsadeideas.springboot.sistema.app.services.IBodegaService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class BodegaRestController {

    @Autowired
    private IBodegaService bodegaService;

    // private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
    @GetMapping("/bodegas")
    public List<Bodega> index() {
        return bodegaService.findAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/bodegas/page/{page}")
    public Page<Bodega> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return bodegaService.findAll(pageable);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/bodegas/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Bodega bodega = null;
        Map<String, Object> response = new HashMap<>();

        try {
            bodega = bodegaService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (bodega == null) {
            response.put("mensaje", "La Bodega ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Bodega>(bodega, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/bodegas")
    public ResponseEntity<?> create(@Valid @RequestBody Bodega bodega, BindingResult result) {

        Bodega bodegaNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            bodegaNew = bodegaService.save(bodega);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("dato", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La Sucursal ha sido creado con éxito!");
        response.put("bodega", bodegaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/bodegas/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Bodega bodega, BindingResult result, @PathVariable Long id) {

        Bodega bodegaActual = bodegaService.findById(id);

        Bodega bodegaUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (bodegaActual == null) {
            response.put("mensaje", "Error: no se pudo editar, La Sucursal ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            
            bodegaActual.setNombre(bodega.getNombre());
            bodegaActual.setCantidad(bodega.getCantidad());
            bodegaActual.setSucursal(bodega.getSucursal());
            bodegaActual.setPrecioVenta(bodega.getPrecioVenta());
            bodegaActual.setPrecioCompra(bodega.getPrecioCompra());
            bodegaActual.setCreateAt(bodega.getCreateAt());
            bodegaActual.setIdCompuesto(bodega.getIdCompuesto());
            bodegaActual.setFechaActualizacion(bodega.getFechaActualizacion());

            bodegaUpdated = bodegaService.save(bodegaActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la Bodega en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("dato", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La Sucursal ha sido actualizado con éxito!");
        response.put("bodega", bodegaUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/bodegas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            bodegaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la sucursal de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Sucursal eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
