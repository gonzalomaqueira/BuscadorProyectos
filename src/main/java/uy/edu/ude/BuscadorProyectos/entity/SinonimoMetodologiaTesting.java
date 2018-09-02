package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SinonimoMetodologiaTesting")
public class SinonimoMetodologiaTesting extends Sinonimo
{		
	@ManyToOne
	@JoinColumn(name = "IdMetodologiaTesting")
	private MetodologiaTesting metodologiaTesting;	
}