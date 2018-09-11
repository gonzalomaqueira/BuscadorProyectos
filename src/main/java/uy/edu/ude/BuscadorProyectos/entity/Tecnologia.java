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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Tecnologias")
@NamedQueries({
	@NamedQuery(name = "Tecnologia.obtenerTecnologiasPorCategoria", query = "SELECT t FROM Tecnologia t  WHERE t.categoria = :idCategoria") })

public class Tecnologia extends ElementoProyecto
{	
	@ManyToOne
	@JoinColumn(name = "IdCategoria")
	private Categoria categoria;
	
	@OneToMany(mappedBy="tecnologia", cascade=CascadeType.ALL)
	private List<SinonimoTecnologia> sinonimos;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="tecnologia")  
    private List<Proyecto> proyectos;  
	
	public Tecnologia() 
	{
	}

	public Tecnologia(@NotNull @Size(min = 1, max = 255) String nombre, Categoria categoria, List<SinonimoTecnologia> sinonimos) 
	{
		super();
		this.categoria = categoria;
		this.sinonimos = sinonimos;
	}	
	
	public Tecnologia(long idTecnologia)
	{
		super(idTecnologia);
	}

	public Categoria getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(Categoria categoria) 
	{
		this.categoria = categoria;
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
	
	public void setSinonimos(List<SinonimoTecnologia> sinonimos) {
		this.sinonimos = sinonimos;
	}

	@Override
	public String toString() {
		return "Tecnologia [id=" + id + ", nombre=" + nombre +"]";
	}

}
