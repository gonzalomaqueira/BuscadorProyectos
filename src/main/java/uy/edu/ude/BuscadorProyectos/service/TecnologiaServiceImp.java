package uy.edu.ude.BuscadorProyectos.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.CategoriaDao;
import uy.edu.ude.BuscadorProyectos.dao.TecnologiaDao;
import uy.edu.ude.BuscadorProyectos.dao.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;

@Service
public class TecnologiaServiceImp implements TecnologiaService {

	@Autowired
	private TecnologiaDao tecnologiaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Tecnologia> obtenerTecnologias() 
	{
		return tecnologiaDao.obtenerTecnologias();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Tecnologia> obtenerTecnologiasCompleto()
	{
		List<Tecnologia> vRetorno = tecnologiaDao.obtenerTecnologias();
		for(Tecnologia tec: vRetorno)
		{
			tec.setSinonimos(tecnologiaDao.obtenerSinonimosTecnologia(tec.getId()));
		}
		return vRetorno;
	}
	
	
	@Transactional
	@Override
	public void add(Tecnologia tecnologia)
	{
		tecnologiaDao.add(tecnologia);		
	}

	@Transactional
	@Override
	public void modify(Tecnologia tecnologia) 
	{
		tecnologiaDao.modify(tecnologia);		
	}

	@Transactional
	@Override
	public void delete(Tecnologia tecnologia)
	{
		tecnologiaDao.delete(tecnologia);		
	}
	
	@Transactional
	@Override
	public List<Tecnologia> obtenerTecnologiasCompletoPorCategoria(Categoria categoria)
	{
		List<Tecnologia> vRetorno = tecnologiaDao.obtenerTecnologiasPorCategoria(categoria.getId());
		for(Tecnologia tec: vRetorno)
		{
			tec.setSinonimos(tecnologiaDao.obtenerSinonimosTecnologia(tec.getId()));
		}
		return vRetorno;
	}
	
   @Transactional
   @Override
   public void altaTecnologia(String nombreTecnologia, int idCategoria)
   {
	   Tecnologia tecnologia = new Tecnologia(nombreTecnologia, new Categoria(idCategoria), new ArrayList<SinonimoTecnologia>());
	   this.add(tecnologia);
   }
   
   @Transactional
   @Override
   public void eliminarTecnologia(int id) 
   {		
	   Tecnologia tecnologia = this.obtenerTecnologiaPorId(id);
	   this.delete(tecnologia);
   }
   
   private Tecnologia obtenerTecnologiaPorId(int id) 
   {
	   return tecnologiaDao.obtenerTecnologiaPorId(id);
   }

@Transactional
   @Override
   public void modificarTecnologia(int idTecnologia, String nombre, int idCategoria)
   {
	   Tecnologia tecnologia = new Tecnologia();
	   tecnologia.setId(idTecnologia);
	   tecnologia.setNombre(nombre);
	   Categoria categoria= new Categoria();
	   categoria.setId(idCategoria);
	   tecnologia.setCategoria(categoria);
	   this.modify(tecnologia);
   }



}
