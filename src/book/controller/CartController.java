package book.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import book.dao.AccountDAO;
import book.dao.BookDAO;
import book.dao.CartDAO;
import book.entity.Book;
import book.entity.CartDetail;
import book.entity.CartDetailKey;
import book.entity.OrderDetail;
import book.entity.OrderDetailKey;
import book.entity.User;

@Controller
public class CartController {

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private AccountDAO accountDAO;

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

	@RequestMapping("update-item-quantity")
	public String getBackBookDetial(HttpServletRequest request, Model model) {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Cookie cookies = this.read(request, "email");
		User user = accountDAO.findByEmail(cookies.getValue());
		String idBook = request.getParameter("id");
		Book book = bookDAO.getBookById(Integer.parseInt(idBook));
		if (quantity > 0 && quantity <= book.getQuantity()) {
			CartDetail oldCart = cartDAO.checkCartExist(book, user);

			oldCart.setQuantity(quantity);
			cartDAO.updateCart(oldCart);
		}

		List<CartDetail> cart = cartDAO.getCartOfUser(user);
		model.addAttribute("cart", cart);

		return "pages/cart-detail";
	}

	@RequestMapping("remove-item-cart")
	public String deleteItemCart(HttpServletRequest request, Model model) {
		Cookie cookies = this.read(request, "email");
		User user = accountDAO.findByEmail(cookies.getValue());
		String idBook = request.getParameter("id");
		Book book = bookDAO.getBookById(Integer.parseInt(idBook));
		CartDetail oldCart = cartDAO.checkCartExist(book, user);
		cartDAO.deleteItemCart(oldCart);

		List<CartDetail> cart = cartDAO.getCartOfUser(user);
		model.addAttribute("cart", cart);
		return "pages/cart-detail";
	}

	@RequestMapping(value = "add-cart", method = RequestMethod.POST)
	public String checkAccount(HttpServletRequest request, @RequestParam("quantity") int quantity, Model model) {
		Cookie cookies = this.read(request, "email");

		if (cookies == null) {
			return "user/login";
		} else {

			CartDetail newCart = new CartDetail();
			User user = accountDAO.findByEmail(cookies.getValue());
			String idBook = request.getParameter("id");
			Book book = bookDAO.getBookById(Integer.parseInt(idBook));

			boolean success = false;

			CartDetail oldCart = cartDAO.checkCartExist(book, user);
			if (oldCart != null) {
				int newQuantity = oldCart.getQuantity() + quantity;
				oldCart.setQuantity(newQuantity);
				success = cartDAO.updateCart(oldCart);
			} else {
				newCart.setCartDetailId(new CartDetailKey(book.getIdBook(), user.getIdUser()));
				newCart.setQuantity(quantity);
				newCart.setDiscountPercent(0);
				success = cartDAO.addCart(newCart);
			}

			if (success) {
				model.addAttribute("successMessage", "Account registration successful!");
			} else {
				model.addAttribute("failedMessage", "An error occurred, please try again later!");
			}
		
			model.addAttribute("book", book);
			return "pages/book-detail";
		}
	}

	@RequestMapping("cart-detail")
	public String getCartDetail(HttpServletRequest request, Model model) {
		Cookie cookies = this.read(request, "email");

		if (cookies == null) {
			return "user/login";
		}

		User user = accountDAO.findByEmail(cookies.getValue());
		List<CartDetail> cart = cartDAO.getCartOfUser(user);

		model.addAttribute("cart", cart);

		return "pages/cart-detail";

	}

	@RequestMapping("select-promotion")
	public String applyPromotion(HttpServletRequest request, Model model, @RequestParam("percent") int percent) {
		Cookie cookies = this.read(request, "email");
		
		if (cookies == null) {
			return "user/login";
		}

		User user = accountDAO.findByEmail(cookies.getValue());
		List<CartDetail> cart = cartDAO.getCartOfUser(user);

		for (CartDetail c : cart) {
			c.setDiscountPercent(percent);
			cartDAO.updateCart(c);
		}

		return "redirect:/order-check.htm";
	}
}
