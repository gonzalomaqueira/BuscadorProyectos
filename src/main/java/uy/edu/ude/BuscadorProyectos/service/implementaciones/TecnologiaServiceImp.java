package uy.edu.ude.BuscadorProyectos.service.implementaciones;

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
import uy.edu.ude.BuscadorProyectos.dao.interfaces.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entidades.Categoria;
import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.entidades.Proyecto;
import uy.edu.ude.BuscadorProyectos.entidades.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entidades.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entidades.Usuario;
import uy.edu.ude.BuscadorProyectos.service.interfaces.TecnologiaService;
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
		tecnologiaDao.agregar(tecnologia);		
	}

	@Transactional
	@Override
	public void modify(Tecnologia tecnologia) 
	{
		tecnologiaDao.modificar(tecnologia);		
	}

	@Transactional
	@Override
	public void delete(Tecnologia tecnologia)
	{
		tecnologiaDao.eliminar(tecnologia);		
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
	   this.agregar(tecnologia);
   }
   
   @Transactional
   @Override
   public void eliminarTecnologia(int id) 
   {
	   Tecnologia tecnologia = this.obtenerTecnologiaPorId(id);
	   for (Proyecto proy: tecnologia.getProyectos())
	   {
		   proy.getTecnologias().remove(tecnologia);
	   }
	   tecnologia.getProyectos().removeAll(tecnologia.getProyectos());
	   this.eliminar(tecnologia);
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
	   this.modificar(tecnologia);
   }

   @Transactional
   @Override
   public void altaSinonimoTecnologia(String nombreSinonimo, int idTecnologia)
   {
	   SinonimoTecnologia sinonimo = new SinonimoTecnologia();
	   sinonimo.setNombre(nombreSinonimo);
	   sinonimo.setTecnologias(this.obtenerTecnologiaPorId(idTecnologia));
	   tecnologiaDao.agregarSinonimo(sinonimo);
   }
   
   @Transactional
   @Override
   public void modificarSinonimoTecnologia(int idSinonimo, String nombreSinonimo)
   {
	   SinonimoTecnologia sinonimo = new SinonimoTecnologia();
	   sinonimo.setId(idSinonimo);
	   sinonimo.setNombre(nombreSinonimo);
	   tecnologiaDao.modificarSinonimo(sinonimo);
   }

   @Transactional
   @Override
   public void eliminarSinonimoTecnologia(int idSinonimo)
   {
	   SinonimoTecnologia sinonimo = new SinonimoTecnologia();
	   sinonimo.setId(idSinonimo);
	   tecnologiaDao.eliminarSinonimo(sinonimo);
   }
}
