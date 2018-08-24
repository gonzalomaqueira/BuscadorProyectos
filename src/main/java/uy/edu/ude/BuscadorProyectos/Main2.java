package uy.edu.ude.BuscadorProyectos;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.cos.COSDocument;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;

public class Main2 {
	
	public static void main(String[] args) 
	{
		Main2 p = new Main2();
        p.start(args);
	}
		
	@Autowired
	Fachada fachada;
	
	public void start(String[] args) {

		PDDocument pdDoc = null;
		PDFTextStripper pdfStripper;

		String parsedText;
		String fileName = "C:\\temp\\Resumenes\\JDBC2.pdf";

		try {

			pdDoc = PDDocument.load(new File(fileName));
			pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
			
	        String textoOriginal[] = parsedText.split("\\r?\\n");
	        ArrayList<String> titulos = new ArrayList<String>();
	        Proyecto proyecto= new Proyecto();
	        //proyecto.setDocumentoPorSecciones(FachadaLogicaImp.armarDocumentoPorSecciones(textoOriginal));
	        
	        
	        List<Tecnologia> vTecnologias = fachada.obtenerTecnologias();
	        	        
	        if(vTecnologias != null)
			{	
	        	for(Tecnologia tec : vTecnologias)
				{
					tec.toString();
				}
			}
	        
	        /*
	        for(String linea : proyecto.devolverResumen())
	        {	        	
	        	System.out.println(linea);
	        }
	        
	        for(SeccionTexto sec : proyecto.getDocumentoPorSecciones())
	        {	        	
	        	sec.listarSeccion();
	        }
	        */
	        
	        for(String linea : proyecto.devolverAlumnos())
	        {
	        	System.out.println(linea);
	        }
	        
	        System.out.println("--------------------------------------------");
	        System.out.println("--------------------------------------------");
	        
	        for(String linea : proyecto.devolverTutor())
	        {
	        	System.out.println(linea);
	        }
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
	}
		
}
