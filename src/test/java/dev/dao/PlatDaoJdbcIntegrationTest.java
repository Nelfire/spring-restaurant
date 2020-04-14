package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
@SpringJUnitConfig(classes = { PlatDaoJdbc.class, JdbcTestConfig.class })
@ActiveProfiles({ "PlatDaoJdbc" })
public class PlatDaoJdbcIntegrationTest {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	PlatDaoJdbc platDaoJdbc;

	@Test
	void TestListerPlatsNonVide() {

		assertThat(platDaoJdbc.listerPlats()).isNotEmpty();
	}

	@Test
	void TestAjouterPlat() {
//		Plat plat1 = new Plat("Kebab plein de gras", 1500);
//		platDaoJdbc.ajouterPlat(plat1.getNom(), plat1.getPrixEnCentimesEuros());
//
//		Plat platAjoute = new Plat("plat 1", 1200);
//		platDaoJdbc.ajouterPlat(platAjoute.getNom(), platAjoute.getPrixEnCentimesEuros());
//
//		String sql = "SELECT * FROM plats where nom=?";
//		Plat platRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "plat 1" }, new PlatRowMapper());
//
//		assertThat(platAjoute).isEqualTo(platRecupere);

		Plat platAjoute = new Plat("plat 1", 1200);
		platDaoJdbc.ajouterPlat(platAjoute.getNom(), platAjoute.getPrixEnCentimesEuros());

		String sql = "SELECT * FROM plat where nom=?";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "plat 1" }, new PlatRowMapper());

		assertThat(platAjoute).isEqualTo(platRecupere);

	}
}
