package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.CrossCount;
import data.Ingredient;
import data.IngredientDAO;
import data.RatingComment;

@RestController
public class IngredientController {

	@Autowired
	IngredientDAO ingredientdao;
	
	//X
	@RequestMapping(path="ings/{id}", method=RequestMethod.GET)
	public Ingredient show(@PathVariable int id){
		return ingredientdao.show(id);
	}
	//X
	@RequestMapping(path="ings/name", method=RequestMethod.POST)
	public Ingredient showByName(@RequestBody String name){
		
		return ingredientdao.showByName(name);
	}
	
	//X
	@RequestMapping(path="ings", method=RequestMethod.POST)
	public Ingredient create(@RequestBody String ingJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		Ingredient i = null;
		try{
			i = mapper.readValue(ingJSON, Ingredient.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return ingredientdao.create(i);
	}
	
	
	@RequestMapping(path="ings/{id}", method=RequestMethod.PUT)
	public Ingredient update(@PathVariable int id, @RequestBody String ingJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		Ingredient i = null;
		try{
			i = mapper.readValue(ingJSON, Ingredient.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return ingredientdao.update(id, i);
	}
	
	@RequestMapping(path="ings/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id){
		ingredientdao.destroy(id);
	}
	
	@RequestMapping(path="ings/{id}/rcs", method=RequestMethod.GET)
	public List<RatingComment> indexRCs(@PathVariable int id){
		return ingredientdao.indexRCs(id);
		
	}
	
	@RequestMapping(path="ings/{id}/ccs", method=RequestMethod.GET)
	public List<CrossCount> indexCCRelations(@PathVariable int id){
		
		List<CrossCount> crossCounts = ingredientdao.indexCCRelations(id);
		
		for(CrossCount cc : crossCounts){
			if(cc.getIngredient1().getId() == id){
				cc.setIngredient1(cc.getIngredient2());
				cc.setIngredient2(null);
			}
		}
		
		return crossCounts;
	}

	@RequestMapping(path="ings/rccs", method=RequestMethod.POST)
	public List<CrossCount> indexRecipeCCs(@RequestBody String ingList){
		
		ObjectMapper mapper = new ObjectMapper();
		Ingredient[] ings = null;
		try{
			ings = mapper.readValue(ingList, Ingredient[].class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("INGREDIENTS HERE ##############################    ####   #");
		System.out.println("Length: "+ings.length);
		
		List<CrossCount> crossCounts = ingredientdao.indexRecipeCCs(ings);
		
		for(int q = 0; q < ings.length ; q++){
			
			for(CrossCount cc : crossCounts){
				
				if(cc.getIngredient1() != null && cc.getIngredient1().getId() == ings[q].getId()){
					Ingredient replacement1 = cc.getIngredient1();
					Ingredient replacement2 = cc.getIngredient2();
					cc.setIngredient1(replacement2);
					cc.setIngredient2(replacement1);
				}
				
				
			}
		}
		
		List<CrossCount> removals = new ArrayList<CrossCount>();
		List<CrossCount> injections = new ArrayList<CrossCount>();
		for(CrossCount cc : crossCounts){
			for(CrossCount cc2 : crossCounts){
				if(cc.getId() != cc2.getId() && cc.getIngredient1() != null && cc2.getIngredient1() != null && 
						cc.getIngredient1().getName().equals(cc2.getIngredient1().getName())){
//					removals.add(cc2);
//					injections.add(cc);
//					injections.remove(cc2);
				}
			}
		}		
//		List<CrossCount> crossCountsRef = new ArrayList<CrossCount>();
//		List<CrossCount> removals = new ArrayList<CrossCount>();
//		crossCountsRef.addAll(crossCounts);
//		for(CrossCount cc : crossCounts){
//			
//			CrossCount cc3 = new CrossCount(){};
//			int index = 0;
//			
//			for(CrossCount cc2 : crossCountsRef){
//				if(cc.getIngredient1() != null && cc2.getIngredient1() != null &&
//						cc2.getIngredient1().getName().equals(cc.getIngredient1().getName())){
////					if(index == 0){
////					cc3 = cc;
////					}
//					index++;
//					if(index > 1){
////					cc2.setCount(cc.getCount()+cc3.getCount());
//						removals.add(cc3);
//					}
//				}
//			}
//		}
//		
//		crossCounts.removeAll(removals);
//		crossCounts.addAll(injections);
//		crossCounts.addAll(removals);
		
		
		return crossCounts;
	}
	
}
