package com.dan.cuentacorriente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dan.cuentacorriente.domain.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
	
	
	
}
