package book.entity;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOUNT")
public class Discount {

	@Id
	@GeneratedValue
	@Column(name = "ID_DISCOUNT")
	private int idDiscount;

	@Column(name = "START_DAY")
	private Date startDay;

	@Column(name = "END_DAY")
	private Date endDay;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DISCOUNT_PERCENT")
	private int discountPercent;
	
	@Column(name = "DETAIL_DISCOUNT")
	private String detailDiscount;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<Book> books;

	@OneToMany(mappedBy = "discount", fetch = FetchType.EAGER)
	private Collection<Order> orders;

	public int getIdDiscount() {
		return idDiscount;
	}

	public void setIdDiscount(int idDiscount) {
		this.idDiscount = idDiscount;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	public String getDetailDiscount() {
		return detailDiscount;
	}

	public void setDetailDiscount(String detailDiscount) {
		this.detailDiscount = detailDiscount;
	}
	
	

}
