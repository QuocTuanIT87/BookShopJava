package book.dao;

import java.util.List;

import book.entity.Book;
import book.entity.Comment;

public interface CommentDAO {
	public boolean addComment(Comment comment);
	
	public List<Comment> getCommentOfBook(Book book);
 }
