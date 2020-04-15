package dev;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.AppConfig;
import dev.entite.Plat;
import dev.repository.PlatRepository;

public class AppSpringData {
	public static void main(String[] args) {
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		// récupération du bean Menu
		// List<Plat> arts = new ArrayList<>();

		PlatRepository platRepo = context.getBean(PlatRepository.class);

		List<Plat> plats = platRepo.findAll();
		for (Plat pla : plats) {
			System.out.println(pla.getNom());
		}
		// fermeture du contexte Spring
		context.close();
	}
}
