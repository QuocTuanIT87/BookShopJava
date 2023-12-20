package book.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import book.dao.OrderDAO;
import book.entity.Order;

@Controller
public class OrderAdminController {
	@Autowired
	private OrderDAO orderDAO;

	@RequestMapping("listorder")
	public String getListOrders(ModelMap model) {
		List<Order> listOrders = orderDAO.getListOrders();
		model.addAttribute("listOrders", listOrders);
		return "admin/listorder";
	}

	@RequestMapping("admin/confirm-order")
	public String confirmOrder(HttpServletRequest request) {
		String idOrder = request.getParameter("id");
		Order order = orderDAO.getOrderById(Integer.parseInt(idOrder));
	
		order.setStatus(1);
		orderDAO.updateOrder(order);

		return "redirect:/listorder.htm";
	}
	
	
	@RequestMapping("admin/success-delivery")
	public String successDelivery(HttpServletRequest request) {
		String idOrder = request.getParameter("id");
		Order order = orderDAO.getOrderById(Integer.parseInt(idOrder));
	
		order.setStatus(2);
		orderDAO.updateOrder(order);

		return "redirect:/listorder.htm";
	}
	
	
	@RequestMapping("admin/cancel-order")
	public String cancelOrder(HttpServletRequest request) {
		String idOrder = request.getParameter("id");
		Order order = orderDAO.getOrderById(Integer.parseInt(idOrder));
	
		order.setStatus(3);
		orderDAO.updateOrder(order);

		return "redirect:/listorder.htm";
	}
}
