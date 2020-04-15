package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);

	// ----- //
	@Query("SELECT AVG(p.prixEnCentimesEuros) FROM Plat p WHERE prixEnCentimesEuros > :prixChoix")
	Double avgPrix(@Param("prixChoix") Integer prix);

	@Query("SELECT p.ingredients FROM Plat p WHERE p.nom = :nomPlat")
//	@Query("SELECT i "
//			+ "FROM Ingredient i,Plat p "
//			+ "WHERE p.ingredients = i.plats "
//			+ "AND p.nom = :nomPlat")
	List<Ingredient> findByNomWithIngredients(@Param("nomPlat") String nom);

	// ----------------------------- //
	@Modifying
	@Query("UPDATE Plat p " + "SET p.nom = :nouveauNom " + "WHERE p.nom = :ancienNom")
	void changerNom(@Param("nouveauNom") String nouveauNom, @Param("ancienNom") String ancienNom);

}
