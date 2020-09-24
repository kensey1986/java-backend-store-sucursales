package com.bolsadeideas.springboot.sistema.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.bolsadeideas.springboot.sistema.app.entity.Factura;
import com.bolsadeideas.springboot.sistema.app.services.IFacturaService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {

    @Autowired
    private IFacturaService facturaService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/facturas")
    public List<Factura> listaCompleta() {
        return facturaService.findAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
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
    public List<Factura> findByCreateAtBetween(@PathVariable  String f1, @PathVariable  String f2) {
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = null;
        Date fecha2 = null;
        try {
            fecha1 = formato.parse(f1);
            fecha2 = formato.parse(f2);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        return facturaService.findByCreateAtBetween(fecha1, fecha2);
    }

    @GetMapping("/facturas/ultimafactura")
    @ResponseStatus(HttpStatus.OK)
    public List<Factura> findFirstByOrderByIdDesc() {
        return facturaService.findFirstByOrderByIdDesc();
    }

}
