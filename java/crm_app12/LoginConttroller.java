package crm_app12;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.services.LoginServices;


@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginConttroller extends HttpServlet{

	private LoginServices loginServices = new LoginServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Lấy hết toàn bộ cookie client gửi lên
		Cookie[] cookies = req.getCookies();
		String email = "";
		String password = "";
		
		// Duyệt qua từng cookie và kiếm cookie có tên ứng với tên đã đặt trước đó để lấy giá trị
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("email")) {
				email = cookie.getValue();
			}
			
			if (cookie.getName().equals("password")) {
				password = cookie.getValue();
			}
		}
		
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember-me");
		
		String message = loginServices.checkLogin(email, password, remember, resp);
		
		System.out.println(message);
			
	}
}
