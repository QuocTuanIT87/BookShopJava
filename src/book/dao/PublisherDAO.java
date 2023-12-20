package book.dao;


import java.util.List;
import book.entity.Publisher;

public interface PublisherDAO {
	public boolean addPublisher(Publisher publisher);
	
	public List<Publisher> getListPublishers();
	
	public Publisher getPublisher(int id);
	
	public boolean deletePublisher(Publisher oldPublisher);
	
	public boolean updatePubilsher(Publisher oldPublisher);
 }
