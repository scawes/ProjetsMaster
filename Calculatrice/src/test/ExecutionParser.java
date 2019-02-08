package test;

import org.w3c.dom.Document;

import extracteur.Genere_modele_dom_variable;
import extracteur.Gestion_dom;
import model.Programme;
import visiteur.VisiteurAffichage;
import visiteur.VisiteurExecution;
import visiteur.VisiteurVerification;

public class ExecutionParser {

	public static void main(String[] args) {
		String fichier = "fichierVariable.xml";
		if(verification(fichier)) return;
		
		affichage(fichier);
		
		calcul(fichier);
		
	}
	
	static Programme transformFichier(String fichier){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		Genere_modele_dom_variable modele = new Genere_modele_dom_variable(document);
		return modele.getResult();
	}
	
	static void calcul(String fichier) {
		Programme racine = transformFichier(fichier);
		VisiteurExecution v = new VisiteurExecution();
		System.out.println("---> EXECUTION");
		racine.accept(v);
	}
	
	static void affichage(String fichier) {
		Programme racine = transformFichier(fichier);
		VisiteurAffichage v = new VisiteurAffichage();
		racine.accept(v);
		System.out.println("---> CODE :");
		System.out.println(v.getResult());
	}
	
	static boolean verification(String fichier) {
		Programme racine = transformFichier(fichier);
		VisiteurVerification v = new VisiteurVerification();
		racine.accept(v);
		System.out.print("---> VERIFICATION : ");
		if(v.getResult())System.out.println("KO");
		else System.out.println("OK");
		return v.getResult();
	}

}
