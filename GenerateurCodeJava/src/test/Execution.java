package test;

import org.w3c.dom.Document;

import genarate.VisitorGenerateJava;
import materialize.Gestion_dom;
import materialize.MaterializeModel;
import model.Model;


public class Execution {

	public static void main(String[] args) {

		String fichier = "programme1.xml";
		affichage(fichier);
	}
	
	static void affichage(String fichier) {
		Model racine = transformFichier(fichier);
		VisitorGenerateJava v = new VisitorGenerateJava();
		racine.accept(v);
		System.out.println("---> CODE :");
		System.out.println(v.getResult());
	}

	static Model transformFichier(String fichier){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeModel modele = new MaterializeModel(document);
		return modele.getResult();
	}
}
