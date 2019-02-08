package import_xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Garage;
import model.Moto;
import model.Societe;
import model.Voiture;

public class SocieteHandler_Attribus extends DefaultHandler{

	Societe societe;
	Garage garage;
	Voiture voiture;
	Moto moto;
	
	public void startDocument() throws SAXException {
		societe = new Societe();
	}
	
	public Societe getResult(){
		return societe;
	}
	
	public void endDocument() throws SAXException {
		System.out.println("done");
	}
	
	public void startElement(String nameSpaceURI, String localName,
			String rawName, Attributes attributs) throws SAXException {
		
		if (localName.equals("Societe")) {
			societe.setNom(attributs.getValue("nom_societe"));
		}
		if (localName.equals("Garage")) {
			garage = new Garage();
			garage.setNom(attributs.getValue("Nom_Garage"));
			garage.setAdresse(attributs.getValue("Adresse_Garage"));
			societe.add_garage(garage);
		}
		if (localName.equals("Voiture")) {
			voiture = new Voiture();
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			try {
				Date date = dateFormat.parse(attributs.getValue("Date_arrive"));
				voiture.setDate_arrive(date);
				date = dateFormat.parse(attributs.getValue("ControleTechnique"));
				voiture.setControle_technique(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			voiture.setMotif(attributs.getValue("Motif"));

			garage.add_reparation(voiture);
		}
		if (localName.equals("Moto")) {
			moto = new Moto();
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			try {
				Date date = dateFormat.parse(attributs.getValue("Date_arrive"));
				moto.setDate_arrive(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			moto.setMotif(attributs.getValue("Motif"));
			moto.setSd(Boolean.valueOf(attributs.getValue("SD")));
			garage.add_reparation(moto);
		}
	}
	
	/*public void characters(char[] ch, int start, int end) throws
	SAXException {
		if (dans_nom_societe) {
			societe.setNom(new String(ch,start,end));
		}
		if (dans_Nom_Garage) {
			garage.setNom(new String(ch,start,end));
		}
		if (dans_Adresse_Garage) {
			garage.setAdresse(new String(ch,start,end));
		}
		if (dans_Date_arrive) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			try {
				Date date = dateFormat.parse(new String(ch,start,end));
				reparation.setDate_arrive(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (dans_Motif) {
			reparation.setMotif(new String(ch,start,end));
		}
		if (dans_ControleTechnique) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			try {
				Date date = dateFormat.parse(new String(ch,start,end));
				voiture.setControle_technique(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (dans_SD) {
			moto.setSd(new Boolean(new String(ch,start,end)));
		}
		
		
	}*/
	
	
	
}
