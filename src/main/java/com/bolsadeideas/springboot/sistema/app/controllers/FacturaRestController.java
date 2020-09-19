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
import com.bolsadeideas.springboot.sistema.app.entity.Factura;
import com.bolsadeideas.springboot.sistema.app.services.IFacturaService;



@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	private IFacturaService facturaService;
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/facturas")
	public List<Factura> listaCompleta() {
		return facturaService.findAll();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/facturas/page/{page}")
	public Page<Factura> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return facturaService.findAll(pageable);
	}
	

	
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return facturaService.findById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		facturaService.delete(id);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		return facturaService.save(factura);
	}
	
	
	
	@GetMapping("/facturas/fecha1/{f1}/fecha2/{f2}")
	@ResponseStatus(HttpStatus.OK)
	public List<Factura> buscarPorRangosFecha(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f1, @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f2) {
		return facturaService.buscarPorRangosFecha(f1, f2);
	}
	
	@GetMapping("/facturas/fecha/{f1}")
	@ResponseStatus(HttpStatus.OK)
	public List<Factura> buscarPoFecha(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date f1) {
		System.out.printf("fecha  :"+f1  );
		System.out.printf("-------------- :" );
		return facturaService.buscarPorFecha(f1);
	}
	
	@GetMapping("/facturas/fechadescripcion/{f1}")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> findByDescripcion(@PathVariable String fecha) {
		return facturaService.findByDescripcion(fecha);
	}
	
	@GetMapping("/facturas/ultimafactura")
	@ResponseStatus(HttpStatus.OK)
	public List<Factura> findFirstByOrderByIdDesc(){
		return facturaService.findFirstByOrderByIdDesc();
	}
	
}
