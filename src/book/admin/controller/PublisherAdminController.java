package book.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import book.dao.CategoryDAO;
import book.dao.PublisherDAO;
import book.entity.Publisher;

@Controller
public class PublisherAdminController {

	@Autowired
	private PublisherDAO publisherDAO;

	@RequestMapping("listpublisher")
	public String registerAccount(ModelMap model) {
		List<Publisher> listPublishers = publisherDAO.getListPublishers();
		model.addAttribute("listPublishers", listPublishers);
		return "admin/listpublisher";
	}

	@RequestMapping("add-publisher")
	public String addPublisher(ModelMap model) {
		return "admin/crud/add-publisher";
	}

	@RequestMapping(value = "add-publisher", method = RequestMethod.POST)
	public String addPublisher(ModelMap model, @RequestParam("name") String publisherName,
			@RequestParam("address") String address, @RequestParam("phone") String phoneNumber,
			HttpServletRequest request) {
		Publisher newPublisher = new Publisher();
		newPublisher.setPublisherName(publisherName);
		newPublisher.setAddress(address);
		newPublisher.setPhoneNumber(phoneNumber);

		String type = request.getParameter("type");
		if (type != null) {
			if (type.equals("edit")) {
				String idPublisher = request.getParameter("id");

				newPublisher.setIdPublisher(Integer.parseInt(idPublisher));

				publisherDAO.updatePubilsher(newPublisher);
				return "redirect:/listpublisher.htm";
			}
		}

		boolean success = publisherDAO.addPublisher(newPublisher);

		if (success) {
			model.addAttribute("successMessage", "Add new publisher successful!");
		} else {
			model.addAttribute("failedMessage", "An error occurred, please try again later!");
		}

		return "admin/crud/add-publisher";
	}

	@RequestMapping("delete-publisher")
	public String deletePublisher(HttpServletRequest request) {

		String idPublisher = request.getParameter("id");

		Publisher oldPublisher = publisherDAO.getPublisher(Integer.parseInt(idPublisher));

		publisherDAO.deletePublisher(oldPublisher);

		return "redirect:/listpublisher.htm";
	}

	@RequestMapping("edit-publisher")
	public String editPublisher(HttpServletRequest request, ModelMap model) {

		String idPublisher = request.getParameter("id");

		Publisher oldPublisher = publisherDAO.getPublisher(Integer.parseInt(idPublisher));

		model.addAttribute("publisher", oldPublisher);

		return "admin/crud/update-publisher";
	}
}
