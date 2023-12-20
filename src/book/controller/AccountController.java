package book.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import book.dao.AccountDAO;
import book.dao.OrderDAO;
import book.entity.Order;
import book.entity.User;
import book.utils.Constants;
import book.bean.AdminMailer;

@Controller
public class AccountController {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private AdminMailer mailer;

// Create Cookies
	public Cookie create(String name, String value, int days) {
		String encodedValue = Base64.getEncoder().encodeToString(value.getBytes());
		Cookie cookie = new Cookie(name, encodedValue);
		cookie.setMaxAge(days * 24 * 60 * 60);
		cookie.setPath("/");
		return cookie;
	}

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

	public void delete(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		cookie.setPath("/"); // Set the path to match the original cookie
		// cookie.setDomain("example.com"); // Set the domain if applicable
		cookie.setSecure(true); // Set the Secure flag if applicable
		cookie.setHttpOnly(true); // Set the HttpOnly flag if applicable
		response.addCookie(cookie);
	}

	@RequestMapping("account")
	public String checkAccount(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Cookie cookies = this.read(request, "email");

		if (cookies == null) {
			return "user/login";
		} else {
			Cookie role = this.read(request, "role");
			User user = accountDAO.findByEmail(cookies.getValue());

			if (role.getValue().equals("0")) {
				List<Order> myOrder = orderDAO.getOrderOfUser(user);
				model.addAttribute("orders", myOrder);
				model.addAttribute("pageNumber", 0);
				return "user/profile";
			} else {
				return "admin/home";
			}
		}

	}

	@RequestMapping("get-otp")
	public String getOTP(@RequestParam("email") String email, ModelMap model, HttpSession session) {
		User user = accountDAO.findByEmail(email);

		if (user == null) {
			model.addAttribute("failedMessage", "Email is not registered");
			return "user/forgot-password";
		}

		String code = Constants.randomCode(6);
		session.setAttribute("codeOTP", code);

		String from = "quoctuanit87@gmail.com";
		String to = email;
		String subject = "Forgot Password";
		String body = "Your code is " + code;

		mailer.sendmailer(from, to, subject, body);

		model.addAttribute("successMessage", "The confirmation code has been sent to your inbox");

		session.setAttribute("email", email);
		return "user/confirm-otp";
	}

	@RequestMapping("confirm-otp")
	public String confirmOTP(HttpSession session, @RequestParam("otp") String OTP) {
		String OTPSession = (String) session.getAttribute("codeOTP");
		String email = (String) session.getAttribute("email");

		if (OTPSession.equals(OTP)) {
			session.setAttribute("email", email);
			return "user/change-password";
		} else {
			return "redirect:/home.htm";
		}

	}

	@RequestMapping("update-password")
	public String updatePassword(HttpSession session, ModelMap model, @RequestParam("re-password") String rePassword,
			@RequestParam("password") String password) {
		boolean isStrongPassword = accountDAO.isStrongPassword(password);

		if (isStrongPassword == false) {
			model.addAttribute("failedMessage", "Please enter at least 6 characters!");
			return "user/change-password";
		} else if (!rePassword.equals(password)) {
			model.addAttribute("failedMessage", "The password entered does not match, please re-enter!");
			return "user/change-password";
		} else {
			String email = (String) session.getAttribute("email");
			User user = accountDAO.findByEmail(email);
			user.setPassword(password);
			accountDAO.updateUser(user);
			model.addAttribute("successMessage", "Update password successfully!");
			return "user/login";
		}

	}

	@RequestMapping("forgot-password")
	public String forgotPassword() {
		return "user/forgot-password";

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerAccount(@Validated @ModelAttribute("user") User user, BindingResult errors, ModelMap model,
			@RequestParam("re-password") String rePassword) {

		User emailUser = accountDAO.findByEmail(user.getEmail());
		boolean isStrongPassword = accountDAO.isStrongPassword(user.getPassword());

		if (errors.hasErrors()) {
			model.addAttribute("user", user);
			return "user/register";
		} else if (emailUser != null) {
			errors.rejectValue("email", "user", "Registration email already exists, please use another email!");
			return "user/register";
		} else if (isStrongPassword == false) {
			errors.rejectValue("password", "user", "Please enter at least 6 characters!");
			return "user/register";
		} else if (!user.getPassword().equals(rePassword)) {
			errors.rejectValue("password", "user", "The password entered does not match, please re-enter!");
			return "user/register";
		}

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setName(user.getName());
//		0 is User, 1 is administration
		newUser.setRole(0);
		boolean success = accountDAO.createUser(newUser);
		if (success) {
			model.addAttribute("successMessage", "Account registration successful!");
			return "user/login";
		} else {
			model.addAttribute("failedMessage", "An error occurred, please try again later!");
		}

		return "user/register";

	}

	@RequestMapping("register")
	public String registerAccount(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user/register";
	}

	@RequestMapping("login")
	public String loginAccount(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user/login";
	}

	@RequestMapping("lock-user")
	public String lockUser(HttpServletRequest request) {

		String email = request.getParameter("email");

		User user = accountDAO.findByEmail(email);

		user.setIsLock(1);
		accountDAO.updateUser(user);

		return "redirect:/listuser.htm";
	}

	@RequestMapping("unlock-user")
	public String unLockUser(HttpServletRequest request) {

		String email = request.getParameter("email");

		User user = accountDAO.findByEmail(email);

		user.setIsLock(0);
		accountDAO.updateUser(user);

		return "redirect:/listuser.htm";
	}

	@RequestMapping("logout")
	public String logoutAccount(HttpServletResponse response, HttpSession session) {
		this.delete("email", response);
		this.delete("password", response);
		this.delete("role", response);
		session.removeAttribute("currentUser");
		return "redirect:/home.htm";
	}

	@RequestMapping("update-user")
	public String updateUser(@RequestParam("phone") String phone, @RequestParam("address") String address,
			HttpServletRequest request) {

		Cookie isUser = this.read(request, "email");
		User user = accountDAO.findByEmail(isUser.getValue());

		user.setPhoneNumber(phone);
		user.setAddress(address);

		accountDAO.updateUser(user);

		return "redirect:/order-check.htm";
	}

	@RequestMapping("my-account")
	public String myAccount(ModelMap model) {

		model.addAttribute("pageNumber", 1);

		return "user/profile";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginAccount(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, ModelMap model, HttpServletResponse response, HttpServletRequest request) {

		User user = accountDAO.findByEmail(email);

		if (user == null) {
			model.addAttribute("failedMessage", "Email account is not registered!");
			return "user/login";
		} else if (user.getIsLock() == 1) {
			return "user/banned-account";
		} else if (!user.getPassword().equals(password)) {
			model.addAttribute("failedMessage", "Wrong password!");
			return "user/login";
		} else {
			Cookie emailCookie = this.create("email", user.getEmail(), 1);
			response.addCookie(emailCookie);
			Cookie passwordCookie = this.create("password", user.getPassword(), 1);
			response.addCookie(passwordCookie);
			Cookie roleCookie = this.create("role", Integer.toString(user.getRole()), 1);
			response.addCookie(roleCookie);

			model.addAttribute("successMessage", "Login account successful!");
			session.setAttribute("currentUser", user);

			try {
				// Sleep for 2 seconds (2000 milliseconds)
				Thread.sleep(1500);

			} catch (InterruptedException e) {
				// Handle the exception (if needed)
				e.printStackTrace();
			}

			return "redirect:home.htm";
		}

	}
}
