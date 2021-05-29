package com.dan.cuentacorriente.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.cuentacorriente.dao.MedioPagoRepository;
import com.dan.cuentacorriente.domain.MedioPago;
import com.dan.cuentacorriente.service.MedioPagoService;

@Service
public class MedioPagoServiceImpl implements MedioPagoService {
	
	@Autowired
	private MedioPagoRepository medioRepo;
	
	public MedioPago createMedioPago(MedioPago medio) {
		return medioRepo.save(medio);
	}
	
	public List<MedioPago> getAllMedioPagos(){
		return medioRepo.findAll();
	}
	
	public void deleteMedioPago(MedioPago medio) {
		medioRepo.delete(medio);
	}
	
	public void deleteMedioPagoPorId(Integer id) {
		medioRepo.deleteById(id);
	}
	
	public Optional<MedioPago> findById(Integer id){
		return medioRepo.findById(id);
	}
	
}
