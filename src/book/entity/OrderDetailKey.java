package book.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_BOOK")
	private int idBook;

	@Column(name = "ID_ORDER")
	private int idOrder;

	public OrderDetailKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetailKey(int idBook, int idOrder) {
		super();
		this.idBook = idBook;
		this.idOrder = idOrder;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
