package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig({ JpaTestConfig.class, PlatDaoJpa.class, DataSourceH2TestConfig.class })
@ActiveProfiles({ "PlatDaoJpa" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJpaTest {
	@Autowired
	IPlatDao platDaoJpa;

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJpaTest.class.getName());

	@Test
	void listerPlatsNonVide() {
		List<Plat> listePlats = platDaoJpa.listerPlats();
		assertFalse(listePlats.isEmpty());
	}

	@Test
	void testAjouterPlat() {
		Plat ajout = new Plat("KEBABBIENGRAS", 1200);
		platDaoJpa.ajouterPlat(ajout.getNom(), ajout.getPrixEnCentimesEuros());
		assertThat(platDaoJpa.listerPlats()).contains(ajout);
	}
}
