package com.dan.cuentacorriente.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dan.cuentacorriente.domain.Pago;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pago")
@Api(value = "PagoRest")
public class PagoRest {
	
	private static final List<Pago> listaPagos = new ArrayList<>();
    private static Integer ID_GEN = 1;
    
    @GetMapping(path = "/id/{id}")
    @ApiOperation(value = "Busca un pago por id")
    public ResponseEntity<Pago> EmpleadoPorId(@PathVariable Integer id){

        Optional<Pago> c =  listaPagos
                .stream()
                .filter(unCli -> unCli.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(c);
    }
    
    @GetMapping
    public ResponseEntity<List<Pago>> todos(){
        return ResponseEntity.ok(listaPagos);
    }
    
    @PostMapping
    public ResponseEntity<Pago> crear(@RequestBody Pago nuevo){
    	System.out.println(" crear empleado "+nuevo);
        nuevo.setId(ID_GEN++);
        listaPagos.add(nuevo);
        return ResponseEntity.ok(nuevo);
    }
    
    @PutMapping(path = "/id/{id}")
    @ApiOperation(value = "Actualiza un pago")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Actualizado correctamente"),
        @ApiResponse(code = 401, message = "No autorizado"),
        @ApiResponse(code = 403, message = "Prohibido"),
        @ApiResponse(code = 404, message = "El ID no existe")
    })
    public ResponseEntity<Pago> actualizar(@RequestBody Pago nuevo,  @PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaPagos.size())
        .filter(i -> listaPagos.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            listaPagos.set(indexOpt.getAsInt(), nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<Pago> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaPagos.size())
        .filter(i -> listaPagos.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            listaPagos.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}