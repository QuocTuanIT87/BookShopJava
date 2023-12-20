package book.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import book.dao.DiscountDAO;
import book.entity.Discount;

@Controller
public class DiscountAdminController {

	@Autowired
	private DiscountDAO discountDAO;

	@RequestMapping("list-promotion")
	public String listPromotion(ModelMap model) {
		List<Discount> listDiscounts = discountDAO.getListDiscounts();
		model.addAttribute("listDiscounts", listDiscounts);
		return "admin/listdiscount";
	}

	@RequestMapping("add-discount")
	public String addPromotion(ModelMap model) {
		return "admin/crud/add-discount";
	}

	@RequestMapping(value = "add-discount", method = RequestMethod.POST)
	public String addDiscount(@RequestParam("detail") String detail, @RequestParam("start") String startDay,
			@RequestParam("end") String endDay, @RequestParam("code") String code, @RequestParam("percent") int percent,
			HttpServletRequest request) {

		SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			Date start = originalFormat.parse(startDay);
			Date end = originalFormat.parse(endDay);

			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date formattedStartDate = newFormat.parse(newFormat.format(start));
			Date formattedEndDate = newFormat.parse(newFormat.format(end));

			Discount discount = new Discount();
			discount.setCode(code);
			discount.setDetailDiscount(detail);
			discount.setStartDay(formattedStartDate);
			discount.setEndDay(formattedEndDate);
			discount.setDiscountPercent(percent);

			String type = request.getParameter("type");

			if (type != null) {
				if (type.equals("edit")) {
					String idDiscount = request.getParameter("id");
					discount.setIdDiscount(Integer.parseInt(idDiscount));
					discountDAO.updateDiscount(discount);

					return "redirect:/list-promotion.htm";
				}
			}

			discountDAO.addDiscount(discount);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "admin/crud/add-discount";
	}

	@RequestMapping("delete-discount")
	public String deletePromotion(HttpServletRequest request) {
		String idDiscount = request.getParameter("id");
		Discount discount = discountDAO.getDiscountById(Integer.parseInt(idDiscount));
		discountDAO.deleteDiscount(discount);

		return "redirect:/list-promotion.htm";
	}

	@RequestMapping("edit-discount")
	public String editPromotion(HttpServletRequest request, ModelMap model) {
		String idDiscount = request.getParameter("id");
		Discount discount = discountDAO.getDiscountById(Integer.parseInt(idDiscount));

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String startDay = formatter.format(discount.getStartDay());
		String endDay = formatter.format(discount.getEndDay());

		model.addAttribute("discount", discount);
		model.addAttribute("startDay", startDay);
		model.addAttribute("endDay", endDay);

		return "admin/crud/update-discount";
	}

}
