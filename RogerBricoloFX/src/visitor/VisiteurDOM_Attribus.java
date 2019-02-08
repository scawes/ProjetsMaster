package visitor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import model.Garage;
import model.Moto;
import model.Reparation;
import model.Societe;
import model.Voiture;

public class VisiteurDOM_Attribus implements Visiteur{
	Document document;
	
	public VisiteurDOM_Attribus(){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		
		try {
			docBuilder = docFactory.newDocumentBuilder();
			document = docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Document to_dom() {
		return document;
	}

	
	@Override
	public void visit(Garage o) {
		Element e = document.createElement ("Garage");
		e.setAttribute("Nom_Garage", o.getNom());
		e.setAttribute("Adresse_Garage", o.getAdresse());
		//Element docelt =(Element) document.getDocumentElement().getLastChild();
		Element docelt =(Element) document.getLastChild();
		docelt.appendChild( e );
		for(Reparation obj : o.get_List_Reparation()){
			obj.accept(this);
		}
	}

	@Override
	public void visit(Reparation o) {

	}

	@Override
	public void visit(Voiture o) {
		Element e = document.createElement ("Voiture");
		e.setAttribute("Date_arrive", o.getDate_arrive().toString());
		e.setAttribute("Motif", o.getMotif());
		e.setAttribute("ControleTechnique", o.getControle_technique().toString());
		Element docelt =(Element) document.getLastChild().getLastChild();
		docelt.appendChild( e );
	}

	@Override
	public void visit(Moto o) {
		Element e = document.createElement ("Moto");
		e.setAttribute("Date_arrive", o.getDate_arrive().toString());
		e.setAttribute("Motif", o.getMotif());
		e.setAttribute("SD", String.valueOf(o.isSd()));
		Element docelt =(Element) document.getLastChild().getLastChild();
		docelt.appendChild( e );

	}

	@Override
	public void visit(Societe societe) {
		Element e = document.createElement ("Societe");
		e.setAttribute("nom_societe", societe.getNom());
		//Element docelt = document.getDocumentElement();
		document.appendChild(e);
		//docelt.appendChild ( e );
		for(Garage obj : societe.getList_garage()){
			obj.accept(this);
		}
	}

}
