package dbSplicers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CrossCounter {

	private static final String URL = "jdbc:mysql://localhost:3306/flavosaurus2_1";

	public CrossCounter() throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

	}

	public static void main(String[] args) {

		String user = "root";
		String pass = "root";

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement("");

			List<Integer> recipeIds = new ArrayList<Integer>();
			String sqltxt = "SELECT id FROM recipe;";
			ResultSet rs = stmt.executeQuery(sqltxt);
			
			while (rs.next()) {
				recipeIds.add(Integer.parseInt(rs.getString(1)));
			}

			for (int recipeId : recipeIds) {
				System.out.println("recipe: "+recipeId);
				List<Integer> ingredientIds = new ArrayList<Integer>();
				String sqltxt2 = "SELECT ingredient_id FROM recipe_has_ingredient where recipe_id = " + recipeId + ";";
				ResultSet rs2 = stmt.executeQuery(sqltxt2);
				
				while (rs2.next()) {
					ingredientIds.add(Integer.parseInt(rs2.getString(1)));
				}

				int index = 0;
				for (int ingredientId : ingredientIds) {
					
					System.out.println("ingredient 1: "+ingredientId);
					
					if(ingredientIds.indexOf(ingredientId)<= index){
						continue;
					}
					
					for (int ingredientTwoId : ingredientIds) {
						System.out.println("ingredient 2 "+ingredientTwoId);
						String sqltxt3 = "SELECT id, count FROM crosscount where (ingredient_one = "
								+ ingredientId + " AND ingredient_two = " + ingredientTwoId + ") OR (ingredient_one = "
								+ ingredientTwoId + " AND ingredient_two = " + ingredientId + ");";
						ResultSet rs3 = stmt.executeQuery(sqltxt3);
						String countString = "nopenopenope";
						int id = -1;
						while (rs3.next()) {
							id = Integer.parseInt(rs3.getString(1));
							countString = rs3.getString(2);
						}
						if(countString.equals("nopenopenope")){
							PreparedStatement stmt2 = conn.prepareStatement(
									"INSERT INTO crosscount (ingredient_one, ingredient_two, count) VALUES (?, ?, ?);");
							stmt2.setString(1, ""+ingredientId);
							stmt2.setString(2, ""+ingredientTwoId);
							stmt2.setString(3, ""+1);

							stmt2.executeUpdate();
						} else {
							int newCount = Integer.parseInt(countString) + 1;
							PreparedStatement stmt2 = conn.prepareStatement(
									"UPDATE crosscount SET count = "+newCount+" WHERE id = "+id+";");
							stmt2.executeUpdate();
						}

					}
					index++;
					
				}

			}
			
			//removing duplicates
			List<CrossCountIdHolder> ccihList = new ArrayList<CrossCountIdHolder>();
			String sqltxt4 = "SELECT id, ingredient_one, ingredient_two, count FROM CrossCount;";
			ResultSet rs4 = stmt.executeQuery(sqltxt4);
			while (rs4.next()) {
				CrossCountIdHolder ccih = new CrossCountIdHolder();
				ccih.setId(rs4.getString(1));
				ccih.setIngredient_one(rs4.getString(2));
				ccih.setIngredient_two(rs4.getString(3));
				ccih.setCount(rs4.getString(4));
			}
			for(CrossCountIdHolder ccih1 : ccihList){
				
				for(CrossCountIdHolder ccih2 : ccihList){
					
					if(ccih1.getIngredient_one().equals(ccih2.getIngredient_two()) && ccih1.getIngredient_two().equals(ccih2.getIngredient_one())){
						int newCount = Integer.parseInt(ccih1.getCount()) + Integer.parseInt(ccih2.getCount());
						ccih1.setCount(""+newCount);
						
						PreparedStatement stmt5 = conn.prepareStatement(
								"UPDATE crosscount SET count = "+newCount+" WHERE id = "+ccih1.getCount()+";");
						stmt5.executeUpdate();
						
						PreparedStatement stmt6 = conn.prepareStatement(
								"DELETE FROM crosscount where id = "+ccih2.getId()+";");
						stmt6.executeUpdate();
						System.out.println("double block: "+ccih1.getId()+", "+ccih2.getId());
					}
					
					
				}
				
				ccihList.remove(ccih1);
			}
			

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
