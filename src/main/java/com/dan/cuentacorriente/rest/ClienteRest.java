package com.dan.cuentacorriente.rest;

import com.dan.cuentacorriente.domain.Cliente;

import com.dan.cuentacorriente.service.ClienteService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/cliente")
@Api(value = "ClienteRest")
public class ClienteRest {
    
    @Autowired
    private ClienteService clienteServ;

    @GetMapping(path = "/id/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Optional<Cliente>> clientePorId(@PathVariable Integer id){
    	return ResponseEntity.ok(clienteServ.findById(id));
    }
    
    @GetMapping(path = "/cuit/{cuit}")
    @ApiOperation(value = "Busca un cliente por cuit")
    public ResponseEntity<Optional<Cliente>> clientePorCuit(@PathVariable String cuit){
    	return ResponseEntity.ok(clienteServ.findByCuit(cuit));
    }
    
    @GetMapping(path = "/razonSocial/{razonSocial}")
    @ApiOperation(value = "Busca un cliente por razonSocial")
    public ResponseEntity<Optional<Cliente>> clientePorRazonSocial(@PathVariable String razonSocial){
    	return ResponseEntity.ok(clienteServ.findByRazonSocial(razonSocial));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> todos(){
        return ResponseEntity.ok(clienteServ.getAllClientes());
    }

//    @PostMapping
//    public ResponseEntity<Cliente> crear(@RequestBody Cliente nuevo){
//    	System.out.println(" crear cliente "+nuevo);
//    	Cliente temp = clienteServ.createCliente(nuevo);
//        
//        try {
//        	return ResponseEntity.created(new URI("/api/cliente" + temp.getId())).body(temp);
//        	
//        }catch(Exception e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

//    @PutMapping(path = "/id/{id}")
//    @ApiOperation(value = "Actualiza un cliente")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Actualizado correctamente"),
//        @ApiResponse(code = 401, message = "No autorizado"),
//        @ApiResponse(code = 403, message = "Prohibido"),
//        @ApiResponse(code = 404, message = "El ID no existe")
//    })
//    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente nuevo,  @PathVariable Integer id){
//        
//    	OptionalInt indexOpt =   IntStream.range(0, listaClientes.size())
//        .filter(i -> listaClientes.get(i).getId().equals(id))
//        .findFirst();
//
//        if(indexOpt.isPresent()){
//            listaClientes.set(indexOpt.getAsInt(), nuevo);
//            return ResponseEntity.ok(nuevo);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping(path = "/id/{id}")
//    public ResponseEntity<Cliente> borrar(@PathVariable Integer id){
//    	clienteServ.deleteClientePorId(id);
//        return ResponseEntity.ok().build();
//    }


}