package model;

public class Orders_detail {
	
	private int orderId;
	private int productId;
	private String nameProduct;
	private String src;//location of image of product
	private int quatity;// sluong product cung loai
	private float price;
	
	public Orders_detail() {
		
	}

	public Orders_detail(int orderId, int productId, String nameProduct, String src, int quatity, float price) {
		this.orderId = orderId;
		this.productId = productId;
		this.nameProduct = nameProduct;
		this.src = src;
		this.quatity = quatity;
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}		
}
