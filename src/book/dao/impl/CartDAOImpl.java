package book.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import book.dao.CartDAO;
import book.entity.Book;
import book.entity.CartDetail;
import book.entity.User;

@Transactional
public class CartDAOImpl implements CartDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCart(CartDetail cartDetail) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cartDetail);
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
	public CartDetail checkCartExist(Book book, User user) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CartDetail WHERE book = :book AND user = :user";
		Query query = session.createQuery(hql);
		query.setParameter("book", book);
		query.setParameter("user", user);
		CartDetail cartDetail = (CartDetail) query.uniqueResult();
		return cartDetail;
	}

	@Override
	public boolean updateCart(CartDetail oldCart) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(oldCart);
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
	public List<CartDetail> getCartOfUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<CartDetail> list = session.createQuery("FROM CartDetail WHERE user = " + user.getIdUser()).list();
		return list;
	}

	@Override
	public int deleteCart(int idUser) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM CartDetail WHERE user = " + idUser;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public boolean deleteItemCart(CartDetail oldCart) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(oldCart);
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
	public CartDetail getOneItemCart(User user) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM CartDetail WHERE user = :user";
		Query query = session.createQuery(hql);
		query.setParameter("user", user);
		query.setMaxResults(1); // Set maximum results to 1
		CartDetail cartDetail = (CartDetail) query.uniqueResult();
		return cartDetail;
	}

}
