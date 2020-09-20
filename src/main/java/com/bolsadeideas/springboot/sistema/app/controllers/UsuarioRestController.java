package com.bolsadeideas.springboot.sistema.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.bolsadeideas.springboot.sistema.app.entity.Role;
import com.bolsadeideas.springboot.sistema.app.entity.Usuario;

import com.bolsadeideas.springboot.sistema.app.services.IUploadFileService;
import com.bolsadeideas.springboot.sistema.app.services.IUsuarioService;



@CrossOrigin(origins = {  "*" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/users")
	public List<Usuario> index() {
		return usuarioService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/users/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 30);
		return usuarioService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/users/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null) {
			response.put("mensaje", "El Usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/users")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
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
			usuarioNew = usuarioService.save(usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Usuario ha sido creado con éxito!");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		Usuario usuarioActual = usuarioService.findById(id);

		Usuario usuarioUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuarioActual.setDocumento(usuario.getDocumento());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setUsername(usuario.getUsername());
                        String passDb = usuarioActual.getPassword();
                        String passFt = usuario.getPassword();
			if(!passDb.equals(passFt)) {
				String passwordBcrypt = passwordEncoder.encode(usuario.getPassword());
				usuarioActual.setPassword(passwordBcrypt);
			}else{
                                usuarioActual.setPassword(usuarioActual.getPassword());
                        }
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setRegion(usuario.getRegion());
			// usuarioActual.setSucursal(usuario.getSucursal());
			usuarioActual.setFecha(usuario.getFecha());
			usuarioActual.setDireccion(usuario.getDireccion());
			usuarioActual.setTelefono(usuario.getTelefono());
			usuarioActual.setCelular1(usuario.getCelular1());
			usuarioActual.setCelular2(usuario.getCelular2());
			usuarioActual.setEnabled(usuario.getEnabled());

			usuarioUpdated = usuarioService.save(usuarioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el USUARIO en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("dato", e.getMostSpecificCause().getMessage());
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Usuario ha sido actualizado con éxito!");
		response.put("user", usuarioUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Usuario usuario = usuarioService.findById(id);
			String nombreFotoAnterior = usuario.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior, "uploadsUsuarios");
			
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el Usuario de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Usuario eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/users/uploadimguser")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Usuario usuario = usuarioService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo, "uploadsUsuarios");
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del Usuario");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = usuario.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior, "uploadsUsuarios");
						
			usuario.setFoto(nombreArchivo);
			
			usuarioService.save(usuario);
			
			response.put("user", usuario);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		} 
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/uploads/imguser/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto,  "uploadsUsuarios");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
		
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/users/roles")
	public List<Role> listarRoles(){
		return usuarioService.findAllRoles();
	}
	
}
