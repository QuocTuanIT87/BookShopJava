package book.dao;

import java.util.List;

import book.entity.Order;
import book.entity.OrderDetail;
import book.entity.User;

public interface OrderDAO {
	public boolean createOrder(Order order);
	
	public boolean createOrderDetail(OrderDetail orderDetail);
	
	public List<Order> getListOrders();
	
	
	public Order getOrderById(int idOrder);
	
	public boolean updateOrder(Order order);
	
	public List<Order> getOrderOfUser(User user);
	
	public List<Order> getOrderOfUserCompleted(User user);
	
	public float getTotalRevenuev();
	
	public int getTotalProductSold();
	
	public List<OrderDetail> getOrderDetailByIdOrder(int idOrder);
	

}
