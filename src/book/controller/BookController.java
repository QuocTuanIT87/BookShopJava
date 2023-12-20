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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import book.dao.AccountDAO;
import book.dao.BookDAO;
import book.dao.CommentDAO;
import book.dao.OrderDAO;
import book.entity.Book;
import book.entity.Comment;
import book.entity.Order;
import book.entity.OrderDetail;
import book.entity.User;

@Controller
public class BookController {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private CommentDAO commentDAO;

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

	@RequestMapping("book-detail")
	public String checkAccount(HttpServletRequest request, Model model) {
		String idBook = request.getParameter("id");
		Book book = bookDAO.getBookById(Integer.parseInt(idBook));

		List<Comment> listComments = commentDAO.getCommentOfBook(book);

		model.addAttribute("book", book);
		model.addAttribute("listComments", listComments);

		Cookie cookies = this.read(request, "email");

		if (cookies == null) {
			model.addAttribute("disableReview", 1);
		}
		return "pages/book-detail";

	}

	@RequestMapping("add-comment")
	public String addComment(@RequestParam("rate") String rate, @RequestParam("comment") String comment, Model model,
			HttpServletRequest request) {
		String idBook = request.getParameter("id");
		Book book = bookDAO.getBookById(Integer.parseInt(idBook));
		Cookie isUser = this.read(request, "email");
		User user = accountDAO.findByEmail(isUser.getValue());

		Date currentDate = new Date();

		Comment newComment = new Comment();
		newComment.setStartCount(Integer.parseInt(rate));
		newComment.setCommentDetail(comment);
		newComment.setCreateAt(currentDate);
		newComment.setBook(book);
		newComment.setUser(user);

		List<Order> listOrder = orderDAO.getOrderOfUserCompleted(user);
		int endCheck = 0;

		if (listOrder.size() == 0) {
			newComment.setIsBuy(0);
		} else {
			for (Order o : listOrder) {
				List<OrderDetail> listODetail = orderDAO.getOrderDetailByIdOrder(o.getIdOrder());
				for (OrderDetail oDetail : listODetail) {
					if (oDetail.getBook().getIdBook() == Integer.parseInt(idBook)) {
						newComment.setIsBuy(1);
						endCheck = 1;
						break;
					}

				}
				if (endCheck == 1) {
					break;
				}
			}
		}
		if (endCheck == 0) {
			newComment.setIsBuy(0);
		}

		commentDAO.addComment(newComment);
		return "redirect:/book-detail.htm?id=" + idBook;

	}
}
