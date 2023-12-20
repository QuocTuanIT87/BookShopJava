package book.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import book.bean.UploadFile;
import book.dao.BookDAO;
import book.dao.CategoryDAO;
import book.dao.PublisherDAO;
import book.entity.Book;
import book.entity.Category;
import book.entity.Publisher;

@Controller
public class BookAdminController {

	public static String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentTime = df.format(new Date());
		return currentTime;
	}

	@Autowired
	@Qualifier("categoryFile")
	private UploadFile upFile;

	@Autowired
	@Qualifier("bookFile")
	private UploadFile fileImgBook;

	@Autowired
	@Qualifier("rootFile")
	private UploadFile rootFile;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private PublisherDAO publisherDAO;

	@RequestMapping("listbook")
	public String listBook(ModelMap model) {
		List<Book> listBooks = bookDAO.getListBooks();
		model.addAttribute("listBooks", listBooks);
		return "admin/listbook";
	}

	@RequestMapping("edit-book")
	public String editBook(HttpServletRequest request, ModelMap model) {
		String idBook = request.getParameter("id");
		Book book = bookDAO.getBookById(Integer.parseInt(idBook));
		model.addAttribute("book", book);

		List<Category> listCategorys = categoryDAO.getListCategorys();
		model.addAttribute("listCategorys", listCategorys);

		List<Publisher> listPublishers = publisherDAO.getListPublishers();
		model.addAttribute("listPublishers", listPublishers);

		return "admin/crud/update-book";
	}

	@RequestMapping("delete-book")
	public String deleteBook(HttpServletRequest request) {
		String idBook = request.getParameter("id");
		Book book = bookDAO.getBookById(Integer.parseInt(idBook));

//		Delete file image on PC
		String filePath = rootFile.getBasePath() + book.getImage();

		try {
			// Create a Path object representing the file
			Path path = Paths.get(filePath);

			// Use Files.delete() to delete the file
			Files.delete(path);

		} catch (IOException e) {
			// Handle exceptions, such as if the file doesn't exist
			System.err.println("Unable to delete the file: " + e.getMessage());
		}

		bookDAO.deleteBook(book);

		return "redirect:/listbook.htm";
	}

	@RequestMapping("list-category")
	public String listCategory(ModelMap model) {
		List<Category> listCategorys = categoryDAO.getListCategorys();
		model.addAttribute("listCategorys", listCategorys);
		return "admin/listcategory";
	}

	@RequestMapping(value = "add-category", method = RequestMethod.POST)
	public String addCategory(@RequestParam("category") String categoryName,
			@RequestParam("image") MultipartFile fileImage, ModelMap model, HttpServletRequest request) {

		Category newCate = new Category();
		newCate.setCategoryName(categoryName);

		if (fileImage.getOriginalFilename() != "") {
			String images = "";
			String nameFormat = getCurrentTime() + "_" + fileImage.getOriginalFilename();
			String path = upFile.getBasePath() + File.separator + nameFormat;
			images += "assets/images/category/" + nameFormat;
			newCate.setImage(images);
			try {
				fileImage.transferTo(new File(path));
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		String type = request.getParameter("type");

		if (type != null) {
			if (type.equals("edit")) {
				String idCategory = request.getParameter("id");
				Category category = categoryDAO.getCategory(Integer.parseInt(idCategory));

				newCate.setIdCategory(Integer.parseInt(idCategory));
				if (fileImage.getOriginalFilename() == "") {
					newCate.setImage(category.getImage());
				} else {
//					Delete file image on PC
					String filePath = rootFile.getBasePath() + category.getImage();

					try {
						// Create a Path object representing the file
						Path path = Paths.get(filePath);

						// Use Files.delete() to delete the file
						Files.delete(path);

					} catch (IOException e) {
						// Handle exceptions, such as if the file doesn't exist
						System.err.println("Unable to delete the file: " + e.getMessage());
					}
				}

				categoryDAO.updateCategory(newCate);
				return "redirect:/list-category.htm";
			}
		}

		boolean success = categoryDAO.addCategory(newCate);
		if (success) {
			model.addAttribute("successMessage", "Add new category successful!");
		} else {
			model.addAttribute("failedMessage", "An error occurred, please try again later!");
		}

		return "admin/crud/add-category";
	}

	@RequestMapping("add-category")
	public String addCategory() {
		return "admin/crud/add-category";
	}

	@RequestMapping("delete-category")
	public String deleteCategory(HttpServletRequest request) {
		String idCategory = request.getParameter("id");
		Category cate = categoryDAO.getCategory(Integer.parseInt(idCategory));

//		Delete file image on PC
		String filePath = rootFile.getBasePath() + cate.getImage();

		try {
			// Create a Path object representing the file
			Path path = Paths.get(filePath);

			// Use Files.delete() to delete the file
			Files.delete(path);

		} catch (IOException e) {
			// Handle exceptions, such as if the file doesn't exist
			System.err.println("Unable to delete the file: " + e.getMessage());
		}

		categoryDAO.deleteCategory(cate);

		return "redirect:/list-category.htm";
	}

	@RequestMapping("edit-category")
	public String updateCategory(HttpServletRequest request, ModelMap model) {
		String idCategory = request.getParameter("id");
		Category category = categoryDAO.getCategory(Integer.parseInt(idCategory));

		model.addAttribute("cate", category);

		return "admin/crud/update-category";
	}

	@RequestMapping("add-book")
	public String addBook(ModelMap model) {
		List<Category> listCategorys = categoryDAO.getListCategorys();
		model.addAttribute("listCategorys", listCategorys);

		List<Publisher> listPublishers = publisherDAO.getListPublishers();
		model.addAttribute("listPublishers", listPublishers);

		return "admin/crud/add-book";
	}

	@RequestMapping(value = "add-book", method = RequestMethod.POST)
	public String addBook(@RequestParam("name") String bookName, @RequestParam("author") String author,
			@RequestParam("price") float price, @RequestParam("year") String year,
			@RequestParam("description") String des, @RequestParam("page") int page,
			@RequestParam("discount") int discount, @RequestParam("quantity") int quantity,
			@RequestParam("category") int categoryId, @RequestParam("publisher") int publisherId,
			@RequestParam("image") MultipartFile fileImage, ModelMap model, HttpServletRequest request) {

		Book newBook = new Book();
		newBook.setBookName(bookName);
		newBook.setPrice(price);
		newBook.setAuthor(author);
		newBook.setPublishYear(year);
		newBook.setDescription(des);
		newBook.setPageCount(page);
		newBook.setDiscountPercent(discount);
		newBook.setQuantity(quantity);

		Category category = categoryDAO.getCategory(categoryId);
		Publisher publisher = publisherDAO.getPublisher(publisherId);

		newBook.setCategory(category);
		newBook.setPublisher(publisher);

		if (fileImage.getOriginalFilename() != "") {
			String images = "";
			String nameFormat = getCurrentTime() + "_" + fileImage.getOriginalFilename();
			String path = fileImgBook.getBasePath() + File.separator + nameFormat;
			images += "assets/images/book/" + nameFormat;
			newBook.setImage(images);

			try {
				fileImage.transferTo(new File(path));
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String type = request.getParameter("type");

		if (type != null) {
			if (type.equals("edit")) {
				String idBook = request.getParameter("id");
				Book book = bookDAO.getBookById(Integer.parseInt(idBook));

				newBook.setIdBook(Integer.parseInt(idBook));
				if (fileImage.getOriginalFilename() == "") {
					newBook.setImage(book.getImage());
				} else {
//					Delete file image on PC
					String filePath = rootFile.getBasePath() + book.getImage();

					try {
						// Create a Path object representing the file
						Path path = Paths.get(filePath);

						// Use Files.delete() to delete the file
						Files.delete(path);

					} catch (IOException e) {
						// Handle exceptions, such as if the file doesn't exist
						System.err.println("Unable to delete the file: " + e.getMessage());
					}
				}

				bookDAO.updateBook(newBook);
				return "redirect:/listbook.htm";
			}
		}

		boolean success = bookDAO.addBook(newBook);
		if (success) {
			model.addAttribute("successMessage", "Add new book successful!");
		} else {
			model.addAttribute("failedMessage", "An error occurred, please try again later!");
		}

		return "redirect:/add-book.htm";
	}

}
