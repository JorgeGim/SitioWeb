package domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("serial")
@Entity
public class Post implements Serializable, Cloneable
{
	@Id
	@GeneratedValue
	private Long id;
	
	private Date fecha;
	private String Contenido;
	
	@ManyToOne
	private Usuario usr;
	
	public Usuario getUsr() {
		return usr;
	}
	
	public void setUsuario(Usuario usr) {
		this.usr = usr;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getContenido() {
		return Contenido;
	}

	public void setContenido(String contenido) {
		Contenido = contenido;
	}
	
    @Override
    public Post clone() throws CloneNotSupportedException {
        try {
            return (Post) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }
    
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", userName=" + usr.getUserName()
                + ", fecha=" + fecha.toString() + ", Contenido=" + Contenido + '}';
    }
}
