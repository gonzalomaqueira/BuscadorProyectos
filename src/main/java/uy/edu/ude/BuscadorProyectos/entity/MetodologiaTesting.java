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
@Table(name = "MetodologiaTesting")
public class MetodologiaTesting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdTesting")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	private String nombre;
	
	@OneToMany(mappedBy="metodologiaTesting", cascade=CascadeType.ALL)
	private List<SinonimoMetodologiaTesting> sinonimos;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="metodologiaTesting")  
    private List<Proyecto> proyectos;  

	
	public MetodologiaTesting() {
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

	public List<SinonimoMetodologiaTesting> getSinonimos() {
		return sinonimos;
	}

	public void setSinonimos(List<SinonimoMetodologiaTesting> sinonimos) {
		this.sinonimos = sinonimos;
	}

	@Override
	public String toString() {
		return "Testing [id=" + id + ", nombre=" + nombre +"]";
	}
	
	
}
