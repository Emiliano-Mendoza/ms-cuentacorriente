package com.dan.cuentacorriente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.cuentacorriente.dao.PagoRepository;
import com.dan.cuentacorriente.domain.Pago;
import com.dan.cuentacorriente.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {
	
	@Autowired
	private PagoRepository pagoRepo;
	
	public Pago createPago(Pago pago) {
		return pagoRepo.save(pago);
	}
	
	public List<Pago> getAllPagos(){
		return pagoRepo.findAll();
	}
	
	public void deletePago(Pago pago) {
		pagoRepo.delete(pago);
	}
	
	public void deletePagoPorId(Integer id) {
		pagoRepo.deleteById(id);
	}
	
	public Optional<Pago> findById(Integer id){
		return pagoRepo.findById(id);
	}
	

}
