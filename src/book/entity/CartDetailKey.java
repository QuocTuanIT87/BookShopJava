package book.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartDetailKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_BOOK")
	private int idBook;

	@Column(name = "ID_USER")
	private int idUser;

	public CartDetailKey() {
		super();
	}

	public CartDetailKey(int idBook, int idUser) {
		super();
		this.idBook = idBook;
		this.idUser = idUser;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
