package controller;


import model.Account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8"); // vietnamese
		// make sure that email is valid
		String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String regex = "[a-zA-Z0-9_!@#$%^&*]+";

		// collect data from a register form
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		// Kiểm tra sự phù hợp của dữ liệu nhập vào form
		if (!pwd.matches(regex) || !usr.matches(regexMail)) {//-> register.jsp again nếu user&pass chưa phù hợp cú pháp
			request.setAttribute("mess", "invalid syntax User email or password");

			request.setAttribute("usr", usr);
			request.setAttribute("pwd", pwd);
			request.setAttribute("role", role);
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("phone", phone);

			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}
		
		// check usr đã tồn tại trong db chưa
		Account acc = new AccountDAO().checkAccountExist(usr);
		
		if (acc == null) {// acc ko tồn tại -> cho phép register new account, ghi vào database 
			//Sign up
			AccountDAO dao = new AccountDAO();
			dao.register(usr, pwd, Integer.parseInt(role), name, address, phone);
			//-> redirect to Servlet:home(ListController) -> (dlieu -> home.jsp)
			response.sendRedirect("login");
			
		} else {// acc đã tồn tại trong database -> báo lỗi, register lại
			//register again
			request.setAttribute("mess", "Account already exist");

			request.setAttribute("usr", usr);
			request.setAttribute("pwd", pwd);
			request.setAttribute("role",role);
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("phone", phone);
			
			// quay lại trang register.jsp để đăng kí
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}
	}
}
