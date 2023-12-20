package book.dao;

import java.util.List;

import book.entity.Book;

public interface BookDAO {
	public boolean addBook(Book book);
	
	public List<Book> getListBooks();
	
	public Book getBookById(int id);
	
	public boolean deleteBook(Book book);
	
	public boolean updateBook(Book book);
}
