package dev;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.DataSourceConfig;
import dev.dao.PlatDaoJdbc;
import dev.entite.Plat;

public class JdbcApp {

	private static final Logger LOGGER = Logger.getLogger(JdbcApp.class.getName());

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class)) {

			PlatDaoJdbc articleDao = context.getBean(PlatDaoJdbc.class);

			Integer nbElements = articleDao.compter();

			LOGGER.info("NB ELEMENTS=" + nbElements);

			List<Plat> plats = articleDao.listerPlats();

			for (Plat pla : plats) {
				LOGGER.info(pla.getNom());
			}

		}

	}

}