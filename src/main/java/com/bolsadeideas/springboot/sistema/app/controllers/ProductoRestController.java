package com.bolsadeideas.springboot.sistema.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.sistema.app.entity.Categoria;
import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.services.IProductoService;
import com.bolsadeideas.springboot.sistema.app.services.IUploadFileService;



@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/productos")
	public List<Producto> index() {
		return productoService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/productos/page/{page}")
	public Page<Producto> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return productoService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			producto = productoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			response.put("mensaje", "El Producto con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/productos")
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {
		
		Producto productoNew = null;
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
			productoNew = productoService.save(producto);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al Crear producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                        response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Producto ha sido creado con éxito!");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {

		Producto productoActual = productoService.findById(id);

		Producto productoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (productoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			productoActual.setCodigo(producto.getCodigo());
			productoActual.setNombre(producto.getNombre());
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setPrecioCompra(producto.getPrecioCompra());
			productoActual.setCantidad(producto.getCantidad());
			productoActual.setCreateAt(new Date());

			productoUpdated = productoService.save(productoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Producto ha sido actualizado con éxito!");
		response.put("producto", productoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Producto producto = productoService.findById(id);
			String nombreFotoAnterior = producto.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior, "uploadsProductos");
			
			productoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Producto eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/productos/uploadimgproducto")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Producto producto = productoService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo, "uploadsProductos");
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del Producto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = producto.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior, "uploadsProductos");
						
			producto.setFoto(nombreArchivo);
			
			productoService.save(producto);
			
			response.put("producto", producto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/uploads/imgproducto/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto,  "uploadsProductos");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	
	@GetMapping("/productos/filtrar-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> findByNombre(@PathVariable String term) {
		System.out.println("llegando " + term);
		return productoService.findByNombre(term);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/productos/categorias")
	public List<Categoria> listarCategorias(){
		return productoService.findAllCategorias();
	}
}
