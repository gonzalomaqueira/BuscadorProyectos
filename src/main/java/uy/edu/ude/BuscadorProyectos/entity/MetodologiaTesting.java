package uy.edu.ude.BuscadorProyectos.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MetodologiaTesting")
public class MetodologiaTesting extends ElementoProyecto
{	
	@OneToMany(mappedBy="metodologiaTesting", cascade=CascadeType.ALL)
	private List<SinonimoMetodologiaTesting> sinonimos;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="metodologiaTesting")  
    private List<Proyecto> proyectos;  

	
	public MetodologiaTesting() 
	{
	}
	
	public MetodologiaTesting(int idMetodologiaTesting) 
	{
		super(idMetodologiaTesting);
	}
	

	@Override
	public List<Sinonimo> getSinonimos()
	{
		ArrayList<Sinonimo> vRetorno = new ArrayList<Sinonimo>();
		for(Sinonimo sin: sinonimos)
		{
			vRetorno.add(sin);
		}
		return vRetorno;
	}
	
	public void setSinonimos(List<SinonimoMetodologiaTesting> sinonimos) {
		this.sinonimos = sinonimos;
	}

	@Override
	public String toString() {
		return "Testing [id=" + id + ", nombre=" + nombre +"]";
	}
	
	
}
