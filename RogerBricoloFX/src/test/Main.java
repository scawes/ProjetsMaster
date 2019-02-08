package test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dom.Genere_modele_dom;
import dom.Gestion_dom;
import import_xml.SocieteHandler_Attribus;
import model.Garage;
import model.Societe;
import visitor.VisiteurDOM_Attribus;
import visitor.VisiteurXML_Attribus;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Societe societe = new Societe("Roger");
		Garage g = societe.add_garage("MonGarage", "Brest");//new Garage();
		g.add_voiture(new Date(), "test",new Date());
		g.add_moto(new Date(), "test",true);
		g.add_voiture(new Date(), "test2",new Date());
		
		VisiteurDOM_Attribus visiteur = new VisiteurDOM_Attribus();
		visiteur.visit(societe);
		System.out.println(visiteur.to_dom().toString());
		Gestion_dom dom = new Gestion_dom();
		dom.generer_xml(visiteur.to_dom());
		/*
		VisiteurXML_Attribus visiteur = new VisiteurXML_Attribus();
		societe.accept(visiteur);
		//System.out.println(visiteur.to_xml());
		InitialiseXML fichier = new InitialiseXML("roger",visiteur.to_dtd());
		fichier.AjouteElementXML(visiteur.to_xml());
		fichier.Ferme();*/
		/*
		
		VisiteurXML_Attribus visiteur2 = new VisiteurXML_Attribus();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			 factory.setNamespaceAware(true);
			 SAXParser parser = factory.newSAXParser();
			 File file = new File("roger.xml");
			 DefaultHandler handler = new SocieteHandler_Attribus();
			 parser.parse(file, handler);
			 ((SocieteHandler_Attribus)handler).getResult().accept(visiteur2);
			 System.out.println(visiteur2.to_xml());
		 } catch (ParserConfigurationException pce) {
		 } catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} */
		
		Gestion_dom dom = new Gestion_dom();
		Document document = dom.generer_document();
		Genere_modele_dom modele = new Genere_modele_dom(document);
		Societe societe = modele.getResult();
		
		VisiteurXML_Attribus visiteur = new VisiteurXML_Attribus();
		societe.accept(visiteur);
		System.out.println(visiteur.to_xml());
	}

}
