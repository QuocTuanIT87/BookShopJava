package book.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import book.dao.OrderDAO;
import book.entity.CartDetail;
import book.entity.Order;
import book.entity.OrderDetail;
import book.entity.User;

@Transactional
public class OrderDAOImpl implements OrderDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean createOrder(Order order) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean createOrderDetail(OrderDetail orderDetail) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(orderDetail);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<Order> getListOrders() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Order> list = session.createQuery("FROM Order").list();
		return list;
	}

	@Override
	public Order getOrderById(int idOrder) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order WHERE idOrder = " + idOrder;
		Query query = session.createQuery(hql);
		Order order = (Order) query.uniqueResult();
		return order;
	}

	@Override
	public boolean updateOrder(Order order) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(order);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	@Override
	public List<Order> getOrderOfUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Order> list = session.createQuery("FROM Order WHERE user = " + user.getIdUser()).list();
		return list;
	}

	@Override
	public float getTotalRevenuev() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order WHERE status = 2";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Order> order = query.list();
		float total = 0;

		for (Order o : order) {
			total += o.getSumMoney();
		}

		return total;
	}

	@Override
	public List<OrderDetail> getOrderDetailByIdOrder(int idOrder) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = session.createQuery("FROM OrderDetail WHERE order = " + idOrder).list();
		return list;
	}

	@Override
	public int getTotalProductSold() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order WHERE status = 2";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Order> order = query.list();
		int total = 0;

		for (Order o : order) {
			List<OrderDetail> orderDetail = getOrderDetailByIdOrder(o.getIdOrder());
			for (OrderDetail oDetail : orderDetail) {
				total += oDetail.getQuantity();
			}
		}

		return total;
	}

	@Override
	public List<Order> getOrderOfUserCompleted(User user) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Order> list = session.createQuery("FROM Order WHERE user = " + user.getIdUser() + "AND status = 2").list();
		return list;
	}

}
