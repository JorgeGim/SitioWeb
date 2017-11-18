package domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable, Cloneable{
	
	@Id
	@GeneratedValue
	private Long id;
	private String CUIT_CUIL;
	private String userName;
	private String e_mail;
	private String password;
	private int prestigio;
	private int puntos;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "usr", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts = new ArrayList<Post>();
	
	public List<Post> getPosts(){
		return posts;
	}
	
	public Usuario(){
		
	}
	
	public Usuario(String CUIL, String userName, String e_mail, String pass){
		this.CUIT_CUIL = CUIL;
		this.userName = userName;
		this.e_mail = e_mail;
		this.password = pass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPrestigio() {
		return prestigio;
	}

	public void setPrestigio(int prestigio) {
		this.prestigio = prestigio;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getCUIT_CUIL() {
		return CUIT_CUIL;
	}

	public String getUserName() {
		return userName;
	}

	public String getE_mail() {
		return e_mail;
	}
	
	public void addPost(Post p) {
		posts.add(p);
	}
	public void removePost(Post p) {
		posts.remove(p); 
		p.setUsuario(null);
		}
	
	public Long getId(){
		return id;
	}
	
	public void setId(long i){
		this.id = i;
	}
	
	 @Override
	 public String toString() {
		 return getUserName();
	}
	 
	@Override
	public Usuario clone() throws CloneNotSupportedException{
		try {
			return (Usuario) BeanUtils.cloneBean(this);
		} catch (Exception ex) {
			throw new CloneNotSupportedException();
		}
	}

	public void setCUIT_CUIL(String cUIT_CUIL) {
		CUIT_CUIL = cUIT_CUIL;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
}
