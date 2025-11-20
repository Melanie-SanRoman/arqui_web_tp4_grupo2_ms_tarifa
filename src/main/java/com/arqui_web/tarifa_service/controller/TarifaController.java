package com.arqui_web.tarifa_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arqui_web.tarifa_service.dto.CostoRequestDTO;
import com.arqui_web.tarifa_service.dto.CostoResponseDTO;
import com.arqui_web.tarifa_service.dto.TarifaResponseDTO;
import com.arqui_web.tarifa_service.service.TarifaService;


@RestController
@RequestMapping("/tarifas")
public class TarifaController { 
	@Autowired
	private TarifaService service;

	@GetMapping("/{tipo}")
	public ResponseEntity<TarifaResponseDTO> getTarifaByTipo(@PathVariable String tipo) {
		Optional<TarifaResponseDTO> encontrada = service.obtenerTarifa(tipo);
		return encontrada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Iterable<TarifaResponseDTO>> getTarifas() {
		Iterable<TarifaResponseDTO> it = service.obtenerTarifas();
		return ResponseEntity.ok(it);
	}

	@PutMapping
	public ResponseEntity<TarifaResponseDTO> updateTarifa(@RequestBody TarifaResponseDTO dto, String tipo) {
		return service.updateTarifa(dto, tipo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/calcular")
	public ResponseEntity<CostoResponseDTO> calcularCosto(@RequestBody CostoRequestDTO request) {
		return ResponseEntity.ok(service.calcularCosto(request));
	}
}
