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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tarifas")
@Tag(name = "Tarifas", description = "Endpoints de gestion de tarifas y calculos de costos")
public class TarifaController {
	@Autowired
	private TarifaService service;

	@Operation(summary = "Obtiene una tarifa por tipo", description = "Devuelve la tarifa asociada a un tipo")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
			@ApiResponse(responseCode = "404", description = "Tipo de tarifa no encontrada") })
	@GetMapping("/{tipo}")
	public ResponseEntity<TarifaResponseDTO> getTarifaByTipo(
			@Parameter(description = "Tipo de la tarifa solicitada") @PathVariable String tipo) {
		Optional<TarifaResponseDTO> encontrada = service.obtenerTarifa(tipo);
		return encontrada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Listar todas las tarifas", description = "Devuelve todas las tarifas registradas en el sistema")
	@ApiResponse(responseCode = "200", description = "Lista devuelta correctamente")
	@GetMapping
	public ResponseEntity<Iterable<TarifaResponseDTO>> getTarifas() {
		Iterable<TarifaResponseDTO> it = service.obtenerTarifas();
		return ResponseEntity.ok(it);
	}

	@Operation(summary = "Actualiza una tarifa", description = "Modifica los valores de una tarifa")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Valor de tarifa actualizado"),
			@ApiResponse(responseCode = "404", description = "Tipo de tarifa no encontrado") })
	@PutMapping
	public ResponseEntity<TarifaResponseDTO> updateTarifa(
			@Parameter(description = "DTO con los nuevos valores de la tarifa") @RequestBody TarifaResponseDTO dto,
			@Parameter(description = "Tipo de tarifa a actualizar") String tipo) {
		return service.updateTarifa(dto, tipo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Calcular costo", description = "Calcula el costo final usando la distancia en km, el tiempo y tipo de tarifa")
	@ApiResponse(responseCode = "200", description = "Calculo realizado con exito")
	@PostMapping("/calcular")
	public ResponseEntity<CostoResponseDTO> calcularCosto(
			@Parameter(description = "DTO de entrada con los datos a calcular")
			@RequestBody CostoRequestDTO request) {
		return ResponseEntity.ok(service.calcularCosto(request));
	}
}
