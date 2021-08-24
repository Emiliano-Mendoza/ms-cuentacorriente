package com.dan.cuentacorriente.rest;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.cuentacorriente.domain.Cliente;
import com.dan.cuentacorriente.domain.Efectivo;
import com.dan.cuentacorriente.domain.MedioPago;
import com.dan.cuentacorriente.domain.Pago;
import com.dan.cuentacorriente.service.ClienteService;
import com.dan.cuentacorriente.service.MedioPagoService;
import com.dan.cuentacorriente.service.PagoService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pago")
@Api(value = "PagoRest")
public class PagoRest {
	
    @Autowired
	private PagoService pagoServ;
    @Autowired
	private ClienteService clienteServ;
    @Autowired
	private MedioPagoService medioServ;
	
    @GetMapping(path = "/id/{id}")
    @ApiOperation(value = "Busca un pago por id")
    public ResponseEntity<Optional<Pago>> PagoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(pagoServ.findById(id));
    }
    
    @GetMapping(path = "/cliente/{id}")
    @ApiOperation(value = "Busca un pago por id de cliente")
    public ResponseEntity<List<Pago>> PagosDeCliente(@PathVariable Integer id){
    	List<Pago> listaPagos = pagoServ.getAllPagos();
    	List<Pago> aux = new ArrayList<>();
    	for(int i=0;i<listaPagos.size();i++) {
    		if(listaPagos.get(i).getCliente().getId()==id)aux.add(listaPagos.get(i));
    	}
        return ResponseEntity.ok(aux);
    }
    
    @GetMapping
    public ResponseEntity<List<Pago>> todos(){
        return ResponseEntity.ok(pagoServ.getAllPagos());
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pago nuevo){
    	System.out.println(" crear pago "+ nuevo);
    	
    	MedioPago medio;
    	
    	try {
    		medio = medioServ.createMedioPago(nuevo.getMedio());
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	nuevo.setMedio(medio);
    	nuevo.setFechaPago(Instant.now());
    	
    	try {
    		nuevo.setCliente(clienteServ.findById(nuevo.getCliente().getId()).get());  		
    	}catch(Exception e) {
    		return ResponseEntity.badRequest().body(("Cliente inexistente"));
    	}   	
        
    	
        try {
        	Pago temp = pagoServ.createPago(nuevo);
        	return ResponseEntity.created(new URI("/api/pago" + temp.getId())).body(temp);
        	
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }
    
    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<Pago> borrar(@PathVariable Integer id){
        pagoServ.deletePagoPorId(id);
        return ResponseEntity.ok().build();
       
    }
    
    
//    @PutMapping(path = "/id/{id}")
//    @ApiOperation(value = "Actualiza un pago")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Actualizado correctamente"),
//        @ApiResponse(code = 401, message = "No autorizado"),
//        @ApiResponse(code = 403, message = "Prohibido"),
//        @ApiResponse(code = 404, message = "El ID no existe")
//    })
//    public ResponseEntity<Pago> actualizar(@RequestBody Pago nuevo,  @PathVariable Integer id){
//        OptionalInt indexOpt =   IntStream.range(0, listaPagos.size())
//        .filter(i -> listaPagos.get(i).getId().equals(id))
//        .findFirst();
//
//        if(indexOpt.isPresent()){
//            listaPagos.set(indexOpt.getAsInt(), nuevo);
//            return ResponseEntity.ok(nuevo);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
  
}