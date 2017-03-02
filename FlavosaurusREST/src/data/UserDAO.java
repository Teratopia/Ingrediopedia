package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	//USER CRUD -----------------------------------------------------------------
	
	//X
	public User show(int id) {
		User user = em.find(User.class, id);
		
		Hibernate.initialize(user.getRatings());
		Hibernate.initialize(user.getRecipes());
		
		return user;
	}
	
	//X
	public User create(User u) {

		String rawPassword = u.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		u.setPassword(encodedPassword);
	
		em.persist(u);
		
		return u;
	}
	
	
	public User verify(String name, String pass) throws NoResultException {
		
		String query = "select u from User u where u.name = '" + name + "'";
		List<User> users = em.createQuery(query, User.class).getResultList();

		for (User u : users) {
			if (passwordEncoder.matches(pass, u.getPassword())) {
				return u;
			}
		}
		return null;
	}
	
	//X
	public User update(int id, User u) {
		destroy(id);
		return create(u);
	}
	
	//X
	public void destroy(int id) {
		em.remove(em.find(User.class, id));
	}
	
	//RATING COMMENT CRUD -----------------------------------------------------------------
	//X
	public List<RatingComment> indexRCs(int id){
		
		String query = "select i from RatingComment i where i.user = " + id + "";
		List<RatingComment> rcs = em.createQuery(query, RatingComment.class).getResultList();
		
		return rcs;
	}
	//X
	public RatingComment showRC(int id){
		return em.find(RatingComment.class, id);
	}
	//X
	public RatingComment createRC(RatingComment rc, int id){
		
		User u = em.find(User.class, id);
		rc.setUser(u);
		System.out.println(u);
		em.persist(rc);
		
		return rc;
	}
	
	public void destroyRC(int id){
		em.remove(em.find(RatingComment.class, id));
	}
	
	public RatingComment updateRC(int id, int rid, RatingComment rc){
		destroyRC(rid);
		return createRC(rc, id);
	}
	
	//RECIPE CRUD -----------------------------------------------------------------
	
	public Recipe showRecipe(int id){
		return em.find(Recipe.class, id);
	}
	
	public List<Recipe> indexRecipes(int id){
		
		String query = "select r from Recipe r where r.user = " + id;
		List<Recipe> recipes = em.createQuery(query, Recipe.class).getResultList();
		
		return recipes;
	}
	
	public Recipe createRecipe(Recipe r, int id){
		
		User u = em.find(User.class, id);
		r.setUser(u);
		
		em.persist(r);
		em.flush();
		
		return r;
	}
	
	public void destroyRecipe(int id){
		em.remove(em.find(Recipe.class, id));
	}
	
	public Recipe updateRecipe(int id, int rid, Recipe r){
		destroyRecipe(rid);
		return createRecipe(r, id);
	}
	
	
}


