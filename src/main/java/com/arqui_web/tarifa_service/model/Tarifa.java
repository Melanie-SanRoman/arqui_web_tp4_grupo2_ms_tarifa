package com.arqui_web.tarifa_service.model;

import com.arqui_web.tarifa_service.dto.TarifaResponseDTO;

public class Tarifa {
	private Long id;
	private TipoTarifa tipo;
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
