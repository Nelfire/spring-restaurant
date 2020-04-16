package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.JpaTestConfig;
import dev.entite.Plat;

/**
 * @author 20-100
 *
 */
@SpringJUnitConfig(classes = { JpaTestConfig.class })
@Transactional
@ActiveProfiles({ "PlatDaoJpa" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatRepositoryIntegrationTest {

	@Autowired
	PlatRepository platRepository;

	List<Plat> listePlat;

	/*
	 * testFindAll() qui teste le fonctionnement de la méthode
	 * PlatRepository::findAll()
	 */
	@Test
	void testFindAll() {

		listePlat = platRepository.findAll();

		assertThat(listePlat).isNotNull();
	}

	/*
	 * • testFindAllSortAsc() qui teste le fonctionnement de la méthode
	 * PlatRepository::findAll(Sort) avec un tri ascendant par prix.
	 */

	@Test
	void testFindAllSortAsc() {

		listePlat = platRepository.findAll(Sort.by("prixEnCentimesEuros").ascending());

		assertThat(listePlat.get(0).getNom()).isEqualTo("Côte de boeuf");
	}

	/*
	 * testFindAllSortDesc() qui teste le fonctionnement de la méthode
	 * PlatRepository::findAll(Sort) avec un tri descendant par prix.
	 */
	@Test
	void testFindAllSortDesc() {

		listePlat = platRepository.findAll(Sort.by("prixEnCentimesEuros").descending());

		assertThat(listePlat.get(0).getNom()).isEqualTo("Gigot d'agneau");
	}

	/*
	 * • testFindAllPageable() qui teste le fonctionnement de la méthode
	 * PlatRepository::findAll(Pageable) : ◦ récupérer les deux premiers éléments en
	 * triant (ascendant) suivant le nom. ◦ résultat attendu : plats avec les noms
	 * Blanquette de veau et Couscous.
	 */
	@Test
	void testFindAllPageable() {

		Sort triAsc = Sort.sort(Plat.class).by(Plat::getNom).ascending();
		Pageable pageable = PageRequest.of(0, 2, triAsc);
		assertThat(platRepository.findAll(pageable).toList().get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(platRepository.findAll(pageable).toList().get(1).getNom()).isEqualTo("Couscous");
		assertThat(platRepository.findAll(pageable).toList().size()).isEqualTo(2);
	}

	/*
	 * • testFindById() qui teste le fonctionnement de la méthode
	 * PlatRepository::findById.
	 */
	@Test
	void testFindById() {
		assertThat(platRepository.findById(1).get().getNom()).isEqualTo("Magret de canard");
	}

	/*
	 * testGetOne() qui teste le fonctionnement de la méthode
	 * PlatRepository::getOne.
	 */
	@Test
	@Transactional
	void testGetOne() {
		assertThat(platRepository.getOne(1).getNom()).isEqualTo("Magret de canard");
	}

	/*
	 * • testCount() qui teste le fonctionnement de la méthode PlatRepository::count
	 */
	@Test
	void testGetCount() {
		assertThat(platRepository.count()).isEqualTo(6);
	}

	/*
	 * testFindByPrix() qui teste le fonctionnement d’une méthode (à créer) de
	 * l’interface PlatRepository qui permet de rechercher des plats par prix.
	 */
	@Test
	void testFindByPrix() {
		assertThat(platRepository.findByPrixEnCentimesEuros(1500).get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(platRepository.findByPrixEnCentimesEuros(1100).get(0).getNom()).isEqualTo("Côte de boeuf");
	}

	/*
	 * • testAvgPrix() qui teste le fonctionnement d’une méthode (à créer) de
	 * l’interface PlatRepository qui permet de trouver la moyenne (select avg(..)
	 * …) des prix supérieurs à un montant donné (clause where prix > ????)
	 * 
	 */

	@Test
	void testAvgPrix() {
		assertThat(platRepository.avgPrix(1500)).isEqualTo(2050);
	}

	/*
	 * 
	 * testFindByNomWithIngredients() qui teste le fonctionnement d’une méthode (à
	 * créer) de l’interface PlatRepository qui permet de rechercher des plats par
	 * nom. ◦ Faire le test avec le nom Moules-frites et vérifier que 6 ingrédients
	 * sont bien récupérés.
	 * 
	 */
	@Test
	void testFindByNomWithIngredients() {
		assertThat(platRepository.findByNomWithIngredients("Moules-frites")).hasSize(6);
	}

	/*
	 * • testSave() qui teste le fonctionnement de la méthode PlatRepository::save.
	 */
	@Test
	void testSave() {
		Plat p1 = new Plat("Kebab", 1500);
		platRepository.save(p1);
		assertThat(platRepository.findById(7).get().getNom()).isEqualTo("Kebab");
		// assertThat(platRepository.findAll()).contains(p1);
	}

	/*
	 * • testChangerNom() qui teste le fonctionnement d’une méthode (à créer) de
	 * l’interface PlatRepository qui permet modifier le nom d’un plat en
	 * fournissant l’ancien et le nouveau nom.
	 * 
	 */
	@Test
	void testChangerNom() {

		platRepository.changerNom("KEBAB-FRITES", "Moules-frites");
		assertThat(platRepository.findAll()).contains(new Plat("KEBAB-FRITES", 69696969));

	}

}
