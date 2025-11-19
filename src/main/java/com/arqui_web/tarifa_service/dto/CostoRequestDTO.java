package com.arqui_web.tarifa_service.dto;

public class CostoRequestDTO {
    private Double kilometros;
    private Double minutosPausa;

    public CostoRequestDTO() {}

    public CostoRequestDTO(Double kilometros, Double minutosPausa) {
        this.kilometros = kilometros;
        this.minutosPausa = minutosPausa;
    }

    public Double getKilometros() {
        return kilometros;
    }

    public void setKilometros(Double kilometros) {
        this.kilometros = kilometros;
    }

    public Double getMinutosPausa() {
        return minutosPausa;
    }

    public void setMinutosPausa(Double minutosPausa) {
        this.minutosPausa = minutosPausa;
    }
}

