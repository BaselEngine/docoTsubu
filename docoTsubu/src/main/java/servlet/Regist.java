package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegistLogic;
import model.User;

@WebServlet("/Regist")
public class Regist extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/Regist.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String checkPass = request.getParameter("checkPass");
		
		User user = new User(name, pass, checkPass);
		
		RegistLogic registLogic = new RegistLogic();
		boolean isRegist = registLogic.execute(user);
		
		if(isRegist) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		
		RequestDispatcher dispacher = 
				request.getRequestDispatcher("/WEB-INF/jsp/RegistResult.jsp");
		dispacher.forward(request, response);
	}
	
}