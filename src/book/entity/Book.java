package book.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {

	@Id
	@GeneratedValue
	@Column(name = "ID_BOOK")
	private int idBook;

	@Column(name = "BOOK_NAME")
	private String bookName;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "PRICE")
	private float price;

	@Column(name = "STAR_COUNT")
	private int starCount;

	@Column(name = "IMAGE")
	private String image;

	@Column(name = "PUBLISH_YEAR")
	private String publishYear;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PAGE_COUNT")
	private int pageCount;

	@Column(name = "DISCOUNT_PERCENT")
	private int discountPercent;

	@Column(name = "QUANTITY")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "ID_PUBLISHER")
	private Publisher publisher;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORY")
	private Category category;

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private Collection<Comment> comments;

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStartCount() {
		return starCount;
	}

	public void setStartCount(int startCount) {
		this.starCount = startCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
