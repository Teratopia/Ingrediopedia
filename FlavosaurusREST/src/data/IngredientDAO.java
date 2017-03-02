package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IngredientDAO {

	@PersistenceContext
	EntityManager em;

	public Ingredient show(int id) {
		Ingredient ingredient = em.find(Ingredient.class, id);
		return ingredient;
	}
	
	public Ingredient showByName(String name) {
		
		String query = "select i from Ingredient i where i.name = '" +name+"'";
		Ingredient ingredient = em.createQuery(query, Ingredient.class).getSingleResult();
		return ingredient;
	}
	
	public Ingredient create(Ingredient i){
		
		em.persist(i);
		
		return i;
	}
	
	public Ingredient update(int id, Ingredient i){
		
		destroy(id);
		return create(i);
	}
	
	public void destroy(int id){
		
		em.remove(em.find(Ingredient.class, id));
	}
	
	public List<RatingComment> indexRCs(int id){
		
		String query = "select r from RatingComment r where r.ingredient = " +id;
		List<RatingComment> rcs = em.createQuery(query, RatingComment.class).getResultList();
		
		return rcs;
	}
	
	public List<CrossCount> indexCCRelations(int id){
		
		//check
		String query = "select c from CrossCount c where c.ingredient1 = "+id+" or c.ingredient2 = "+id;
		List<CrossCount> crossCounts = em.createQuery(query, CrossCount.class).getResultList();
		
		return crossCounts;
	}
	
	public List<CrossCount> indexRecipeCCs(Ingredient[] ingredients){
		
//		ArrayList<CrossCount> rccs = (ArrayList<CrossCount>) indexCCRelations(ingredients[0].getId());
		
		
		String query = "select c from CrossCount c where c.ingredient1 = "+ingredients[0].getId()+" or c.ingredient2 = "+ingredients[0].getId();
		if(ingredients.length > 1){
			for(int q = 1; q < ingredients.length ; q++){
				query += "OR c.ingredient1 = "+ingredients[q].getId()+" or c.ingredient2 = "+ingredients[q].getId();
			}
		}
		
		List<CrossCount> crossCounts = em.createQuery(query, CrossCount.class).getResultList();
		
		return crossCounts;
		
//		for(int i = 1; i < ingredients.length ; i++){
//			for(CrossCount icc : indexCCRelations(ingredients[i].getId())){
//				for(CrossCount rcc : rccs){
//					if(!rccs.contains(icc)){
//						rccs.add(icc);
//					}
					
					
					
					
//					if(icc.getIngredient1().getId() == rcc.getIngredient1().getId() ||
//							icc.getIngredient2().getId() == rcc.getIngredient2().getId() ||
//							icc.getIngredient1().getId() == rcc.getIngredient2().getId() ||
//							icc.getIngredient2().getId() == rcc.getIngredient1().getId()){
//						int nCount = rcc.getCount()+icc.getCount();
//						rcc.setCount(nCount);
//					} else {
//						rccs.add(icc);
//					}
//				}
//			}
//		}
//		
//		return rccs;
	}
	
}
