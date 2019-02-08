package extracteur;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Gestion_dom {

	Document document;
	DocumentBuilderFactory factory;
	String fichier;
	
	public Gestion_dom() {
		this("FichierXML.xml");
	}
	
	public Gestion_dom(String fichier) {
		this.fichier = fichier;
		factory = DocumentBuilderFactory.newInstance();
		
	}

	public Document generer_document() {
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			File xml = new File(fichier);
			document = builder.parse(xml);
			return document;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void generer_xml(Document document) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			// Ecrire dans un fichier
			StreamResult f = new StreamResult(new File(fichier));
			transformer.transform(source, f);
			// Ecrire sur la sortie standard
			StreamResult c = new StreamResult(System.out);
			transformer.transform(source, c);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}