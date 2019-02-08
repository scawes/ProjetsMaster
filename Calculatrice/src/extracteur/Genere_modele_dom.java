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
		//si le node possède un id
		if (element.getAttributes().getNamedItem("id") != null) {
			//ajouter le node à la table
			tabId.put(element.getAttributes().getNamedItem("id").getNodeValue(), element);
		}
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			parcoursId((Node) nl.item(i));
		}
	}
	//recherche de la premiere opération
	public void Origine(Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return;
		//si l'element ne possède pas d'id
		if (element.getAttributes().getNamedItem("id") == null) {
			programme = new Programme(null);
			//generer l'élément
			Expr resultat = Generation(programme,element);
			//si un élément à été généré
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
		//intencier l'élément
		switch (element.getNodeName()) {
			//plus
			case "PlusExp":
				ExpBinaire expPlus = new PlusExp(parent);
				//retrouver le node depuis l'id dans la table
				String p1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				//generer l'élément gauche
				expPlus.setOpg(Generation(expPlus,tabId.get(p1)));
				//retrouver le node depuis l'id dans la table
				String p2 = element.getAttributes().getNamedItem("opd").getNodeValue();
				//generer lélément droit
				expPlus.setOpd(Generation(expPlus,tabId.get(p2)));
				return expPlus;
			//multiplié
			case "MultExp":
				ExpBinaire expMult = new MultExp(parent);
				//generer l'élément gauche
				String m1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				expMult.setOpg(Generation(expMult,tabId.get(m1)));
				//generer l'élément droit
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
