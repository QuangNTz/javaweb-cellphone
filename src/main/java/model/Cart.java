package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> items;//list of item in cart
	
	public Cart() {
		items = new ArrayList<>();
	}	

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	//add e new product to cart
	public void add(Product ci) {
		for (Product x : items) {
			// if product exist -> set num = num +1
			if (ci.getId() == x.getId()) {
				x.setNumber(x.getNumber() + 1);
				return;
			}
		}
		// if product not exist -> add this product to list items
		items.add(ci);
	}
	
	//remove a product from cart
	public void remove(int id) {
		for (Product x : items) {
			if (x.getId() == id) {
				items.remove(x);
				return;
			}
		}
	}
	
	//return total amount of cart
	public double getTotalAmount() {
		double s = 0;
		for (Product x : items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s * 100.0) / 100.0;
	}
	
		
}
