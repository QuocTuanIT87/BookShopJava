package book.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import book.dao.PublisherDAO;
import book.entity.Category;
import book.entity.Publisher;

@Transactional
public class PublisherDAOImpl implements PublisherDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addPublisher(Publisher publisher) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(publisher);
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
	public List<Publisher> getListPublishers() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Publisher> list = session.createQuery("FROM Publisher").list();
		return list;
	}

	@Override
	public Publisher getPublisher(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Publisher WHERE idPublisher = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		Publisher publisher = (Publisher) query.uniqueResult();
		return publisher;
	}

	@Override
	public boolean deletePublisher(Publisher oldPublisher) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(oldPublisher);
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
	public boolean updatePubilsher(Publisher oldPublisher) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(oldPublisher);
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
