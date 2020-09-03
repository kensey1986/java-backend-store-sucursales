package com.bolsadeideas.springboot.sistema.app.controllers;

import java.util.Date;
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

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.bolsadeideas.springboot.sistema.app.entity.Categoria;
import com.bolsadeideas.springboot.sistema.app.entity.Servicio;
import com.bolsadeideas.springboot.sistema.app.services.IServicioService;




@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ServicioRestController {

	@Autowired
	private IServicioService servicioService;
	
	
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/servicios")
	public List<Servicio> index() {
		return servicioService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/servicios/page/{page}")
	public Page<Servicio> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return servicioService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/servicios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Servicio servicio = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			servicio = servicioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(servicio == null) {
			response.put("mensaje", "El Servicio con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/servicios")
	public ResponseEntity<?> create(@Valid @RequestBody Servicio servicio, BindingResult result) {
		
		Servicio servicioNew = null;
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
			servicioNew = servicioService.save(servicio);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Producto ha sido creado con éxito!");
		response.put("servicio", servicioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/servicios/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Servicio servicio, BindingResult result, @PathVariable Long id) {

		Servicio servicioActual = servicioService.findById(id);

		Servicio servicioUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (servicioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
		try {
			;
			servicioActual.setNombre(servicio.getNombre());
			servicioActual.setDescripcion(servicio.getDescripcion());
			servicioActual.setCostoRepuesto(servicio.getCostoRepuesto());
			servicioActual.setCostoServicio(servicio.getCostoServicio());
			servicioActual.setCreateAt(new Date());

			servicioUpdated = servicioService.save(servicioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Servicio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Servicio ha sido actualizado con éxito!");
		response.put("servicio", servicioUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@DeleteMapping("/servicios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
						
			servicioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Servicio eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/servicios/filtrar-servicios/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Servicio> findByNombre(@PathVariable String term) {
		
		return servicioService.findByNombre(term);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/servicios/categorias")
	public List<Categoria> listarCategorias(){
		return servicioService.findAllCategorias();
	}
}
