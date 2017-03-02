package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CrossCount")
public class CrossCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "ingredient_one")
	private Ingredient ingredient1;
	@ManyToOne
	@JoinColumn(name = "ingredient_two")
	private Ingredient ingredient2;
	private int count;
	
	public CrossCount(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ingredient getIngredient1() {
		return ingredient1;
	}

	public void setIngredient1(Ingredient ingredient1) {
		this.ingredient1 = ingredient1;
	}

	public Ingredient getIngredient2() {
		return ingredient2;
	}

	public void setIngredient2(Ingredient ingredient2) {
		this.ingredient2 = ingredient2;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CrossCount [id=" + id + ", ingredient1=" + ingredient1 + ", ingredient2=" + ingredient2 + ", count="
				+ count + "]";
	}
	
	
}
