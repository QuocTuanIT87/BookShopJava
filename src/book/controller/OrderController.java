package book.controller;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import book.dao.AccountDAO;
import book.dao.CartDAO;
import book.dao.DiscountDAO;
import book.dao.OrderDAO;
import book.entity.CartDetail;
import book.entity.Discount;
import book.entity.Order;
import book.entity.OrderDetail;
import book.entity.OrderDetailKey;
import book.entity.User;

@Controller
public class OrderController {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private DiscountDAO discountDAO;

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

	@RequestMapping("order-check")
	public String orderCheck(HttpServletRequest request, Model model) {

		Cookie isUser = this.read(request, "email");
		User user = accountDAO.findByEmail(isUser.getValue());
		model.addAttribute("user", user);

		List<CartDetail> cart = cartDAO.getCartOfUser(user);

		if (cart.size() == 0) {
			return "pages/cart-detail";
		}

		model.addAttribute("cart", cart);

		List<Discount> listDiscounts = discountDAO.getListDiscounts();
		model.addAttribute("listDiscounts", listDiscounts);

		return "pages/order-check";
	}

	@RequestMapping("my-order")
	public String getMyOrder(HttpServletRequest request, Model model) {

		Cookie isUser = this.read(request, "email");
		User user = accountDAO.findByEmail(isUser.getValue());

		List<Order> myOrder = orderDAO.getOrderOfUser(user);
		model.addAttribute("orders", myOrder);
		model.addAttribute("pageNumber", 0);

		return "user/profile";
	}

	@RequestMapping("confirm-payment")
	public String completeOrder(HttpServletRequest request, Model model) {
		Cookie isUser = this.read(request, "email");
		User user = accountDAO.findByEmail(isUser.getValue());

		if (user.getAddress() == null && user.getPhoneNumber() == null) {
			model.addAttribute("messAddress", "Please fill in your delivery address and phone number!");

			List<CartDetail> cart = cartDAO.getCartOfUser(user);
			model.addAttribute("cart", cart);
			List<Discount> listDiscounts = discountDAO.getListDiscounts();
			model.addAttribute("listDiscounts", listDiscounts);
			model.addAttribute("user", user);
			return "pages/order-check";
		} else if (user.getAddress() == null) {
			model.addAttribute("messAddress", "Please fill in your delivery address");
			return "pages/order-check";
		} else if (user.getPhoneNumber() == null) {
			model.addAttribute("messPhone", "Please fill in your phone number");
			return "pages/order-check";
		}

		Order newOrder = new Order();
		// Lấy ngày hiện tại
		Date currentDate = new Date();
		newOrder.setStatus(0);
		newOrder.setCreateAt(currentDate);
		newOrder.setUser(user);
		newOrder.setAddress(user.getAddress());
		newOrder.setPhoneNumber(user.getPhoneNumber());
		newOrder.setName(user.getName());

		String total = request.getParameter("total");

		newOrder.setSumMoney(Float.parseFloat(total));

		CartDetail cartDetail = cartDAO.getOneItemCart(user);
		newOrder.setDiscountPercent(cartDetail.getDiscountPercent());

		orderDAO.createOrder(newOrder);

		List<CartDetail> cart = cartDAO.getCartOfUser(user);

		for (CartDetail c : cart) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderDetailId(new OrderDetailKey(c.getBook().getIdBook(), newOrder.getIdOrder()));
			orderDetail.setQuantity(c.getQuantity());

			orderDAO.createOrderDetail(orderDetail);
		}

		cartDAO.deleteCart(user.getIdUser());

		return "redirect:/home.htm";
	}

}
