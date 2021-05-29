package com.dan.cuentacorriente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.cuentacorriente.dao.ClienteRepository;
import com.dan.cuentacorriente.domain.Cliente;
import com.dan.cuentacorriente.service.ClienteService;
@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepo.findAll();
	}

	@Override
	public Optional<Cliente> findById(Integer id) {
		return clienteRepo.findById(id);
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	@Override
	public Optional<Cliente> findByCuit(String cuit) {
		return clienteRepo.findByCuit(cuit);
	}

	@Override
	public Optional<Cliente> findByRazonSocial(String razonSocial) {
		return clienteRepo.findByRazonSocial(razonSocial);
	}
	
	
	
	
}
