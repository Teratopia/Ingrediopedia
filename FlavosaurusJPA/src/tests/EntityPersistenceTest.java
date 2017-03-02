package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import data.Recipe;
import data.User;

public class EntityPersistenceTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Before
	public void first(){
		emf = Persistence.createEntityManagerFactory("FlavosaurusJPA");
		em = emf.createEntityManager();
	}

	@Test
	public void testRecipe() {
		
		User u = em.find(User.class, 1);
		assertEquals(u.getUsername(), "Uploads");
		
	}
	
	@Test
	public void testUserRecipesIngredients() {
		
		int id = 2;
		
		User u = em.find(User.class, id);
//		assertEquals(u.getUsername(), "testu1");
//		assertEquals(u.getRecipes().size(), 1);
		
		String query = "select r from Recipe r where r.user = " + 2;
		List<Recipe> recipes = em.createQuery(query, Recipe.class).getResultList();
		
		assertEquals(recipes.size(), 1);
		
		
		
	}

}
