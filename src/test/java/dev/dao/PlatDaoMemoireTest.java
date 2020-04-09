package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
class PlatDaoMemoireTest {
	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {

		List<Plat> listePlats = platDaoMemoire.listerPlats();
		assertThat(listePlats).isEmpty();
	}

	@Test
	void ajouterPlatCasPassants() {

		platDaoMemoire.ajouterPlat("Kebab", 1800);
		assertThat(platDaoMemoire.listerPlats()).isNotEmpty();
	}
}
