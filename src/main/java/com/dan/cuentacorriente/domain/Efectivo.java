package com.dan.cuentacorriente.domain;

public class Efectivo extends MedioPago {

	private Integer nroRecibo;

	public Integer getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(Integer nroRecibo) {
		this.nroRecibo = nroRecibo;
	}
}