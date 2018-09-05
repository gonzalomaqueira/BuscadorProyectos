package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "SinonimosModeloProceso")
@NamedQueries({
	@NamedQuery(name = "SinonimoModeloProceso.obtenerSinonimosModeloProceso", query = "SELECT s FROM SinonimoModeloProceso s WHERE s.id = :idModeloProceso") })
public class SinonimoModeloProceso extends Sinonimo
{
	@ManyToOne
	@JoinColumn(name = "IdModeloProceso")
	private ModeloProceso modeloProceso;	
}