package com.dan.cuentacorriente.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.cuentacorriente.domain.Cheque;
import com.dan.cuentacorriente.domain.Efectivo;
import com.dan.cuentacorriente.domain.MedioPago;
import com.dan.cuentacorriente.domain.Transferencia;
import com.dan.cuentacorriente.service.MedioPagoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/medioPago")
@Api(value = "MedioPagoRest")
public class MedioPagoRest {
	
	@Autowired
	private MedioPagoService medioServ;
	
	@GetMapping(path = "/id/{id}")
    @ApiOperation(value = "Busca un pago por id")
    public ResponseEntity<Optional<MedioPago>> MedioPagoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(medioServ.findById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<MedioPago>> todos(){
        return ResponseEntity.ok(medioServ.getAllMedioPagos());
    }
    
//    @PostMapping
//    public ResponseEntity<MedioPago> crear(@RequestBody MedioPago nuevo){
//    	System.out.println(" crear empleado "+ nuevo);
//        
//    	MedioPago temp = medioServ.createMedioPago(nuevo);
//        
//        try {
//        	return ResponseEntity.created(new URI("/api/medioPago" + temp.getId())).body(temp);
//        	
//        }catch(Exception e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        
//    }
    
    @PostMapping(path = "/efectivo")
    public ResponseEntity<MedioPago> crear(@RequestBody Efectivo nuevo){
    	System.out.println(" crear efectivo "+ nuevo);
        
    	if(nuevo.getNroRecibo()!=null) {
    		MedioPago temp = medioServ.createMedioPago(nuevo);
            try {
            	return ResponseEntity.created(new URI("/api/medioPago" + temp.getId())).body(temp);
            	
            }catch(Exception e) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } 	
    	}
    	else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
       
    }
    @PostMapping(path = "/cheque")
    public ResponseEntity<MedioPago> crear(@RequestBody Cheque nuevo){
    	System.out.println(" crear cheque "+ nuevo);
        
    	if(nuevo.getBanco()!=null && nuevo.getNroCheque()!=null) {
    		MedioPago temp = medioServ.createMedioPago(nuevo);
            
            try {
            	return ResponseEntity.created(new URI("/api/medioPago" + temp.getId())).body(temp);
            	
            }catch(Exception e) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
    	}
    	else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	 
    }
    @PostMapping(path = "/transferencia")
    public ResponseEntity<MedioPago> crear(@RequestBody Transferencia nuevo){
    	System.out.println(" crear transferencia "+ nuevo);
        
    	if(nuevo.getCbuDestino()!=null && nuevo.getCbuOrigen()!=null && nuevo.getCodigoTransferencia()!=null) {
        	MedioPago temp = medioServ.createMedioPago(nuevo);
            
            try {
            	return ResponseEntity.created(new URI("/api/medioPago" + temp.getId())).body(temp);
            	
            }catch(Exception e) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } 
    	}
    	else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    
    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<MedioPago> borrar(@PathVariable Integer id){
    	medioServ.deleteMedioPagoPorId(id);
        return ResponseEntity.ok().build();
       
    }
	
	
}
