package data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class RatingComment {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value="user_ratings")
	private User user;
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "cook_method")
	private CookType cookMethod;
	private short sweet;
	private short sour;
	private short bitter;
	private short salt;
	private short umami;
	private String comment;
	
	public RatingComment(){}

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

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public CookType getCookMethod() {
		return cookMethod;
	}

	public void setCookMethod(CookType cookMethod) {
		this.cookMethod = cookMethod;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RatingComment [id=" + id + ", createDate=" + createDate + ", comment=" + comment + "]";
	}
	
	

}
