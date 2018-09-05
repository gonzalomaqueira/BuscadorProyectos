package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;

public interface MetodologiaTestingService {
	
	List<MetodologiaTesting> obtenerMetodologiasTesting();
	List<MetodologiaTesting> obtenerMetodologiasTestingCompleto();
	void add(MetodologiaTesting metodologia);
	void modify(MetodologiaTesting metodologia);
	void delete(MetodologiaTesting metodologia);
}
