package com.dan.cuentacorriente.domain;

import java.time.Instant;


import javax.persistence.Entity;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
//import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue(value = "Cheque")
//@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cheque extends MedioPago {

	private Integer nroCheque;
	
//	@CreationTimestamp
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_COBRO", columnDefinition = "TIME")
	private Instant fechaCobro;
	private String banco;
	
	public Integer getNroCheque() {
		return nroCheque;
	}
	public void setNroCheque(Integer nroCheque) {
		this.nroCheque = nroCheque;
	}
	public Instant getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(Instant fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	@Override
	public String toString() {
		return "Cheque [nroCheque=" + nroCheque + ", fechaCobro=" + fechaCobro + ", banco=" + banco + ", id=" + id
				+ ", observacion=" + observacion + "]";
	}
	
}