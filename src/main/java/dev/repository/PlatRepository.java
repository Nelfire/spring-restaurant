package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
public interface PlatRepository extends JpaRepository<Plat, Integer> {

	/*
	 * testFindByPrix() qui teste le fonctionnement d’une méthode (à créer) de
	 * l’interface PlatRepository qui permet de rechercher des plats par prix.
	 */
	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);

	/*
	 * • testAvgPrix() qui teste le fonctionnement d’une méthode (à créer) de
	 * l’interface PlatRepository qui permet de trouver la moyenne (select avg(..)
	 * …) des prix supérieurs à un montant donné (clause where prix > ????)
	 * 
	 */
	@Query("SELECT AVG(p.prixEnCentimesEuros) FROM Plat p WHERE prixEnCentimesEuros > :prixChoix")
	Double avgPrix(@Param("prixChoix") Integer prix);

	/*
	 * • testFindByNomWithIngredients() qui teste le fonctionnement d’une méthode (à
	 * créer) de l’interface PlatRepository qui permet de rechercher des plats par
	 * nom. ◦ Faire le test avec le nom Moules-frites et vérifier que 6 ingrédients
	 * sont bien récupérés.
	 */
	@Query("SELECT p.ingredients FROM Plat p WHERE p.nom = :nomPlat")
	List<Ingredient> findByNomWithIngredients(@Param("nomPlat") String nom);

	/*
	 * • testChangerNom() qui teste le fonctionnement d’une méthode (à créer) de
	 * l’interface PlatRepository qui permet modifier le nom d’un plat en
	 * fournissant l’ancien et le nouveau nom.
	 * 
	 */
	@Modifying
	@Query("UPDATE Plat p " + "SET p.nom = :nouveauNom " + "WHERE p.nom = :ancienNom")
	void changerNom(@Param("nouveauNom") String nouveauNom, @Param("ancienNom") String ancienNom);

}
