package com.arqui_web.tarifa_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arqui_web.tarifa_service.model.Tarifa;
import com.arqui_web.tarifa_service.model.TipoTarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

	@Query("SELECT t FROM Tarifa t WHERE t.tipo = :tipo")
	public Optional<Tarifa> findByTipo(@Param("tipo") TipoTarifa tipo);
}
