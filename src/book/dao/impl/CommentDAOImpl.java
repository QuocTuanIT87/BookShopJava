package book.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import book.dao.CommentDAO;
import book.entity.Book;
import book.entity.Comment;

@Transactional
public class CommentDAOImpl implements CommentDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public boolean addComment(Comment comment) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(comment);
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
	public List<Comment> getCommentOfBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Comment> listComment = session.createQuery("FROM Comment WHERE book=" + book.getIdBook()).list();
		return listComment;
	}

}
