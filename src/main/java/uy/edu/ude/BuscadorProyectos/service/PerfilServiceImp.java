package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.PerfilDao;
import uy.edu.ude.BuscadorProyectos.dao.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;

@Service
public class PerfilServiceImp implements PerfilService
{
	@Autowired
	private PerfilDao perfilDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Perfil> listPerfiles()
	{
		return perfilDao.listPerfiles();
	}
}
