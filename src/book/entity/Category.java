package book.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "CATEGORY")
public class Category {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_CATEGORY")
	private int idCategory;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	@Column(name = "IMAGE")
	private String image;
	
	@OneToMany(mappedBy = "category", fetch=FetchType.EAGER)
	private Collection<Book> books;

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
