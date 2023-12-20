package book.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import book.dao.AccountDAO;
import book.entity.Category;
import book.entity.Order;
import book.entity.User;

@Transactional
public class AccountDAOImpl implements AccountDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void checkRegister() {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		return user;
	}

	@Override
	public boolean isStrongPassword(String password) {
		// TODO Auto-generated method stub
		boolean f = false;
		if (password.length() >= 6) {
			f = true;
		}
		return f;
	}

	@Override
	public boolean createUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
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
	public List<User> getListUsers() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> list = session.createQuery("FROM User WHERE role = 0").list();
		return list;
	}

	@Override
	public boolean updateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
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
	public int getTotalUser() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE role = 0";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> user = query.list();
		int total = user.size();
		return total;
	}

}
