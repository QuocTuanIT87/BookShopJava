package book.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import book.dao.AccountDAO;
import book.entity.User;

public class UserInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	AccountDAO accountDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("account");
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/home.htm");
			return false;
		}

		return true;
	}
}
