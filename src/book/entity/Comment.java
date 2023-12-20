package book.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT")
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "ID_COMMENT")
	private int idComment;

	@Column(name = "COMMENT_DETAIL")
	private String commentDetail;

	@Column(name = "CREATE_AT")
	private Date createAt;

	@Column(name = "STAR_COUNT")
	private int starCount;
	
	@Column(name = "IS_BUY")
	private int isBuy;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	@ManyToOne
	@JoinColumn(name = "ID_BOOK")
	private Book book;

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getCommentDetail() {
		return commentDetail;
	}

	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public int getStartCount() {
		return starCount;
	}

	public void setStartCount(int startCount) {
		this.starCount = startCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public int getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(int isBuy) {
		this.isBuy = isBuy;
	}

	
}
