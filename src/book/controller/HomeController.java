package book.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import book.dao.AccountDAO;
import book.dao.BookDAO;
import book.dao.CategoryDAO;
import book.dao.OrderDAO;
import book.entity.Book;
import book.entity.Category;
import book.entity.User;

@Controller
public class HomeController {
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private OrderDAO orderDAO;

//	Read Cookies
	public Cookie read(HttpServletRequest request, String name) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					String decodedvalue = new String(Base64.getDecoder().decode(cookie.getValue()));
					cookie.setValue(decodedvalue);
					return cookie;
				}
			}
		}
		return null;
	}

	@RequestMapping(value = { "home", "/" })
	public String homePage(HttpServletRequest request, HttpSession session, ModelMap model) {

		Cookie isUser = this.read(request, "email");

		List<Category> listCategorys = categoryDAO.getListCategorys();
		model.addAttribute("listCategorys", listCategorys);
		
		List<Book> listBooks = bookDAO.getListBooks();
		model.addAttribute("listBooks", listBooks);
		
		if (isUser != null) {
			User user = accountDAO.findByEmail(isUser.getValue());
			
			session.setAttribute("currentUser", user);
			
			if (user.getRole() == 0) {
				return "pages/home";
			} else {
				float totalRevenuev = orderDAO.getTotalRevenuev();
				model.addAttribute("totalRevenuev", totalRevenuev);
				
				int totalProductSold = orderDAO.getTotalProductSold();
				model.addAttribute("totalProductSold", totalProductSold);
				
				int totalUser = accountDAO.getTotalUser();
				model.addAttribute("totalUser", totalUser);
				
				return "admin/home";
			}

		}
		return "pages/home";

	}

}
