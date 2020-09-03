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


import com.bolsadeideas.springboot.sistema.app.entity.Categoria;

import com.bolsadeideas.springboot.sistema.app.services.ICategoriaService;





@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class CategoriaRestController {

	@Autowired
	private ICategoriaService categoriaService;
	
	
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/categorias")
	public List<Categoria> index() {
		return categoriaService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/categorias/page/{page}")
	public Page<Categoria> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return categoriaService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/categorias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Categoria categoria = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			categoria = categoriaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(categoria == null) {
			response.put("mensaje", "La Categoria con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/categorias")
	public ResponseEntity<?> create(@Valid @RequestBody Categoria categoria, BindingResult result) {
		
		Categoria categoriaNew = null;
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
			categoriaNew = categoriaService.save(categoria);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La Categoria ha sido creado con éxito!");
		response.put("categoria", categoriaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/categorias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Categoria categoria, BindingResult result, @PathVariable Long id) {

		Categoria categoriaActual = categoriaService.findById(id);

		Categoria categoriaUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (categoriaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la Categoria con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			;
			categoriaActual.setNombre(categoria.getNombre());
			categoriaActual.setDescripcion(categoria.getDescripcion());
			categoriaActual.setCodigo(categoria.getCodigo());
			
			

			categoriaUpdated = categoriaService.save(categoriaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la Categoria en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Categoria ha sido actualizado con éxito!");
		response.put("categoria", categoriaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
	
			categoriaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la Categoria de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "la Categoria eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	/*
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/categorias/productos/{id}")
	public List<Producto> listarCategorias(@PathVariable Long id){
		return categoriaService.findAllProductos(id);
	}*/
	
	
}
