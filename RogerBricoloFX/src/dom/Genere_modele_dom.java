package dom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import model.Garage;
import model.Societe;

public class Genere_modele_dom{

	Societe societe;
	Garage garage;
	Document document;
	
	public Genere_modele_dom(Document document) {
		this.document=document;
		//societe = new Societe();
		
		parcours((Element)document.getDocumentElement());
	}
	
	public Societe getResult(){
		return societe;
	}
	
	public void parcours(Element element){
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		
		switch (element.getTagName()) {
	        case "Societe":
	        	societe = new Societe(element.getAttribute("nom_societe"));
	        	break;
	        case "Garage":
	        	garage = societe.add_garage(element.getAttribute("Nom_Garage"), element.getAttribute("Adresse_Garage"));
	        	break;
	        case "Voiture":
	        	
				try {
					Date date_arrive = dateFormat.parse(element.getAttribute("Date_arrive"));
					Date date_CT = dateFormat.parse(element.getAttribute("ControleTechnique"));
					garage.add_voiture(date_arrive, element.getAttribute("Motif"), date_CT);
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	break;
	        case "Moto":
				try {
					Date date_arrive = dateFormat.parse(element.getAttribute("Date_arrive"));
					garage.add_moto(date_arrive, element.getAttribute("Motif"), Boolean.valueOf(element.getAttribute("SD")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	break;
	        default: 
	        	break;
		}	
		NodeList nl = element.getChildNodes();
		for(int i=0;i<nl.getLength();i++) {
			parcours((Element) nl.item ( i ));
		}
	}
}
