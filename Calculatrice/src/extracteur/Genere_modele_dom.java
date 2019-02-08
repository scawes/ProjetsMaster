package extracteur;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Element;
import model.ExpBinaire;
import model.Expr;
import model.Programme;
import model.operateur.MultExp;
import model.operateur.PlusExp;
import model.valeur.IntExp;

public class Genere_modele_dom {

	Map<String, Node> tabId;

	Expr expression;
	Document document;
	Programme programme;

	public Genere_modele_dom(Document document) {
		//table d'id
		tabId = new HashMap<String, Node>();
		this.document = document;
		parcoursId((Node) document.getDocumentElement());
		Origine((Node) document.getDocumentElement());
	}

	public Expr getResult() {
		return expression;
	}

	public void parcoursId(Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return;
		//si le node poss�de un id
		if (element.getAttributes().getNamedItem("id") != null) {
			//ajouter le node � la table
			tabId.put(element.getAttributes().getNamedItem("id").getNodeValue(), element);
		}
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			parcoursId((Node) nl.item(i));
		}
	}
	//recherche de la premiere op�ration
	public void Origine(Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return;
		//si l'element ne poss�de pas d'id
		if (element.getAttributes().getNamedItem("id") == null) {
			programme = new Programme(null);
			//generer l'�l�ment
			Expr resultat = Generation(programme,element);
			//si un �l�ment � �t� g�n�r�
			if(resultat!=null) {
				//affecter le resultat
				expression = resultat;
			}	
		}
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Origine((Node) nl.item(i));
		}
	}
	
	public Expr Generation(Element parent,Node element) {
		//intencier l'�l�ment
		switch (element.getNodeName()) {
			//plus
			case "PlusExp":
				ExpBinaire expPlus = new PlusExp(parent);
				//retrouver le node depuis l'id dans la table
				String p1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				//generer l'�l�ment gauche
				expPlus.setOpg(Generation(expPlus,tabId.get(p1)));
				//retrouver le node depuis l'id dans la table
				String p2 = element.getAttributes().getNamedItem("opd").getNodeValue();
				//generer l�l�ment droit
				expPlus.setOpd(Generation(expPlus,tabId.get(p2)));
				return expPlus;
			//multipli�
			case "MultExp":
				ExpBinaire expMult = new MultExp(parent);
				//generer l'�l�ment gauche
				String m1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				expMult.setOpg(Generation(expMult,tabId.get(m1)));
				//generer l'�l�ment droit
				String m2 = element.getAttributes().getNamedItem("opd").getNodeValue();
				expMult.setOpd(Generation(expMult,tabId.get(m2)));
				return expMult;
			//valeur
			case "IntExp":
				return new IntExp(parent,Integer.valueOf(element.getAttributes().getNamedItem("val").getNodeValue()));
			default:
				return null;
			}
	}
}
