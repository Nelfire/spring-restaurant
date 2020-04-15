package dev.entite;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author 20-100
 *
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;

	@ManyToMany(mappedBy = "ingredients")
	private Set<Plat> plats;
	// TODO Générer les getters/setters

	/**
	 * Constructeur vide
	 * 
	 */
	public Ingredient() {
	}

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param nom
	 */
	public Ingredient(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
