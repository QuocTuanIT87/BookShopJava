package book.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import book.dao.CategoryDAO;
import book.entity.Category;


@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean addCategory(Category category) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(category);
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
	public List<Category> getListCategorys() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Category> list = session.createQuery("FROM Category").list();
		return list;
	}

	@Override
	public Category getCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Category WHERE idCategory = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		Category category = (Category) query.uniqueResult();
		return category;
	}

	@Override
	public boolean deleteCategory(Category cate) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cate);
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
	public boolean updateCategory(Category oldCate) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(oldCate);
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
