package data;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Ingredient {

	@Id
	private int id;
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "Recipe_has_Ingredient", joinColumns = @JoinColumn(name = "Ingredient_Id"), inverseJoinColumns = @JoinColumn(name = "Recipe_Id"))
//	@JsonBackReference(value="ingredients_recipes")
//	private Set<Recipe> recipes;
	@OneToMany(mappedBy = "ingredient", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonIgnore
	private Set<RatingComment> ratings;
	private String name;
	private String type;
	@Column(name = "Cook_Method")
	private HashMap<CookType, Integer> cookMethods;
	private short sweet;
	private short sour;
	private short bitter;
	private short salt;
	private short umami;

	public Ingredient() {
	}
	
//	public void addRecipe(Recipe r) {
//
//		if (!this.recipes.contains(r)) {
//			recipes.add(r);
//			r.addIngredient(this);
//		}
//
//	}
//
//	public void removeRecipe(Recipe r) {
//
//		if (this.recipes.contains(r)) {
//			this.recipes.remove(r);
//		}
//		r.removeIngredient(this);
//	}

	public void addRating(RatingComment r) {

		if (!this.ratings.contains(r)) {
			ratings.add(r);
			r.setIngredient(this);
		}

	}

	public void removeRating(RatingComment r) {

		if (this.ratings.contains(r)) {
			this.ratings.remove(r);
		}
		r.setIngredient(null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public Set<Recipe> getRecipes() {
//		return recipes;
//	}
//
//	public void setRecipes(Set<Recipe> recipes) {
//		this.recipes = recipes;
//	}

	public Set<RatingComment> getRatings() {
		return ratings;
	}

	public void setRatings(Set<RatingComment> ratings) {
		this.ratings = ratings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<CookType, Integer> getCookMethods() {
		return cookMethods;
	}

	public void setCookMethods(HashMap<CookType, Integer> cookMethods) {
		this.cookMethods = cookMethods;
	}

	public short getSweet() {
		return sweet;
	}

	public void setSweet(short sweet) {
		this.sweet = sweet;
	}

	public short getSour() {
		return sour;
	}

	public void setSour(short sour) {
		this.sour = sour;
	}

	public short getBitter() {
		return bitter;
	}

	public void setBitter(short bitter) {
		this.bitter = bitter;
	}

	public short getSalt() {
		return salt;
	}

	public void setSalt(short salt) {
		this.salt = salt;
	}

	public short getUmami() {
		return umami;
	}

	public void setUmami(short umami) {
		this.umami = umami;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

}
