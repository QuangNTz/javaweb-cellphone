package controller.product;


import model.Account;
import model.Orders_detail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;

/**
 * Servlet implementation class OrderedController
 */
public class OrderedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderedController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute("login_user");
		
		try (PrintWriter out = response.getWriter()) {					
			OrdersDAO ordersDAO = new OrdersDAO();			
			List<Orders_detail> listOrdered = ordersDAO.getOrdered(acc.getUsr());
						
			//set listOrdered vào listOrdered,
			request.setAttribute("listOrdered", listOrdered);
			
			RequestDispatcher rd = request.getRequestDispatcher("ordered.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
