package com.dan.cuentacorriente.domain;


import javax.persistence.Entity;
//import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue(value = "Efectivo")
//@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Efectivo extends MedioPago {

	private Integer nroRecibo;

	public Integer getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(Integer nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	@Override
	public String toString() {
		return "Efectivo [nroRecibo=" + nroRecibo + ", id=" + id + ", observacion=" + observacion + "]";
	}
	
	
}