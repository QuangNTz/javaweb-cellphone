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

import dao.ListProductDAO;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Search sản phẩm theo tên nhập vào từ ô tìm kiếm
		response.setContentType("text/html; charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			String name = request.getParameter("search");
			String page = request.getParameter("page");
			ListProductDAO productDAO = new ListProductDAO();
			// set khởi gán cho page_num = 1
			int page_num = (page == null) ? 1 : Integer.parseInt(page);
			// số product/1trang -> cài= web.xml
			int numPerPage = Integer.parseInt(getServletContext().getInitParameter("ItemPerPage"));
			List<Product> list = productDAO.searchPerPageList(name, page_num, numPerPage);
			int numProduct = productDAO.countProductInDatabase(name);
			// số của trang kết thúc
			int endPage = (int) Math.ceil((float) numProduct / numPerPage);
			
			if (list == null) {
				request.setAttribute("mess", "Can not find any product");
				request.setAttribute("nameS", name);// giá trị value tạm trong ô search
				
				RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);				
			} else {
				// set list vào products_search,
				// endPage vào endP_search,
				// page_num vào page_search_active để show lên search.jsp
				request.setAttribute("products_search", list);
				request.setAttribute("endP_search", endPage);
				request.setAttribute("page_search_active", page_num);
				request.setAttribute("nameS", name);// giá trị value tạm trong ô search
				
				RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);
			}

		} catch (Exception ex) {
			Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
