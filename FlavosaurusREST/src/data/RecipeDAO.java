package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RecipeDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public Recipe showRecipe(int id){
		return em.find(Recipe.class, id);
	}

	public Recipe createRecipe(Recipe recipe){
		
		em.persist(recipe);
		em.flush();
		
		return recipe;
	}
	
	public void destroyRecipe(int id){
		em.remove(em.find(Recipe.class, id));
	}
	
	public Recipe updateRecipe(int id, Recipe recipe){
		destroyRecipe(id);
		return createRecipe(recipe);
	}
	
}
