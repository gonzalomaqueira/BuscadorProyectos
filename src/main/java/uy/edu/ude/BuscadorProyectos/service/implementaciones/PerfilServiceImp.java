package uy.edu.ude.BuscadorProyectos.service.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.PerfilDao;
import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.service.interfaces.PerfilService;

@Service
public class PerfilServiceImp implements PerfilService
{
	@Autowired
	private PerfilDao perfilDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Perfil> obtenerPerfiles()
	{
		return perfilDao.obtenerPerfiles();
	}
}
