package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Cart;
import model.Orders;
import model.Orders_detail;
import model.Product;

public class OrdersDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//insert information of Order to data source, that including list of products in cart (c) and information of buyer in Orders o
	public void insertOrder(Orders o, Cart c) {		
		// insert db order
		String queryOrder = "INSERT INTO Orders(user_mail, order_totalprice, order_address)\n"
							+ "VALUES (?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(queryOrder);
			ps.setString(1, o.getUserMail());
			ps.setFloat(2, (float) c.getTotalAmount());
			ps.setString(3, o.getAddress());
			
			ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Lưu ý đối với trường hợp có nhiều order cùng 1 lúc thì cách này -> ko được
		// cần nâng cấp chỗ này
		String queryNewOrderID = "SELECT MAX(order_id) FROM Orders";
		int newOrdersID = 0;
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(queryNewOrderID);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				newOrdersID = Integer.parseInt(rs.getString(1));
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		System.out.println("new orderID: " +newOrdersID);
//		for (Product product : c.getItems()) {
//			System.out.println("Name cua sp: " + product.getName());				
//		}
		
		// insert db order_detail		
		String queryOrderdetail = "INSERT INTO Orders_detail(order_id, product_id, product_name, product_img_source, product_quatity, product_price)\n"
								+ "VALUES (?,?,?,?,?,?)";		
		try {
			conn = new DBContext().getConnection();			
			ps = conn.prepareStatement(queryOrderdetail);
			
			for (Product product : c.getItems()) {
				ps.setInt(1, newOrdersID);
				ps.setInt(2, product.getId());
				ps.setString(3, product.getName());
				ps.setString(4, product.getSrc());
				ps.setInt(5, product.getNumber());
				ps.setFloat(6, product.getPrice());				
				
				ps.executeUpdate();				
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	// func in ra list sp đã order lấy từ db
	public List<Orders_detail> getOrdered(String usermail) throws Exception {		
		List<Orders_detail> list = new ArrayList<>();
		List<Integer> listOrderID = new ArrayList<>();
		
		String queryOrderID = "SELECT order_id FROM Orders\n"
							+ "WHERE user_mail='" + usermail + "'";
		try {			
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(queryOrderID);
			rs = ps.executeQuery();
			while (rs.next()) {
				listOrderID.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Integer id : listOrderID) {
			String query = "SELECT* FROM dbo.Orders_detail\r\n"
					+ "WHERE order_id=" + id;			
			try {			
				conn = new DBContext().getConnection();
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();

				while (rs.next()) {
					list.add(new Orders_detail(rs.getInt(1),
												rs.getInt(2),
												rs.getString(3),
												rs.getString(4),
												rs.getInt(5),
												rs.getFloat(6)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	return list;
	}
}
