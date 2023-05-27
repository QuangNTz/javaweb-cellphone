package model;

import java.util.Date;
import java.util.List;

public class Orders {

	private String userMail;// buyer's email
	private int orderID;
	private float totalPrice;// total amount of order
	private int status;
	private Date orderDate;
	private String discount;
	private String address;// buyer's address
	private String phoneNumber;
	private Date receivedDate;
	private List<Orders_detail> lp;

	public Orders() {

	}

	public Orders(int orderID, float totalPrice, int status, Date orderDate, String address, String phoneNumber,
			List<Orders_detail> lp, String userMail, Date receivedDate) {
		this.orderID = orderID;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.lp = lp;
		this.userMail = userMail;
		this.receivedDate = receivedDate;
	}

	public Orders(String userMail, int status, String discount, String address, String phoneNumber, Date receivedDate) {
		this.userMail = userMail;
		this.status = status;
		this.discount = discount;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.receivedDate = receivedDate;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public float getPrice() {
		return totalPrice;
	}

	public void setPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Orders_detail> getLp() {
		return lp;
	}

	public void setLp(List<Orders_detail> lp) {
		this.lp = lp;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
}
