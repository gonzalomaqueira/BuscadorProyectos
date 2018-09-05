package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoMetodologiaTesting;


public interface MetodologiaTestingDao {
	
	List<MetodologiaTesting> obtenerMetodologiasTesting();
	void add(MetodologiaTesting metodologia);
	void modify(MetodologiaTesting metodologia);
	void delete(MetodologiaTesting metodologia);
	List<SinonimoMetodologiaTesting> obtenerSinonimosMetodologiaTesting(long idMetodologiaTesting);

}
