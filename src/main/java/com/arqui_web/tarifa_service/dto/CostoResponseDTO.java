package com.arqui_web.tarifa_service.dto;

public class CostoResponseDTO {

    private Double costo;
    private String tipoTarifa;  // "BASICA" o "EXTRA"

    public CostoResponseDTO() {}

    public CostoResponseDTO(Double costo, String tipoTarifa) {
        this.costo = costo;
        this.tipoTarifa = tipoTarifa;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(String tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }
}

