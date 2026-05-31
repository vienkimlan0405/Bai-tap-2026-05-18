package crm_app12.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Khi user muốn vào trang user-add: thì user phải có quyền ADMIN
 * Khi user muốn vào trang hiển thị danh sách user: Thì user đó phải đăng nhập
 * 
 * - Trong trường hợp user không đăng nhập và không có quyền tương ứng thì sẽ đá ra trang login
 * 
 * Giải pháp:
 * - Tạo ra một cookie lưu lại tên quyển của user khi user đăng nhập thành công
 * - Dùng filter lấy ra cookie ad94 lưu tên quyền trước đó
 * - Kiểm tra giá trị của cookie lưu tên quyền xem có giá trị hay không
 * - Nếu có thì <=> đăng nhập thành công, nếu không thì xem như user chưa có đăng nhập
 */

@WebFilter(filterName = "demoFilter", urlPatterns = {"/user"})
public class DemoFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		
		System.out.println("Kiemtra filter");
			
		// Cho đi tiếp
		chain.doFilter(req, res);
	}
	
}
