package book.dao;

import java.util.List;

import book.entity.User;

public interface AccountDAO {
	public void checkRegister();

	public User findByEmail(String email);
	
	public boolean isStrongPassword(String password);
	
	public boolean createUser(User user);
	
	public List<User> getListUsers();
	
	public boolean updateUser(User user);
	
	public int getTotalUser();
}
