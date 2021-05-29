package com.dan.cuentacorriente.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dan.cuentacorriente.domain.MedioPago;

public interface MedioPagoRepository extends JpaRepository<MedioPago, Integer> {

}
