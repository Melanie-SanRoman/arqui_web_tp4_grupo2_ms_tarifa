package com.arqui_web.tarifa_service.dto;

import com.arqui_web.tarifa_service.model.TipoTarifa;

public class TarifaResponseDTO {
	private Long id;
	private TipoTarifa tipo;
	private Double monto;

	public TarifaResponseDTO() {
		super();
	}

	public TarifaResponseDTO(Long id, TipoTarifa tipo, Double monto) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.monto = monto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTarifa getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarifa tipo) {
		this.tipo = tipo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
