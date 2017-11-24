package domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private int cantMeGusta;
	private int cantNoMeGusta;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Voto> votos = new HashSet<Voto>();
	
	
	public Set<Voto> getVotos() {
		return votos;
	}

	public void setVotos(Set<Voto> votos) {
		this.votos = votos;
	}
	
	public boolean votar(Voto v){
		
		boolean ret = this.votos.add(v);
		
		calcularMeGusta();
		calcularNoMeGusta();
		
		return ret;
	}

	public int getCantMeGusta() {
		
		calcularMeGusta();
		return cantMeGusta;
	}

	public int getCantNoMeGusta() {
		
		calcularNoMeGusta();
		return cantNoMeGusta;
	}
	
	public void calcularMeGusta() {
		
		int meGustas = 0;
		
		for(Voto v : votos){
			
			if(v.getVoto()>0) meGustas++;
		}
		
		cantMeGusta = meGustas;
	}
	
	public void calcularNoMeGusta() {
		
		int noMeGustas = 0;
		
		for(Voto v : votos){
			
			if(v.getVoto()<0) noMeGustas++;
		}
		
		cantNoMeGusta = noMeGustas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usr) {
		this.usuario = usr;
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
        return "Post{" + "id=" + id + ", userName=" + usuario.getUserName()
                + ", fecha=" + fecha.toString() + ", Contenido=" + Contenido + '}';
    }
}
