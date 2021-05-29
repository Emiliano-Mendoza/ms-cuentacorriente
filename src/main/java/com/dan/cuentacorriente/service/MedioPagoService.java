package com.dan.cuentacorriente.service;

import java.util.List;
import java.util.Optional;


import com.dan.cuentacorriente.domain.MedioPago;


public interface MedioPagoService {
	
	public List<MedioPago> getAllMedioPagos();
	public void deleteMedioPago(MedioPago medio);
	public void deleteMedioPagoPorId(Integer id);
	public Optional<MedioPago> findById(Integer id);
	
	public MedioPago createMedioPago(MedioPago medio);
	
}
