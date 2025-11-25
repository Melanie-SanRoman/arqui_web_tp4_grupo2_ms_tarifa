package com.arqui_web.tarifa_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arqui_web.tarifa_service.model.Tarifa;
import com.arqui_web.tarifa_service.model.TipoTarifa;
import com.arqui_web.tarifa_service.repository.TarifaRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final TarifaRepository repository;

	public DataLoader(TarifaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) {
		if (repository.count() == 0) {
			Tarifa basica = new Tarifa();
			basica.setTipo(TipoTarifa.BASICA);
			basica.setMontoKm(50.0);

			Tarifa extra = new Tarifa();
			extra.setTipo(TipoTarifa.EXTRA);
			extra.setMontoKm(80.0);

			repository.save(basica);
			repository.save(extra);

			System.out.println("==> Tarifas BASICA y EXTRA creadas automáticamente");
		}
	}
}
