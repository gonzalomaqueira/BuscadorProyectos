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
@Table(name = "ModeloProceso")
public class ModeloProceso extends ElementoProyecto
{
	@OneToMany(mappedBy="modeloProceso", cascade=CascadeType.ALL)
	private List<SinonimoModeloProceso> sinonimos;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="modeloProceso")  
    private List<Proyecto> proyectos;  

	public ModeloProceso() 
	{
	}
	
	public ModeloProceso(long idModeloProceso) 
	{
		super(idModeloProceso);
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
	
	public void setSinonimos(List<SinonimoModeloProceso> sinonimos) {
		this.sinonimos = sinonimos;
	}

	@Override
	public String toString() {
		return "ModeloProceso [id=" + id + ", nombre=" + nombre +"]";
	}	
	
}
