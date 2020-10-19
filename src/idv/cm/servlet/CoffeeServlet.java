package idv.cm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import idv.cm.db.CoffeeDAO;
import idv.cm.db.CoffeeVO;
import idv.cm.db.ConnectionFactory;

/**
 * Servlet implementation class CoffeeServlet
 */
@WebServlet("/CoffeeServlet")
public class CoffeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String type = request.getParameter("action");
		request.getServletContext().log("Servlet type = " + type);
		HashSet<CoffeeVO> set = null;
		HttpSession session = request.getSession();
		switch (type) {
		case "search":
			set = searchDb(request, response);
			session.setAttribute("refresh", set);
			break;
			
		case "edit":
			int count=0;
			count = editDb(request, response);
			session.setAttribute("update", count);
			session.removeAttribute("refresh");
			break;
		default:
			request.getServletContext().log("Servlet NOT READY");
			session.removeAttribute("refresh");
			session.removeAttribute("update");
		}

		response.sendRedirect(request.getContextPath() + "/index.jsp");
		// sent back to jsp (session)
//		HttpSession session = request.getSession();
//		session.setAttribute("refresh", set);
//		response.sendRedirect(request.getContextPath() + "/index.jsp");
		//sent back to jsp (forward)
		//RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		//rd.forward(request, response);
		/*
		 * Enumeration<String> list = request.getAttributeNames(); Hashtable<String,
		 * String> table = new Hashtable<>(); while (list.hasMoreElements()) { String
		 * key = list.nextElement(); String value = request.getParameter(key);
		 * table.put(key, value); }
		 */

	}

	private int editDb(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		try {
			con = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// get set from DB
		CoffeeDAO dao = new CoffeeDAO();
		HashSet<CoffeeVO> set = searchDb(request,response);
		int result = dao.updateAll(con, set);
		request.getServletContext().log("update count = "+result);
		return result;
	}

	private HashSet<CoffeeVO> searchDb(HttpServletRequest request, HttpServletResponse response) {
		// check Connection
		Connection con = null;
		try {
			con = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// get set from DB
		CoffeeDAO dao = new CoffeeDAO();
		return dao.findAll(con);

	}

}
