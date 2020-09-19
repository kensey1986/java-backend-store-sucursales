package com.bolsadeideas.springboot.sistema.app.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.bolsadeideas.springboot.sistema.app.entity.Producto;
import com.bolsadeideas.springboot.sistema.app.entity.Reporte;

import com.bolsadeideas.springboot.sistema.app.services.IReporteService;



@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ReporteRestController {

	@Autowired
	private IReporteService reporteService;
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/reportes")
	public List<Reporte> listaCompleta() {
		return reporteService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/reportes/page/{page}")
	public Page<Reporte> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return reporteService.findAll(pageable);
	}
	

	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/reportes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Reporte show(@PathVariable Long id) {
		return reporteService.findById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/reportes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		reporteService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/reportes")
	@ResponseStatus(HttpStatus.CREATED)
	public Reporte crear(@RequestBody Reporte reporte) {
		return reporteService.save(reporte);
	}
	
	@GetMapping("/reportes/fecha1/{f1}/fecha2/{f2}")
	@ResponseStatus(HttpStatus.OK)
	public List<Reporte> buscarPorRangosFecha(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f1, @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f2) {
		return reporteService.buscarPorRangosFecha(f1, f2);
	}
	
	@GetMapping("/reportes/fecha/{f1}")
	@ResponseStatus(HttpStatus.OK)
	public List<Reporte> buscarPoFecha(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f1) {
		System.out.printf("fecha  :"+f1  );
		System.out.printf("-------------- :" );
		return reporteService.buscarPorFecha(f1);
	}
	
	@GetMapping("/reportes/fechareporte/{f1}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> findByNombre(@PathVariable String fecha) {
		return reporteService.findByNombre(fecha);
	}
	
	@GetMapping("/reportes/ultimaReporte")
	@ResponseStatus(HttpStatus.OK)
	public List<Reporte> findFirstByOrderByIdDesc(){
		return reporteService.findFirstByOrderByIdDesc();
	}
	
    @Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping("/reportes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Reporte reporte, BindingResult result, @PathVariable Long id) {

		Reporte reporteActual = reporteService.findById(id);

		Reporte reporteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (reporteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Reporte ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			reporteActual.setNombre(reporte.getNombre());
			reporteActual.setCantidad(reporte.getCantidad());
			reporteActual.setDescripcion(reporte.getDescripcion());
			reporteActual.setCreateAt(reporte.getCreateAt());
			reporteActual.setFechaModificado(reporte.getFechaModificado());
			
			reporteUpdated = reporteService.save(reporteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el reporte en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El reporte ha sido actualizado con éxito!");
		response.put("reporte", reporteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
