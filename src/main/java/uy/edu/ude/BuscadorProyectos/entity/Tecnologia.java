package uy.edu.ude.BuscadorProyectos.entity;

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
@Table(name = "Tecnologias")
public class Tecnologia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdTecnologia")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "IdCategoria")
	private Categoria categoria;
	
	@OneToMany
	@JoinColumn(name = "IdTecnologia")
	private List<Sinonimo> sinonimos;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="tecnologia")  
    private List<Proyecto> proyectos;  


	
	
	public Tecnologia() {
	}

	

	public Tecnologia(@NotNull @Size(min = 1, max = 255) String nombre, Categoria categoria, List<Sinonimo> sinonimos) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.sinonimos = sinonimos;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Sinonimo> getSinonimos() {
		return sinonimos;
	}

	public void setSinonimos(List<Sinonimo> sinonimos) {
		this.sinonimos = sinonimos;
	}

	@Override
	public String toString() {
		return "Tecnologia [id=" + id + ", nombre=" + nombre +"]";
	}
	
	
}
