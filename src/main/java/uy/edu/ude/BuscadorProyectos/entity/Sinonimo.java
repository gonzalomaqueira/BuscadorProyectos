package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Sinonimo
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdSinonimo")
	protected Long id;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	protected String nombre;
	
	public Sinonimo() {
	}
	
	public Sinonimo(Long id, @NotNull @Size(min = 1, max = 255) String nombre)
	{
		this.nombre = nombre;
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
}
