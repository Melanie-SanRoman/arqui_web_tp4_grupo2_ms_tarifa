package com.arqui_web.tarifa_service.model;

import com.arqui_web.tarifa_service.dto.TarifaResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarifa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private TipoTarifa tipo;
	@Column
	private Double montoKm;

	public Tarifa() {
		super();
	}

	public Tarifa(TipoTarifa tipo, Double monto) {
		super();
		this.tipo = tipo;
		this.montoKm = monto;
	}

	public TarifaResponseDTO toTarifaDTO() {
		return new TarifaResponseDTO(this.getId(), this.getTipo(), this.getMontoKm());
	}

	public TipoTarifa getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarifa tipo) {
		this.tipo = tipo;
	}

	public Double getMontoKm() {
		return montoKm;
	}

	public void setMontoKm(Double monto) {
		this.montoKm = monto;
	}

	public Long getId() {
		return id;
	}

}
