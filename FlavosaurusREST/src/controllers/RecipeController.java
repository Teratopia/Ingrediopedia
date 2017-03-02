package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.Recipe;
import data.RecipeDAO;

@RestController
public class RecipeController {

	@Autowired
	RecipeDAO recipedao;
	
	@RequestMapping(path="recipe/{id}", method=RequestMethod.GET)
	public Recipe show(@PathVariable int id){
		return recipedao.showRecipe(id);
	}
	
	@RequestMapping(path="recipe", method=RequestMethod.POST)
	public Recipe create(String recipeJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		Recipe r = null;
		try{
			r = mapper.readValue(recipeJSON, Recipe.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return recipedao.createRecipe(r);
	}
	
	@RequestMapping(path="recipe/{id}", method=RequestMethod.PUT)
	public Recipe update(@PathVariable int id, @RequestBody String recipeJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		Recipe r = null;
		try{
			r = mapper.readValue(recipeJSON, Recipe.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return recipedao.updateRecipe(id, r);
	}
	
	@RequestMapping(path="recipe/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id){
		recipedao.destroyRecipe(id);
	}
	
}
