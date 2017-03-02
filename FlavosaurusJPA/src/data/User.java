package data;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class User {

	@Id
	private int id;
	@Enumerated(EnumType.STRING)
	private ROLE role;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;
	private String tagline;
	
	// change to unidirectional relationships? 
	@OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonManagedReference(value="user_recipes")
	@JsonIgnore
	private Set<Recipe> recipes;
	@JsonManagedReference(value="user_ratings")
	@OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonIgnore
	private Set<RatingComment> ratings;
	
	public User(){}

	public int getId() {
		return id;
	}
	
	public void addRating(RatingComment r){
		
		if(!this.ratings.contains(r)){
			ratings.add(r);
			r.setUser(this);
		}
		
	}
	
	public void removeRating(RatingComment r){
		
		if(this.ratings.contains(r)){
			this.ratings.remove(r);
		}
		r.setUser(null);
	}
	
	public void addRecipe(Recipe r){
		
		if(!this.recipes.contains(r)){
			recipes.add(r);
			r.setUser(this);
		}
	}
	
	public void removeRecipe(Recipe r){
		
		if(this.recipes.contains(r)){
			this.recipes.remove(r);
		}
		r.setUser(null);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public ROLE getRole() {
		return role;
	}
	public void setRole(ROLE role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public Set<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	public Set<RatingComment> getRatings() {
		return ratings;
	}
	public void setRatings(Set<RatingComment> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", username=" + username + ", createDate=" + createDate + "]";
	}
	
}
