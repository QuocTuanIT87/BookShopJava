package book.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PUBLISHER")
public class Publisher {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_PUBLISHER")
	private int idPublisher;
	
	@Column(name = "PUBLISHER_NAME")
	private String publisherName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@OneToMany(mappedBy = "publisher", fetch=FetchType.EAGER)
	private Collection<Book> books;

	public int getIdPublisher() {
		return idPublisher;
	}

	public void setIdPublisher(int idPublisher) {
		this.idPublisher = idPublisher;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
}
