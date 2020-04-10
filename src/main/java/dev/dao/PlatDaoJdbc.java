package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

@Repository
public class PlatDaoJdbc implements IPlatDao {

	private JdbcTemplate jdbcTemplate;

	public PlatDaoJdbc(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public Integer compter() {
		return this.jdbcTemplate.queryForObject("select count(*) from plat", Integer.class);
	}

	@Override
	public List<Plat> listerPlats() {
		return this.jdbcTemplate.query("select * from plat", new PlatRowMapper());
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {

	}

}
