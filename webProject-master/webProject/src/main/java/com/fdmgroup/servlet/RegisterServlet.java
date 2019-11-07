package com.fdmgroup.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.LoginDao;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("userName");
		String id = request.getParameter("userId");
		String pass = request.getParameter("userPass");

		if (LoginDao.validateId(id)) {
			out.print("User ID exists.");
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.include(request, response);
		} else {
			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "trainee1",
						"!QAZSE4");

				PreparedStatement ps = con.prepareStatement("insert into admin values(?,?,?)");

				ps.setString(1, name);
				ps.setString(2, id);
				ps.setString(3, pass);

				int i = ps.executeUpdate();
				if (i > 0)
					out.print("You are successfully registered...");

			} catch (Exception e2) {
				System.out.println(e2);
			}

			out.close();
		}
	}

}
