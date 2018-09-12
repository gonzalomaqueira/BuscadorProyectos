package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "SinonimoMetodologiaTesting")
@NamedQueries({
	@NamedQuery(name = "SinonimoMetodologiaTesting.obtenerSinonimosMetodologiaTesting", query = "SELECT s FROM SinonimoMetodologiaTesting s WHERE s.metodologiaTesting = :idMetodologiaTesting") })
public class SinonimoMetodologiaTesting extends Sinonimo
{		
	@ManyToOne
	@JoinColumn(name = "IdMetodologiaTesting")
	private MetodologiaTesting metodologiaTesting;	
}