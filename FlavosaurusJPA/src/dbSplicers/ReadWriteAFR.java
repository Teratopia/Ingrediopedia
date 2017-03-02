package dbSplicers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Ingredient;
import data.Recipe;
import data.User;

public class ReadWriteAFR {

	public static void main(String[] args) {

//		List<String> ingredients = getIngredientList();
//		List<Recipe> recipes = loadRecipes("/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/VegetarianRecipes.txt");
		
//		System.out.println(recipes.get(8));
//
//		for (Ingredient i : recipes.get(8).getIngredients()) {
//
//			System.out.println(i.toString());
//
//		}

	}

	public static List<Recipe> loadRecipes(String filePath) {
//		"/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/testReadIn.txt"

		List<Recipe> recipes = new ArrayList<Recipe>();
		List<String> ingNames = getIngredientList();

		try (BufferedReader bufIn = new BufferedReader(
				new FileReader(filePath))) {

			String line;
			FileWriter fw = new FileWriter("ingsNotOnList.txt");
            PrintWriter pw = new PrintWriter(fw);
			while ((line = bufIn.readLine()) != null) {
				if (line.contains("<recipe createDate")) {

					List<String> ingredients = new ArrayList<String>();
					Matcher m = Pattern.compile("(?:ItemName=\"(.+?)\")").matcher(line);
					while (m.find()) {
						boolean flag = false;
						for(String s : ingNames){
							if(m.group(1).toLowerCase().contains(s.toLowerCase())){
								ingredients.add(s);
								flag = true;
								break;
							}
						}
						if(flag == false){

				            pw.println(m.group(1));

							System.err.println(m.group(1));
						}
						
					}

					Date createDate = new Date();
					m = Pattern.compile("(?:createDate=\"(.+?)\")").matcher(line);
					while (m.find()) {
						String s = m.group(1);
						DateFormat format = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
						try {
							createDate = format.parse(s);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

					Date updateDate = new Date();
					m = Pattern.compile("(?:modifyDate=\"(.+?)\")").matcher(line);
					while (m.find()) {
						String s = m.group(1);
						DateFormat format = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
						try {
							createDate = format.parse(s);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

					String name = "";
					m = Pattern.compile("(?:description=\"(.+?)\")").matcher(line);
					while (m.find()) {
						name = m.group(1);
					}

					Set<Ingredient> ings = new HashSet<Ingredient>();
					for (String s : ingredients) {
						Ingredient i = new Ingredient();
						i.setName(s);
						ings.add(i);
					}
					User u = new User();
					u.setId(0);
					Recipe r = new Recipe();
					r.setName(name);
					r.setId(0);
					r.setUser(u);
					r.setIngredients(ings);
					r.setCreateDate(createDate);
					r.setUpdateDate(updateDate);
					r.setProcedure("Online Upload");

					recipes.add(r);

				}

			}
			pw.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return recipes;

	}

	public static List<String> getIngredientList() {

		List<String> names = new ArrayList<String>();
		String line = "";
		try (BufferedReader bufIn = new BufferedReader(
				new FileReader("/Users/Jolteon/Flavosaurus/FlavosaurusJPA/src/IngredientList2.txt"))) {
			while ((line = bufIn.readLine()) != null) {
				if (line.length() > 2) {
//					System.out.println(line);
					names.add(line);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		names.sort(new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				return obj2.length() - obj1.length();
			}
		});

//		for (String s : names) {
//			System.out.println(s);
//		}

		return names;

	}

}
