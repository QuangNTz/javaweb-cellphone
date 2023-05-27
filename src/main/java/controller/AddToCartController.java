package controller;


import model.Cart;
import model.Product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;

/**
 * Servlet implementation class AddToCartController
 */
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartController() {
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
		
		try {
			HttpSession session = request.getSession(true);
			String idd = request.getParameter("id");
			String action = request.getParameter("action");
			
			if (action != null && action.equalsIgnoreCase("add")) {
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
				}
				int id = Integer.parseInt(idd);
				Product p = new ListProductDAO().getProduct("" + id);
				Cart c = (Cart) session.getAttribute("cart");
				
				c.add(new Product(p.getId(), 
						p.getName(), 
						p.getDescription(), 
						p.getPrice(), 
						p.getSrc(), 
						p.getType(), 
						p.getBrand(), 
						1));						
			} else if (action != null && action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(idd);
				Cart c = (Cart) session.getAttribute("cart");
				c.remove(id);
			}
			
			response.sendRedirect("cart.jsp");
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
