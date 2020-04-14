package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
// Créer une implémentation dev.dao.PlatDaoJpa de l’interface dev.dao.IPlatDao qui utilise @PersistenceContext et EntityManager pour accéder aux données
@Repository
@Profile("PlatDaoJpa")
public class PlatDaoJpa implements IPlatDao {

	private EntityManager em;

	@Override
	public List<Plat> listerPlats() {
		return em.createQuery("SELECT p FROM Plat p", Plat.class).getResultList();
	}

	@Override
	@Transactional
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		Plat plat = new Plat(nomPlat, prixPlat);
		em.persist(plat);

	}

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
