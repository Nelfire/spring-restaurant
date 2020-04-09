package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;

/**
 * @author 20-100
 *
 */
class PlatServiceVersion1Test {

	private IPlatDao platDao;
	private IPlatService platServiceVersion1;

	@BeforeEach
	void setUp() {
		platDao = mock(IPlatDao.class);
		platServiceVersion1 = new PlatServiceVersion1(platDao);
	}

	@Test
	// Nom invalide (moins de 3 caracteres)
	void testAjouterPlatNomInvalide() {
		assertThatThrownBy(() -> {
			platServiceVersion1.ajouterPlat("a", 2500);
		}).hasMessage("un plat doit avoir un nom de plus de 3 caractères");
	}

	@Test
	// un prix invalide (moins de 3 caractères)
	void testAjouterPlatPrixInvalide() {
		assertThatThrownBy(() -> {
			platServiceVersion1.ajouterPlat("Kebab", 50);
		}).hasMessage("le prix d'un plat doit être supérieur à 5 €");
	}

	@Test
	void testAjouterPlatValide() {
		platServiceVersion1.ajouterPlat("kebab", 1200);
		verify(platDao).ajouterPlat("kebab", 1200);
	}

}
