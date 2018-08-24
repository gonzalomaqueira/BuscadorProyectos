package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Alumnos")
public class Alumno 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlumno;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProyecto")
    private Proyecto proyecto;
    
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	private int nombreAlumno;
}
