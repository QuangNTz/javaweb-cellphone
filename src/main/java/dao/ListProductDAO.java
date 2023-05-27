package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

public class ListProductDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// return the list of products by product_name
	public List<Product> search(String characters) throws Exception {
		List<Product> list = new ArrayList<>();
		String query = "SELECT * FROM Products\n" 
						+ "WHERE product_name LIKE ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + characters + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Product(rs.getInt(1), 
									rs.getString(2), 
									rs.getString(3), 
									rs.getFloat(4), 
									rs.getString(5),
									rs.getString(6), 
									rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;		
	}

	// get the product by product_id -> show productInfo
	public Product getProduct(String id) throws Exception {
		String query = "SELECT * FROM products\n" 
					+ "WHERE product_id = ?";		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				return new Product(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6), 
						rs.getString(7));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	// đếm số lượng sp có trong database
	public int countProductInDatabase(String characters) throws Exception {
		String query = "SELECT COUNT(*)\n"
						+ "FROM Products\n"
						+ "WHERE product_name LIKE ?";		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + characters + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return 0;		
	}
	
	// return the list of products by product_name / page số (trang 1 -> index=0 ) / số sp trong 1 page
	public List<Product> searchPerPageList(String characters, int page_num, int numPerPage) {
		List<Product> list = new ArrayList<>();
		String query ="SELECT * FROM dbo.Products\n"
						+ "WHERE product_name LIKE ?\n"
						+ "ORDER BY product_id\n"
						+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + characters + "%");
			ps.setInt(2, (page_num-1)*numPerPage);
			ps.setInt(3, numPerPage);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getFloat(4), 
						rs.getString(5),
						rs.getString(6), 
						rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		if (list.size()>0) {
			return list;
		} else {
			return null;
		}	
	}	
	
	// Test kết quả
	public static void main(String[] args) throws Exception {		
		ListProductDAO dao = new ListProductDAO();		
		//
		List<Product> list = dao.search("");
		for (Product o : list) {
			System.out.println("id: " + o.getId());
		}
		System.out.println();
		//
		Product p = new Product();
		p = dao.getProduct("1");		
		System.out.println("Des id=1: " + p.getDescription());
		System.out.println();
		//
		int count = dao.countProductInDatabase("");
		System.out.println("Sl product trong database " + count);
		System.out.println();
		//
		int a = 3;		
		int page = (int) Math.ceil((float)count/a);
		
		System.out.println("sl page " + page);
		System.out.println();
	}

}
