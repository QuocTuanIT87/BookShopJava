package book.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CART_DETAIL")
public class CartDetail {

	@EmbeddedId
	private CartDetailKey cartDetailId;

	@ManyToOne
	@MapsId("ID_BOOK")
	@JoinColumn(name = "ID_BOOK")
	private Book book;

	@ManyToOne
	@MapsId("ID_USER")
	@JoinColumn(name = "ID_USER")
	private User user;

	@Column(name = "QUANTITY")
	private int quantity;
	
	@Column(name = "DISCOUNT_PERCENT")
	private int discountPercent;

	public CartDetailKey getCartDetailId() {
		return cartDetailId;
	}

	public void setCartDetailId(CartDetailKey cartDetailId) {
		this.cartDetailId = cartDetailId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	

}
