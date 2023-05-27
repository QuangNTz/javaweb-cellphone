package controller.product;


import model.Product;

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

import dao.ListProductDAO;

/**
 * Servlet implementation class ListController
 */
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListController() {
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
		HttpSession session = request.getSession(true);

		try (PrintWriter out = response.getWriter()) {
			String page = request.getParameter("page");
			ListProductDAO productDAO = new ListProductDAO();
			//set khởi gán cho page_num
			int page_num;			
			if (page == "") {
				page_num = (int) session.getAttribute("page_active");
			} else if (page == null) {
				page_num = 1;
			} else {
				page_num = Integer.parseInt(page);
			}			
			System.out.println(page_num);			
			// số product/1trang -> cài= web.xml
			int numPerPage = Integer.parseInt(getServletContext().getInitParameter("ItemPerPage"));			
			List<Product> list = productDAO.searchPerPageList("", page_num, numPerPage);
			// đếm sl product trong db
			int numProduct = productDAO.countProductInDatabase("");
			//số của trang kết thúc
			int endPage = (int) Math.ceil((float)numProduct/numPerPage);
			
			//set list vào products, endPage vào endP để show lên home.jsp
			session.setAttribute("products", list);
			session.setAttribute("endP", endPage);
			// gán active page trong trang home.jsp
			session.setAttribute("page_active", page_num);
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/html; charset=UTF-8");		

	}
	

}
