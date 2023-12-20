package book.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import book.dao.DiscountDAO;
import book.entity.Book;
import book.entity.Discount;

@Transactional
public class DiscountDAOImpl implements DiscountDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Discount> getListDiscounts() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Discount> list = session.createQuery("FROM Discount").list();
		return list;
	}

	@Override
	public boolean addDiscount(Discount discount) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(discount);
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
	public Discount getDiscountById(int idDiscount) {
		Session session = sessionFactory.getCurrentSession();
		Discount discount = (Discount) session.createQuery("FROM Discount WHERE idDiscount=" + idDiscount)
				.uniqueResult();
		return discount;
	}

	@Override
	public boolean deleteDiscount(Discount discount) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(discount);
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
	public boolean updateDiscount(Discount discount) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(discount);
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

}
