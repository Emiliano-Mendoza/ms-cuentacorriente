package com.dan.cuentacorriente.service;

import java.util.List;
import java.util.Optional;

import com.dan.cuentacorriente.domain.Cliente;

public interface ClienteService {
	
	public List<Cliente> getAllClientes();
	public Optional<Cliente> findById(Integer id);
	public Cliente createCliente(Cliente cliente);
	public Optional<Cliente> findByCuit(String cuit);
	public Optional<Cliente> findByRazonSocial(String razonSocial);
	
	
	
}
