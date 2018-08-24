package uy.edu.ude.BuscadorProyectos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdCategoria")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	private String nombre;
	
	@OneToMany
	@JoinColumn(name = "IdCategoria")
	private List<Tecnologia> tecnologias;
	
	
	
	public Categoria() {
	}

	public Categoria(String nombre, List<Tecnologia> tecnologias) {
		this.nombre = nombre;
		this.tecnologias = tecnologias;
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

	public List<Tecnologia> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<Tecnologia> tecnologias) {
		this.tecnologias = tecnologias;
	}

	@Override
	public String toString() {
		return String.format("Categoria [nombre=%s, tecnologias=%s]", nombre, tecnologias);
	}
	
	
	
	
	
	

}
