package dbSplicers;

public class CrossCountIdHolder {
	
	private String id;
	private String ingredient_one;
	private String ingredient_two;
	private String count;
	
	public CrossCountIdHolder(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIngredient_one() {
		return ingredient_one;
	}

	public void setIngredient_one(String ingredient_one) {
		this.ingredient_one = ingredient_one;
	}

	public String getIngredient_two() {
		return ingredient_two;
	}

	public void setIngredient_two(String ingredient_two) {
		this.ingredient_two = ingredient_two;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CrossCountIdHolder [id=" + id + ", ingredient_one=" + ingredient_one + ", ingredient_two="
				+ ingredient_two + ", count=" + count + "]";
	}
	
	

}
