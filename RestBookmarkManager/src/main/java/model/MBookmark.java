package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import common.PersistantObjectImpl;


@Entity
@Table(name = "mbookmark")
public class MBookmark extends PersistantObjectImpl {
	private static final long serialVersionUID = 1L;
	
	@Column(length = 40, nullable = false)
	private String nom;
	@Column(length = 255, nullable = false)
	private String url;
	@Column(length = 2048, nullable = true)
	private String description;
	@Column(nullable = false)
	private int vues;
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<MTag> tags;

	public Set<MTag> getTags() {
		return tags;
	}

	public void setTags(Set<MTag> tags) {
		this.tags = tags;
	}

	public MBookmark() {
		this(null, null, null, -1);
	}
	
	public MBookmark(final String pNom, final String pUrl, final String pDescription, final int pVues) {
		super();
		this.nom = pNom;
		this.url = pUrl;
		this.description = pDescription;
		this.vues = pVues;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getVues() {
		return vues;
	}
	
	public void addTag(MTag tag){
		this.tags.add(tag);
	}
	public void setVues(int vues) {
		this.vues = vues;
	}
	
	public String toString(){
		return "nom: " + this.nom;
	}
}
