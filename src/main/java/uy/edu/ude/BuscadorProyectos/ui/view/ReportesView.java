package uy.edu.ude.BuscadorProyectos.ui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.service.Fachada;

@SpringView
@SpringComponent
public class ReportesView extends ReportesViewDesign implements View{
	@Autowired
	Fachada fachada;
	
	public void enter(ViewChangeEvent event) {
		
				
		PDDocument pdDoc = null;
		PDFTextStripper pdfStripper;

		String parsedText;
		String fileName = "C:\\temp\\Resumenes\\JDBC1.pdf";
		try {

			pdDoc = PDDocument.load(new File(fileName));
			pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
			
	        String textoOriginal[] = parsedText.split("\\r?\\n");
	        ArrayList<String> titulos = new ArrayList<String>();
	        Proyecto proyecto= new Proyecto();
	        proyecto.setDocumentoPorSecciones(fachada.armarDocumentoPorSecciones(textoOriginal));
	        //System.out.println(parsedText);
	        
	        List<Tecnologia> vTecnologiasDelProyecto = fachada.obtenerTecnologiasProyecto(proyecto);	  	        
	        
	        for(Tecnologia tecnologia : vTecnologiasDelProyecto)
			{
	        	System.out.println(tecnologia.toString());
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
	        /*
	        for(String linea : proyecto.devolverAlumnos())
	        {
	        	System.out.println(linea);
	        }*/
	        
	        System.out.println("--------------------------------------------");
	        System.out.println("--------------------------------------------");
	        /*
	        for(String linea : proyecto.devolverTutor())
	        {
	        	System.out.println(linea);
	        }*/
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		link.setCaption("Hola!!!!!");
	}

}
