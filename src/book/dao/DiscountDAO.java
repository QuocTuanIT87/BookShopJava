package book.dao;

import java.util.List;

import book.entity.Discount;

public interface DiscountDAO {
	public List<Discount> getListDiscounts();
	
	public boolean addDiscount(Discount discount);
	
	public Discount getDiscountById(int idDiscount);
	
	public boolean deleteDiscount(Discount discount);
	
	public boolean updateDiscount(Discount discount);
}
