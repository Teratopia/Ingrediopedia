package data;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Recipe {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value="user_recipes")
	private User user;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	private Date updateDate;
	private String name;
	@Column(name = "Method")
	private String procedure;
	@Column(name = "tools_needed")
	private String toolsNeeded;
	@Column(name = "time_needed")
	private String timeNeeded;
//	@OneToMany(mappedBy = "recipe", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
//	@JsonManagedReference(value="ingredients_recipes")
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}) 
    @JoinTable(name="recipe_has_ingredient", joinColumns=@JoinColumn(name="recipe_id"), inverseJoinColumns=@JoinColumn(name="ingredient_id"))  
	@JsonIgnore
	private Set<Ingredient> ingredients;
	
	public Recipe(){}

//	public void addIngredient(Ingredient i) {
//
//		if (!this.ingredients.contains(i)) {
//			ingredients.add(i);
//			i.addRecipe(this);
//		}
//
//	}
//
//	public void removeIngredient(Ingredient i) {
//
//		if (this.ingredients.contains(i)) {
//			this.ingredients.remove(i);
//		}
//		i.removeRecipe(this);
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getToolsNeeded() {
		return toolsNeeded;
	}

	public void setToolsNeeded(String toolsNeeded) {
		this.toolsNeeded = toolsNeeded;
	}

	public String getTimeNeeded() {
		return timeNeeded;
	}

	public void setTimeNeeded(String timeNeeded) {
		this.timeNeeded = timeNeeded;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", createDate=" + createDate + ", user id=" + user.getId() + "]";
	}
	
	
}  