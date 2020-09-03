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

import com.bolsadeideas.springboot.sistema.app.entity.Sucursal;

import com.bolsadeideas.springboot.sistema.app.services.ISucursalService;



@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class SucursalRestController {

	@Autowired
	private ISucursalService sucursalService;
	
	
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	
	@GetMapping("/sucursales")
	public List<Sucursal> index() {
		return sucursalService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/sucursales/page/{page}")
	public Page<Sucursal> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return sucursalService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/sucursales/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Sucursal sucursal = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			sucursal = sucursalService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(sucursal == null) {
			response.put("mensaje", "La Sucursal ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Sucursal>(sucursal, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/sucursales")
	public ResponseEntity<?> create(@Valid @RequestBody Sucursal sucursal, BindingResult result) {
		
		Sucursal sucursalNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			sucursalNew = sucursalService.save(sucursal);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Sucursal ha sido creado con éxito!");
		response.put("sucursal", sucursalNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/sucursales/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Sucursal sucursal, BindingResult result, @PathVariable Long id) {

		Sucursal sucursalActual = sucursalService.findById(id);

		Sucursal sucursalUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (sucursalActual == null) {
			response.put("mensaje", "Error: no se pudo editar, La Sucursal ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			sucursalActual.setNombre(sucursal.getNombre());
			sucursalActual.setCelular1(sucursal.getCelular1());
			sucursalActual.setCelular2(sucursal.getCelular2());
			sucursalActual.setDireccion(sucursal.getDireccion());
			sucursalActual.setFacebook(sucursal.getFacebook());
			sucursalActual.setGeoposicion(sucursal.getGeoposicion());
			sucursalActual.setInstagram(sucursal.getInstagram());
			sucursalActual.setNit(sucursal.getNit());
			sucursalActual.setSede(sucursal.getSede());
			sucursalActual.setPropietario(sucursal.getPropietario());
			sucursalActual.setRegimen(sucursal.getRegimen());
			sucursalActual.setTelefono(sucursal.getTelefono());
			
			
			sucursalUpdated = sucursalService.save(sucursalActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Sucursal en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Sucursal ha sido actualizado con éxito!");
		response.put("sucursal", sucursalUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@DeleteMapping("/sucursales/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			sucursalService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la sucursal de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Sucursal eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
}
