package com.arqui_web.tarifa_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arqui_web.tarifa_service.dto.CostoRequestDTO;
import com.arqui_web.tarifa_service.dto.CostoResponseDTO;
import com.arqui_web.tarifa_service.dto.TarifaResponseDTO;
import com.arqui_web.tarifa_service.model.Tarifa;
import com.arqui_web.tarifa_service.model.TipoTarifa;
import com.arqui_web.tarifa_service.repository.TarifaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TarifaService {
	@Autowired
	private TarifaRepository repository;
	private static final Logger log = LoggerFactory.getLogger(TarifaService.class);

	public Optional<TarifaResponseDTO> obtenerTarifa(String tarifa) {
		TipoTarifa tipoTarifa = null;
		if (tarifa != null) {
			tipoTarifa = TipoTarifa.valueOf(tarifa.toUpperCase());
		}
		return repository.findByTipo(tipoTarifa).map(t -> {

			TarifaResponseDTO dto = t.toTarifaDTO();
			return dto;
		});
	}

	public Iterable<TarifaResponseDTO> obtenerTarifas() {
		return repository.findAll().stream().map(t -> t.toTarifaDTO()).toList();
	}

	public Optional<TarifaResponseDTO> updateTarifa(TarifaResponseDTO dto, String tarifa) {
		TipoTarifa tipoTarifa = null;
		if (tarifa != null) {
			tipoTarifa = TipoTarifa.valueOf(tarifa.toUpperCase());
		}
		return repository.findByTipo(tipoTarifa).map(t -> {
			t.setMontoKm(dto.getMonto());

			repository.save(t);
			log.info("Tarifa de tipo {} actualizada correctamente", tarifa);
			return t.toTarifaDTO();
		});
	}

	public CostoResponseDTO calcularCosto(CostoRequestDTO dto) {
		double km = dto.getKilometros();
		double pausa = dto.getMinutosPausa();

		// Determinar tipo de tarifa según la pausa
		TipoTarifa tipo = (pausa >= 15) 
				? TipoTarifa.EXTRA 
				: TipoTarifa.BASICA;

		// Buscar tarifa
		Tarifa tarifa = repository.findByTipo(tipo)
				.orElseThrow(() -> new EntityNotFoundException("No existe la tarifa de tipo: " + tipo));

		// Calcular costo
		double costo = km * tarifa.getMontoKm();

		return new CostoResponseDTO(costo, tarifa.getTipo().toString());
	}

}
