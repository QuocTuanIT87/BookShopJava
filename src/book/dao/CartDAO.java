package book.dao;

import java.util.List;

import book.entity.Book;
import book.entity.CartDetail;
import book.entity.User;

public interface CartDAO {
	public boolean addCart(CartDetail cartDetail);
	
	public CartDetail checkCartExist(Book book, User user);
	
	public CartDetail getOneItemCart(User user);
	
	public boolean updateCart(CartDetail oldCart);
	
	public int deleteCart(int idUser);
	
	public boolean deleteItemCart(CartDetail oldCart);
	
	public List<CartDetail> getCartOfUser(User user);
	
}
