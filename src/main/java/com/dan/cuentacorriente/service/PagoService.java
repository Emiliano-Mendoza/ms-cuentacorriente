package com.dan.cuentacorriente.service;

import java.util.List;
import java.util.Optional;

import com.dan.cuentacorriente.domain.Pago;

public interface PagoService {
	
	public Pago createPago(Pago pago);
	public List<Pago> getAllPagos();
	public void deletePago(Pago pago);
	public void deletePagoPorId(Integer id);
	public Optional<Pago> findById(Integer id);
	
}
