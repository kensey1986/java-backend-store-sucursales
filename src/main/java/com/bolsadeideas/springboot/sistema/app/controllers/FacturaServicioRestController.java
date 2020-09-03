package com.bolsadeideas.springboot.sistema.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.sistema.app.entity.Cliente;
import com.bolsadeideas.springboot.sistema.app.entity.FacturaServicio;
import com.bolsadeideas.springboot.sistema.app.services.IFacturaServicioService;



@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class FacturaServicioRestController {

	@Autowired
	private IFacturaServicioService facturaServicioService;
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/facturas_servicios")
	public List<FacturaServicio> listaCompleta() {
		return facturaServicioService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/facturas_servicios/page/{page}")
	public Page<FacturaServicio> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return facturaServicioService.findAll(pageable);
	}
	

	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/facturas_servicios/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FacturaServicio show(@PathVariable Long id) {
		return facturaServicioService.findById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas_servicios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		facturaServicioService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/facturas_servicios")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaServicio crear(@RequestBody FacturaServicio facturaServicio) {
		return facturaServicioService.save(facturaServicio);
	}
	
	
	
	@GetMapping("/facturas_servicios/fecha1/{f1}/fecha2/{f2}")
	@ResponseStatus(HttpStatus.OK)
	public List<FacturaServicio> buscarPorRangosFecha(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f1, @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f2) {
		System.out.printf("fecha1 :"+f1 +"fecha 2 :"+f2 );
		return facturaServicioService.buscarPorRangosFecha(f1, f2);
	}
	
	@GetMapping("/facturas_servicios/fecha/{f1}")
	@ResponseStatus(HttpStatus.OK)
	public List<FacturaServicio> buscarPoFecha(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f1) {
		System.out.printf("fecha  :"+f1  );
		System.out.printf("-------------- :" );
		return facturaServicioService.buscarPorFecha(f1);
	}
	
	@GetMapping("/facturas_servicios/fechadescripcion/{f1}")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> findByDescripcion(@PathVariable String fecha) {
		return facturaServicioService.findByDescripcion(fecha);
	}
	
	


}
