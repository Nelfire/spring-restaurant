package dev.dao;

import java.util.List;

import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
public interface IPlatDao {
	List<Plat> listerPlats();

	void ajouterPlat(String nomPlat, Integer prixPlat);
}
