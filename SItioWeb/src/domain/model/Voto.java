package domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Voto implements Serializable, Cloneable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int voto;

	@ManyToOne
	private Post post;
	
	@ManyToOne
	private Usuario usr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVoto() {
		return voto;
	}

	public void like() {
		this.voto = 1;
	}
	
	public void dislike() {
		this.voto = -1;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		this.usr = usr;
	}
}
