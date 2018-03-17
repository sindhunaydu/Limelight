package pages;

public class Item{
	String name;
	double price;
	double score;

	public Item(String name, double price, double score) {
		super();
		this.name = name;
		this.price = price;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}