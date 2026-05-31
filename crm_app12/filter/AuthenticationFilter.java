package crm_app12.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "authenticationFilter", urlPatterns = {"/user", "/user-add"})
public class AuthenticationFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		Cookie[] cookies = req.getCookies();
		String role = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("role")) {
					role = cookie.getValue();
				}
			} 
		}
		
		if (role != null) {
			String path = req.getServletPath();
					
			if ("/user-add".equals(path) && "ROLE_ADMIN".equals(role)) {
				chain.doFilter(req, res);
			} else if ("/user".equals(path)) {
				chain.doFilter(req, res);
			} else {
				res.sendRedirect(req.getContextPath() + "/login");
			}
			
		} else {
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}
	
}
