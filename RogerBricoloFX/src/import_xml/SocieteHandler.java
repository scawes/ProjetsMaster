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
import model.Reparation;
import model.Societe;
import model.Voiture;

public class SocieteHandler extends DefaultHandler{

	Societe societe;
	boolean dans_nom_societe = false, 
			dans_Garage = false, 
			dans_Nom_Garage = false,
			dans_Adresse_Garage = false,
			dans_Voiture = false,
			dans_Moto = false,
			dans_Date_arrive = false,
			dans_Motif = false,
			dans_ControleTechnique = false,
			dans_SD = false	;
	
	Garage garage;
	Reparation reparation;
	Voiture voiture;
	Moto moto;
/*	
	public SocieteHandler(){
		
	}*/
	
	public void startDocument() throws SAXException {
		societe = new Societe();
	}
	
	public Societe getResult(){
		return societe;
	}
	
	public void endDocument() throws SAXException {
		System.out.println("done");
	}
	
	@Override
	public void endElement(String arg0, String arg1, String arg2) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(arg0, arg1, arg2);
		dans_nom_societe = false;
		dans_Garage = false;
		dans_Nom_Garage = false;
		dans_Adresse_Garage = false;
		dans_Voiture = false;
		dans_Moto = false;
		dans_Date_arrive = false;
		dans_Motif = false;
		dans_ControleTechnique = false;
		dans_SD = false;
	}

	public void startElement(String nameSpaceURI, String localName,
			String rawName, Attributes attributs) throws SAXException {
		if (localName.equals("nom_societe")) {
			dans_nom_societe = true;
		}
		if (localName.equals("Garage")) {
			dans_Garage = true;
			garage = new Garage();
			societe.add_garage(garage);
		}
		if (localName.equals("Nom_Garage")) {
			dans_Nom_Garage = true;
		}
		if (localName.equals("Adresse_Garage")) {
			dans_Adresse_Garage = true;
		}
		if (localName.equals("Voiture")) {
			dans_Voiture = true;
			voiture = new Voiture();
			reparation = voiture;
			garage.add_reparation(voiture);
		}
		if (localName.equals("Moto")) {
			dans_Moto = true;
			moto = new Moto();
			reparation = moto;
			garage.add_reparation(moto);
		}
		if (localName.equals("Date_arrive")) {
			dans_Date_arrive = true;
		}
		if (localName.equals("Motif")) {
			dans_Motif = true;
		}
		if (localName.equals("ControleTechnique")) {
			dans_ControleTechnique = true;
		}
		if (localName.equals("SD")) {
			dans_SD = true;
		}
	}
	
	public void characters(char[] ch, int start, int end) throws
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
		
		
	}
	
	
	
}
