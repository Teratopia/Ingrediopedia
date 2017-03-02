package dbSplicers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import data.Ingredient;
import data.Recipe;

public class UploaderOne {

	private static final String URL = "jdbc:mysql://localhost:3306/flavosaurus2_1";
	private static final List<Recipe> AFRRECIPES = ReadWriteAFR.loadRecipes("/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/ArmedForcesRecipes.txt");
	private static final List<Recipe> ETHNICRECIPES = ReadWriteAFR.loadRecipes("/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/EthnicRecipes.txt");
	private static final List<Recipe> COMMONRECIPES = ReadWriteAFR.loadRecipes("/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/CommonRecipes.txt");
	private static final List<Recipe> VEGETARIANRECIPES = ReadWriteAFR.loadRecipes("/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/VegetarianRecipes.txt");

	public UploaderOne() throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

	}
	
	public static List<Recipe> compileLists(){
		
		List<Recipe> recipes = new ArrayList<>();
		
		for(Recipe r : AFRRECIPES){
			recipes.add(r);
		}
		for(Recipe r : ETHNICRECIPES){
			recipes.add(r);
		}
		for(Recipe r : COMMONRECIPES){
			recipes.add(r);
		}
		for(Recipe r : VEGETARIANRECIPES){
			recipes.add(r);
		}
		
		return recipes;
	}

	public static void main(String[] args) throws SQLException {

		String user = "root";
		String pass = "root";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement("");
		List<Recipe> recipes = compileLists();

		for (Recipe r : recipes) {
			System.out.println("in for recipes");

			stmt = conn.prepareStatement(
					"INSERT INTO recipe (User_id, Name, Create_Date, Update_Date, Method) VALUES (?, ?, ?, ?, ?);");
			stmt.setString(1, "1");
			stmt.setString(2, r.getName());
			DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			String d1 = df.format(r.getCreateDate());
			stmt.setString(3, d1);
			String d2 = df.format(r.getUpdateDate());
			stmt.setString(4, d2);
			stmt.setString(5, r.getProcedure());
			stmt.executeUpdate();

			Set<Ingredient> ingredients = r.getIngredients();

			String sqltxt = "SELECT id FROM recipe WHERE name = '" + r.getName() + "';";
			ResultSet rs = stmt.executeQuery(sqltxt);

			String recipeId = "";
			while (rs.next()) {
				recipeId = rs.getString(1);
				System.out.println("recipe id: " + recipeId);
			}

			for (Ingredient i : ingredients) {
				System.out.println("in for ingredients");

				String sqltxt2 = "SELECT id FROM ingredient WHERE name = '" + i.getName() + "';";
				rs = stmt.executeQuery(sqltxt2);
				String ingredientId = "null";
				while (rs.next()) {
					ingredientId = rs.getString(1);
					System.out.println("ingredient id: " + ingredientId);
				}

				if (ingredientId.equals("null")) {
					PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO ingredient (Name) VALUES (?);");
					stmt2.setString(1, i.getName());
					stmt2.executeUpdate();

					rs = stmt2.executeQuery(sqltxt2);
					while (rs.next()) {
						ingredientId = rs.getString(1);
						System.out.println("ingredient id: " + ingredientId);
					}
					stmt2.close();
				}

				String sqltxt3 = "select * from recipe_has_ingredient where recipe_id = " + recipeId
						+ " AND ingredient_id = " + ingredientId + ";";
				String isThere = "nopenopenope";
				rs = stmt.executeQuery(sqltxt3);
				while (rs.next()) {
					isThere = ingredientId = rs.getString(1);
					System.out.println("ingredient id: " + ingredientId);
				}
				if (isThere.equals("nopenopenope")) {
					PreparedStatement stmt3 = conn.prepareStatement(
							"INSERT INTO recipe_has_ingredient (Recipe_id, Ingredient_id) values (?, ?)");
					stmt3.setString(1, recipeId);
					stmt3.setString(2, ingredientId);
					stmt3.executeUpdate();
					stmt3.close();
				}
			}

			rs.close();

		}
		
		conn.close();

	}

}
