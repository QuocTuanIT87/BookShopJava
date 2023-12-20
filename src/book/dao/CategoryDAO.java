package book.dao;

import java.util.List;

import book.entity.Category;

public interface CategoryDAO {
	public boolean addCategory(Category category);
	
	public List<Category> getListCategorys();
	
	public Category getCategory(int id);
	
	public boolean deleteCategory(Category cate);
	
	public boolean updateCategory(Category oldCate);
}
