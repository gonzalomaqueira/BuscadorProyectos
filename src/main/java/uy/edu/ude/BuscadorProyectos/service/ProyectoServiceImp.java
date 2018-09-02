package uy.edu.ude.BuscadorProyectos.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.ProyectoDao;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;

@Service
public class ProyectoServiceImp implements ProyectoService {

	@Autowired
	private ProyectoDao proyectoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Proyecto> listarProyectos() {
		return proyectoDao.listarProyectos();
	}

	@Transactional
	@Override
	public void add(Proyecto proyecto) {
		proyectoDao.add(proyecto);
	}

	@Transactional
	@Override
	public void modify(Proyecto proyecto) {
		proyectoDao.modify(proyecto);
	}

	@Transactional
	@Override
	public void delete(Proyecto proyecto) {
		proyectoDao.delete(proyecto);
	}
	
	public List<SeccionTexto> armarDocumentoPorSecciones(String textoOriginal[])
	{
		List<SeccionTexto> documentoPorSecciones = new ArrayList<SeccionTexto>();
        SeccionTexto seccion = null;
        ArrayList<String> contenido = new ArrayList<String>();
        boolean encontreTitulo = false;
		
        documentoPorSecciones.add(armarSeccionAlumnos(textoOriginal));
        documentoPorSecciones.add(armarSeccionTutor(textoOriginal));        
         
        List<String> Textolista = new ArrayList<String>(Arrays.asList(textoOriginal));
        
        for (String linea : textoOriginal)
        {
        	if (!FuncionesTexto.esTituloResumen(linea))
        	{
        		Textolista.remove(0);
        	}
        	else
        		break;
        }
        
		for (String linea : Textolista)
        {
    	    if (FuncionesTexto.esTitulo(linea))
    	    {
    	    	encontreTitulo = true;
    	    	if (seccion != null)
    	    	{
    	    		seccion.setContenido(contenido);
    	    		documentoPorSecciones.add(seccion);
    	    	}
    	    	seccion = new SeccionTexto();
    	    	contenido = new ArrayList<String>();
    	    	seccion.setTitulo(linea);
			}
    	    else
    	    {
    	    	if (encontreTitulo && contenido != null)
    	    	{
    	    		contenido.add(linea);
    	    	}        	    	
    	    }
        }
		if (seccion != null)
		{
			seccion.setContenido(contenido);
			documentoPorSecciones.add(seccion);
		}
		
		return documentoPorSecciones;
	}
	
	public SeccionTexto armarSeccionAlumnos(String texto[])
	{
        SeccionTexto seccion = null;
        ArrayList<String> contenido = new ArrayList<String>();
        boolean encontreTituloAlumno = false;
        
		for (String linea : texto)
        {
    	    if (FuncionesTexto.esTituloTutor(linea))
    	    	break;
    	    
			if (FuncionesTexto.esTituloAlumnos(linea))
    	    {
    	    	encontreTituloAlumno = true;
    	    	if (seccion != null)
    	    	{
    	    		seccion.setContenido(contenido);
    	    	}
    	    	seccion = new SeccionTexto();
    	    	contenido = new ArrayList<String>();
    	    	seccion.setTitulo(linea);
			}
    	    else
    	    {
    	    	if (encontreTituloAlumno && !FuncionesTexto.esTituloTutor(linea))
    	    	{
    	    		contenido.add(linea);
    	    	}        	    	
    	    }
        }
		if (seccion != null)
		{
			seccion.setContenido(contenido);
		}
		return seccion;
	}
	
	public SeccionTexto armarSeccionTutor(String texto[])
	{
        SeccionTexto seccion = null;
        ArrayList<String> contenido = new ArrayList<String>();
        boolean encontreTituloAlumno = false;
        
		for (String linea : texto)
        {
    	    if (FuncionesTexto.esTituloResumen(linea))
    	    	break;
    	    
			if (FuncionesTexto.esTituloTutor(linea))
    	    {
    	    	encontreTituloAlumno = true;
    	    	if (seccion != null)
    	    	{
    	    		seccion.setContenido(contenido);
    	    	}
    	    	seccion = new SeccionTexto();
    	    	contenido = new ArrayList<String>();
    	    	seccion.setTitulo(linea);
			}
    	    else
    	    {
    	    	if (encontreTituloAlumno && !FuncionesTexto.esTituloResumen(linea))
    	    	{
    	    		contenido.add(linea);
    	    	}        	    	
    	    }
        }
		if (seccion != null)
		{
			seccion.setContenido(contenido);
		}
		return seccion;
	}	
	
	@Transactional
	@Override
	public List<Tecnologia> obtenerTecnologiasProyecto (Proyecto proyecto, List<Tecnologia> vTecnologias)
	{
		List<Tecnologia> vListaRetorno = new ArrayList<Tecnologia>();
		if (proyecto.getDocumentoPorSecciones() != null)
		{
			for(Tecnologia tecnologia : vTecnologias)
			{
				for(SeccionTexto seccion : proyecto.getDocumentoPorSecciones())
				{
					if(FuncionesTexto.seccionContieneTexto(seccion, tecnologia.getNombre()))
					{
						vListaRetorno.add(tecnologia);
						break;
					}
					else
					{
						for (SinonimoTecnologia sinonimo: tecnologia.getSinonimos())
						{
							if(FuncionesTexto.seccionContieneTexto(seccion, sinonimo.getNombre()))
							{
								vListaRetorno.add(tecnologia);
								break;
							}
						}
					}
				}
			}
		}
		return vListaRetorno;
	}
	

}
