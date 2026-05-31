package crm_app12.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import crm_app12.entity.UserEntity;
import crm_app12.repository.UserRepository;

public class LoginServices {

	private UserRepository userRepository = new UserRepository();
	
	public String checkLogin(String email, String password, String remember, HttpServletResponse resp) {
		String message = "Đăng nhập thất bại !";
		List<UserEntity> listUserEntities = userRepository.findByEmailAndPassword(email, password);
		if (listUserEntities.size() > 0) {
			// Kiểm tra có check vào checkbox Ghi Nhớ Tài Khoản hay không
			if (remember != null) {
				// Có check
				// Tạo cookie
				Cookie cEmail = new Cookie("email", email);
				Cookie cPassword = new Cookie("password", password);
				
				resp.addCookie(cEmail);
				resp.addCookie(cPassword);
			}
			message = "Đăng nhập thành công!";
			Cookie cRole = new Cookie("role", listUserEntities.get(0).getRoleName());
			
			resp.addCookie(cRole);
			
			try {
				
				resp.sendRedirect("/crm_app12/user");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return message;
	}
}
