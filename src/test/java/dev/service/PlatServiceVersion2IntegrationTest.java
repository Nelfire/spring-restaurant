package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;

/**
 * @author 20-100
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoMemoire.class })
@ActiveProfiles({ "PlatDaoMemoire", "PlatServiceVersion2" })
class PlatServiceVersion2IntegrationTest {

	@Autowired
	PlatServiceVersion2 platServiceVersion2;

	/**
	 * un test de la méthode ajouterPlat avec un nom et un prix valide. Vérifier que
	 * la liste des plats est bien alimentée
	 */
	@Test
	void ajouterPlatValide() {
		platServiceVersion2.ajouterPlat("kebabs", 1200);
		assertThat(platServiceVersion2.listerPlats()).isNotEmpty();
	}

	/**
	 * un test de la méthode ajouterPlat avec un prix invalide. Vérifier qu’une
	 * exception est bien lancée
	 */
	@Test
	void ajouterPlatPrixInvalide() {
		assertThatThrownBy(() -> {
			platServiceVersion2.ajouterPlat("Kebabs", 50);
		}).hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
