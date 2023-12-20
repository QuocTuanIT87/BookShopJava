package book.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import book.dao.AccountDAO;
import book.dao.PublisherDAO;
import book.entity.Publisher;
import book.entity.User;

@Controller
public class UserAdminController {
	@Autowired
	private AccountDAO accountDAO;
	
	@RequestMapping("listuser")
	public String registerAccount(ModelMap model) {
		List<User> listUsers = accountDAO.getListUsers();
		model.addAttribute("listUsers", listUsers);
		return "admin/listuser";
	}
}
