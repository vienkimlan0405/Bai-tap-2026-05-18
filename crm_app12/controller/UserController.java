package crm_app12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.entity.RoleEntity;
import crm_app12.entity.UserEntity;
import crm_app12.services.UserServices;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user-add"})
public class UserController extends HttpServlet{
	
	private UserServices userServices = new UserServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Lấy đường dẫn servet mà client gọi
		String path = req.getServletPath();
		if (path.equals("/user")) {
			List<UserEntity> listUserEntities = userServices.getAll();
			
			req.setAttribute("listUsers", listUserEntities);
			
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else if (path.equals("/user-add")) {
			
			List<RoleEntity> listRoleEntities = userServices.getAllRoles();
			
			req.setAttribute("listRoles", listRoleEntities);
			
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/user")) {
			
		} else if (path.equals("/user-add")) {
			UserEntity userEntity = new UserEntity();
			userEntity.setFullname(req.getParameter("fullname"));
			userEntity.setEmail(req.getParameter("email"));
			userEntity.setPassword(req.getParameter("password"));
			userEntity.setPhone(req.getParameter("phone"));
			userEntity.setRoleId(req.getParameter("role"));
			
			
			req.getRequestDispatcher("");
			
			/**
			 * 
			 * Giải tiếp tính năng thêm user đang còn giang giở
			 */
		}
	}
}
