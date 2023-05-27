package controller;


import model.Cart;
import model.Orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;

/**
 * Servlet implementation class PayController
 */
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			if (session.getAttribute("cart") == null) {
			}
			OrdersDAO dao = new OrdersDAO();
			Cart c = (Cart) session.getAttribute("cart");
			String usermail = request.getParameter("usermail");
			String address = request.getParameter("address");
			Orders o = new Orders(usermail, 2, "", address, "", null);
			dao.insertOrder(o, c);
			// xóa sp trong cart
			session.removeAttribute("cart");
			
			response.sendRedirect("home");
		} catch (Exception ex) {
			response.getWriter().println(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
}
