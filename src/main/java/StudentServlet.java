

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Servlet implementation class StudehtServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

	public StudentServlet() {
		this.studentDAO = new StudentDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				try {
					insertStudent(request, response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/delete":
				try {
					deleteStudent(request, response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/edit":
				try {
					showEditForm(request, response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/update":
				try {
					updateStudent(request, response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				try {
					showAllStudents(request, response);
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("student-form.jsp");
		rd.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentDAO.selectStudent(id);
		RequestDispatcher rd = request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("student", student);
		rd.forward(request, response);
	}
	
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pswd = request.getParameter("password");
		Student student = new Student(id, fname, lname, pswd);
		studentDAO.insertStudent(student);
		response.sendRedirect("all-students");
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDAO.deleteStudent(id);
		response.sendRedirect("all-students");
	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pswd = request.getParameter("password");
		Student student = new Student(id, fname, lname, pswd);
		studentDAO.updateStudent(student);
		response.sendRedirect("all-students");
	}
	
	private void showAllStudents(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		List<Student> students = studentDAO.selectAllStudents();
		RequestDispatcher rd = request.getRequestDispatcher("student-list.jsp");
		request.setAttribute("students", students);
		rd.forward(request, response);
	}
}
