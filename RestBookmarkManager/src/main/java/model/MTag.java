package model;

import common.PersistantObjectImpl;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "mtag")
public class MTag extends PersistantObjectImpl {
	private static final long serialVersionUID = 1L;
	
	@Column(length = 64, nullable = false)
	private String tag;
	
	@ManyToMany(mappedBy = "tags")
	private Set<MBookmark> bookmarks;
	
	public MTag() {
		this(null);
	}
	
	public MTag(final String pTag) {
		super();
		this.tag = pTag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set<MBookmark> getBookmarks() {
		return bookmarks;
	}
	
	public void addBookmark(MBookmark bookmark) {
		this.bookmarks.add(bookmark);
	}

	public void setBookmarks(Set<MBookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
}
