package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
@SpringJUnitConfig(classes = PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
class PlatDaoFichierTest {

	@Autowired
	PlatDaoFichier platDaoFichier;

	/**
	 * 
	 */
	@Test
	void testAjouterPlat() {
		platDaoFichier.ajouterPlat("Kebabs", 1500);
		assertThat(platDaoFichier.listerPlats()).isNotEmpty();
	}

	@Test
	void testAjouterPlatVerifierSeul() {
		Plat plat1 = new Plat("Kebabs", 1500);
		List<Plat> listePlats = new ArrayList<>();
		listePlats.add(plat1);
		platDaoFichier.ajouterPlat("Kebabs", 1500);
		assertThat(platDaoFichier.listerPlats()).isEqualTo(listePlats);
	}

	/**
	 * Ajouter un second test de la méthode ajouterPlat en vérifiant que la
	 * sauvegarde s’est bien déroulée. Vérifier également qu’il n’y a qu’un seul
	 * enregistrement dans la liste des plats.
	 * 
	 */
	@Test
	void testListerPlat() {
		platDaoFichier.ajouterPlat("Kebabs", 1500);
		// platDaoFichier.ajouterPlat("Kebabs2", 1500);

		assertThat(platDaoFichier.listerPlats()).containsOnly(new Plat("Kebabs", 1500));
	}
}
