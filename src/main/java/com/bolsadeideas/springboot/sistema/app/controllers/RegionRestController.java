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

import com.bolsadeideas.springboot.sistema.app.entity.Region;
import com.bolsadeideas.springboot.sistema.app.services.IRegionService;



@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class RegionRestController {

	@Autowired
	private IRegionService regionService;
	
	
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/regiones")
	public List<Region> index() {
		return regionService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/regiones/page/{page}")
	public Page<Region> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return regionService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/regiones/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Region region = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			region = regionService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(region == null) {
			response.put("mensaje", "El Barrio ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Region>(region, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/regiones")
	public ResponseEntity<?> create(@Valid @RequestBody Region region, BindingResult result) {
		
		Region regionNew = null;
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
			regionNew = regionService.save(region);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Barrio ha sido creado con éxito!");
		response.put("region", regionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/regiones/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Region region, BindingResult result, @PathVariable Long id) {

		Region regionActual = regionService.findById(id);

		Region regionUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (regionActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Barrio ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			regionActual.setNombre(region.getNombre());
			
			regionUpdated = regionService.save(regionActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Barrio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Barrio ha sido actualizado con éxito!");
		response.put("region", regionUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@DeleteMapping("/regiones/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			regionService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Barrio de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "BARRIO eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
}
