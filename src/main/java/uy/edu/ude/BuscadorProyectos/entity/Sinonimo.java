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
	protected int id;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	protected String nombre;
	
	public Sinonimo() {
	}
	
	public Sinonimo(int id, @NotNull @Size(min = 1, max = 255) String nombre)
	{
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
