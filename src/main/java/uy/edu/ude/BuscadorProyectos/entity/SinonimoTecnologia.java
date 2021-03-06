package uy.edu.ude.BuscadorProyectos.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SinonimosTecnologia")
@NamedQueries({
	@NamedQuery(name = "SinonimoTecnologia.obtenerSinonimosTecnologia", query = "SELECT s FROM SinonimoTecnologia s WHERE s.tecnologia = :idTecnologia") })

public class SinonimoTecnologia extends Sinonimo
{	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdTecnologia")
	private Tecnologia tecnologia;

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}
}
