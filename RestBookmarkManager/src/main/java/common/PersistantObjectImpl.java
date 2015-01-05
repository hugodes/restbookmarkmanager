package common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistantObjectImpl implements IPersitantObject {
	
	 @Id
	 @GeneratedValue
	 @Column(name = "id", unique = true, nullable = false)
	 private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
}
