package com.dan.cuentacorriente.rest;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyInt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dan.cuentacorriente.DanPagosTest;
import com.dan.cuentacorriente.dao.ClienteRepository;
import com.dan.cuentacorriente.dao.MedioPagoRepository;
import com.dan.cuentacorriente.domain.Cheque;
import com.dan.cuentacorriente.domain.Cliente;

import com.dan.cuentacorriente.domain.Pago;
import com.dan.cuentacorriente.service.ClienteService;
import com.dan.cuentacorriente.service.MedioPagoService;
import com.dan.cuentacorriente.service.PagoService;




@SpringBootTest( 
		classes = DanPagosTest.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
@Profile("testing")
public class PagoRestTest {
	@LocalServerPort
	String puerto;
	  
	private final String urlServer= "http://localhost";
	private final String apiPago = "api/pago";
    int randomServerPort;
	
    private RestTemplate restTemplate = new RestTemplate();
    
	@MockBean
	ClienteService clienteServ;
	@MockBean
	PagoService pagoServ;
	@MockBean
	MedioPagoService medioServ;

	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	MedioPagoRepository medioRepo;
	
	
    @Test
    void deberiaAceptarPago() {
    	String server = urlServer+":"+puerto+"/"+apiPago;
		System.out.println("SERVER "+server);
		Pago unPago = new Pago();
		
		unPago.setCliente(clienteRepo.save(new Cliente()));
		unPago.setMedio(new Cheque());
		
		Optional<Cliente> opC = Optional.ofNullable(unPago.getCliente());
		when(clienteServ.findById(anyInt())).thenReturn(opC);
		when(medioServ.createMedioPago(any(Cheque.class))).thenReturn(unPago.getMedio());
		when(pagoServ.createPago(any(Pago.class))).thenReturn(unPago);
		
		HttpEntity<Pago> requestPago = new HttpEntity<>(unPago);
		ResponseEntity<Pago> respuesta = restTemplate.exchange(server, HttpMethod.POST, requestPago , Pago.class);
		
		assertTrue(respuesta.getStatusCode().equals(HttpStatus.CREATED));
    }
    
    @Test
    void deberiaRechazarPorClienteInexistente() {
    	String server = urlServer+":"+puerto+"/"+apiPago;
		System.out.println("SERVER "+server);
		Pago unPago = new Pago();
		unPago.setCliente(new Cliente());
		unPago.setMedio(new Cheque());
		
		
		HttpEntity<Pago> requestPago = new HttpEntity<>(unPago);
		Throwable ex = assertThrows( 
			      HttpClientErrorException.class, 
			      () -> {
			    	  ResponseEntity<Pago> respuesta = restTemplate.exchange(server, HttpMethod.POST, requestPago , Pago.class);
			  			assertTrue(respuesta.getStatusCode().equals(HttpStatus.BAD_REQUEST));
			      	}
			  );
		
		System.out.println(ex.getMessage());
		assertTrue(ex.getMessage().startsWith("400"));
    }
}
