package book.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey orderDetailId;

	@ManyToOne
	@MapsId("ID_BOOK")
	@JoinColumn(name = "ID_BOOK")
	private Book book;

	@ManyToOne
	@MapsId("ID_ORDER")
	@JoinColumn(name = "ID_ORDER")
	private Order order;

	@Column(name = "QUANTITY")
	private int quantity;

	public OrderDetailKey getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(OrderDetailKey orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
