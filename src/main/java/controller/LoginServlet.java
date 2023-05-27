package controller;


import model.Account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;

/**
 * Servlet implementation class LoginServler
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ä�á»�c cookie
		Cookie arr[] = request.getCookies();
		if (arr != null) {
			for (Cookie co : arr) {
				// Set thuộc tính cho usermail form trang login.jsp
				if (co.getName().equals("userC")) {
					request.setAttribute("user_cookie", co.getValue());
				}
			}			
		}
		// Chuyển tới trang login.jsp
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8"); // vietnamese		
		// make sure that email is valid
		String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		String regexPwd = "[a-zA-Z0-9_!@#$%^&*]+";

		// collect data from a login form
		String usermail = request.getParameter("usermail");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		// Kiểm tra sự phù hợp cú pháp của dữ liệu nhập vào login
		if (!usermail.matches(regexMail) || !password.matches(regexPwd)) {
			// Nếu input ko thỏa regex
			request.setAttribute("mess", "invalid syntax");			
			request.setAttribute("user_cookie", usermail);
			// forward login.jsp
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		
		Account acc = new AccountDAO().login(usermail, password);		
		if (acc == null) {// acc ko tồn tại -> login lại
			request.setAttribute("mess", "wrong username or password");
			request.setAttribute("user_cookie", usermail);

			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else {// acc đã tồn tại -> home Servlet
			// set session	
			HttpSession session = request.getSession();
			
			session.setAttribute("login_user", acc);
			session.setAttribute("welcomeToUser", "Welcome " + acc.getName());
			
			// set cookie
			if (remember != null) {
				// luu cookie if Remember me
				Cookie ck = new Cookie("userC", usermail);
				ck.setMaxAge(180);

				response.addCookie(ck);
			}
			// login is valid, now redirect to Servlet:home(ListController) -> (dlieu -> home.jsp) 
			response.sendRedirect("home?page");
		}
	}
}
