package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import context.DBContext;
import model.Account;

public class AccountDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//func trả về acccount nếu tồn tại trong database
	public Account login(String user, String pass) {
		String query = "SELECT * FROM dbo.Account\n" 
						+ "WHERE user_mail = ?\n" 
						+ "AND password = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();

			while (rs.next()) {
				return new Account(rs.getString(1), 
									rs.getString(2), 
									rs.getInt(3), 
									rs.getString(4), 
									rs.getString(5), 
									rs.getString(6), 
									0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// func thực hiện việc đang ký new acc khi dữ liệu nhập phù hợp
	public void register(String usr, String pwd, int role, String name, String address, String phone) {
		String query = "INSERT INTO dbo.Account\n"
						+ "VALUES (?,?,?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, usr);
			ps.setString(2, pwd);
			ps.setInt(3, role);
			ps.setString(4, name);
			ps.setString(5, address);
			ps.setString(6, phone);
			
			ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//func kiểm tra tồn tại của acc thông qua usermail
	public Account checkAccountExist(String user) {
		String query = "SELECT * FROM dbo.Account\n" 
						+ "WHERE user_mail = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			rs = ps.executeQuery();

			while (rs.next()) {
				return new Account(rs.getString(1), 
									rs.getString(2), 
									rs.getInt(3), 
									rs.getString(4), 
									rs.getString(5), 
									rs.getString(6), 
									0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Test
	public static void main(String[] args) {
		AccountDAO dao = new AccountDAO();
		Account acc = new Account();

		acc = dao.login("duongdt@fpt.com.vn", "123");

		System.out.println(acc.getCheck());
	}

}
