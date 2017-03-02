package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.RatingComment;
import data.Recipe;
import data.User;
import data.UserDAO;

@RestController
public class UserController {

	@Autowired
	UserDAO userdao;
	
	@RequestMapping(value="ping", method=RequestMethod.GET)
	public String ping(){
		return "pong";
	}
	
	//X -- showing recipes despite jsonignore
	@RequestMapping(path="users/{id}", method=RequestMethod.GET)
	public User show(@PathVariable int id){
		return userdao.show(id);
	}
	
	//O
	@RequestMapping(path="users/verify", method=RequestMethod.POST)
	public User verify(@RequestBody String userJSON){
		System.out.println(userJSON);
		ObjectMapper mapper = new ObjectMapper();
		User u = null;
		try{
			u = mapper.readValue(userJSON, User.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.verify(u.getUsername(), u.getPassword());
	}
	
	//X
	@RequestMapping(path="users", method=RequestMethod.POST)
	public User create(@RequestBody String userJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		User u = null;
		try{
			u = mapper.readValue(userJSON, User.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.create(u);
	}
	
	//X
	@RequestMapping(path="users/{id}", method=RequestMethod.PUT)
	public User update(@PathVariable int id, @RequestBody String userJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		User u = null;
		try{
			u = mapper.readValue(userJSON, User.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.update(id, u);
	}
	
	//X
	@RequestMapping(path="users/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable int id){
		userdao.destroy(id);
	}
	
	//X
	@RequestMapping(path="users/{id}/rcs", method=RequestMethod.GET)
	public List<RatingComment> indexRCs(@PathVariable int id){
		return userdao.indexRCs(id);
	}
	
	//X
	@RequestMapping(path="users/{id}/rc", method=RequestMethod.POST)
	public RatingComment createRC(@PathVariable int id, @RequestBody String rcJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		RatingComment rc = null;
		try{
			rc = mapper.readValue(rcJSON, RatingComment.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.createRC(rc, id);
	}
	
	//X
	@RequestMapping(path="users/{id}/rc/{rid}", method=RequestMethod.PUT)
	public RatingComment updateRC(@PathVariable int id, @PathVariable int rid, @RequestBody String rcJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		RatingComment rc = null;
		try{
			rc = mapper.readValue(rcJSON, RatingComment.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.updateRC(id, rid, rc);
	}
	
	//X
	@RequestMapping(path="users/{id}/rcs/{rid}", method=RequestMethod.DELETE)
	public void deleteRC(@PathVariable int id, @PathVariable int rid){
		
		userdao.destroyRC(rid);
	}
	
	//X
	@RequestMapping(path="users/{id}/recipes/{rid}", method=RequestMethod.GET)
	public Recipe showRecipe(@PathVariable int id, @PathVariable int rid){
		return userdao.showRecipe(rid);
	}
	
	//X
	@RequestMapping(path="users/{id}/recipes", method=RequestMethod.GET)
	public List<Recipe> indexRecipes(@PathVariable int id){
		return userdao.indexRecipes(id);
	}
	
	//X -- Id auto-inc working?
	@RequestMapping(path="users/{id}/recipe", method=RequestMethod.POST)
	public Recipe createRecipe(@PathVariable int id, @RequestBody String rJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		Recipe r = null;
		try{
			r = mapper.readValue(rJSON, Recipe.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.createRecipe(r, id);
	}
	
	//X
	@RequestMapping(path="users/{id}/recipes/{rid}", method=RequestMethod.PUT)
	public Recipe updateRecipe(@PathVariable int id, @PathVariable int rid, @RequestBody String rJSON){
		
		ObjectMapper mapper = new ObjectMapper();
		Recipe r = null;
		try{
			r = mapper.readValue(rJSON, Recipe.class);
		}catch(Exception e){
			System.out.println(e);
		}
		
		return userdao.updateRecipe(id, rid, r);
	}
	
	//X
	@RequestMapping(path="users/{id}/recipes/{rid}", method=RequestMethod.DELETE)
	public void deleteRecipe(@PathVariable int id, @PathVariable int rid){
		
		userdao.destroyRecipe(rid);
	}
	
}
