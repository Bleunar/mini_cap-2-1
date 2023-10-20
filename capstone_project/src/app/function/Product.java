package app.function;

public class Product {
	private String productName;
	private int productId;
	private double productPrice;
	private int productQuantity;
	Product(){
		
	}
	
	//Setters
	void setProductDetails(int id, String name, double price, int quantity){
		this.productId = id;
		this.productName = name;
		this.productPrice = price;
		this.productQuantity = quantity;
	}
	
	//Getters
	int getProductId() {
		return this.productId;
	}
	String getProductName() {
		return this.productName;
	}
	double getProductPrice() {
		return this.productPrice;
	}
	int getProductQuantity() {
		return this.productQuantity;
	}
}
