package test;

import org.w3c.dom.Document;

import dependance.GestionDependance;
import genarate.VisitorGenerateJava;
import genarate.VisitorLiaison;
import genarate.VisitorVerification;
import materialize.Gestion_dom;
import materialize.MaterializeDependance;
import materialize.MaterializeModel;
import model.Model;


public class Execution {

	final static String fichierModel = "programme1.xml";
	final static String fichierDependance = "dependance.xml";
	
	public static void main(String[] args) {

		//String fichierModel = "programme1.xml";
		affichage();
	}
	
	static void affichage() {
		Model racine = generationInstance();
		VisitorVerification verif = new VisitorVerification();
		racine.accept(verif);
		if(!verif.getResult()) {
			System.out.println("---> ERROR");
			System.out.println(verif.getDetail());
			return;
		}
		VisitorGenerateJava v = new VisitorGenerateJava();
		racine.accept(v);
		System.out.println("---> CODE :");
		System.out.println(v.getResult());
	}

	static Model GenerationModel(String fichier){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeModel materializeModel = new MaterializeModel(document);
		return materializeModel.getResult();
	}
	
	static GestionDependance GenerationDependance(String fichier,Model model){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeDependance materializeDependance = new MaterializeDependance(document,model);
		return materializeDependance.getResult();
	}
	
	static Model generationInstance() {
		Model model = GenerationModel(fichierModel);
		GestionDependance dependances = GenerationDependance(fichierDependance, model);
		VisitorLiaison visitor = new VisitorLiaison(model,dependances);
		model.accept(visitor);
		return model;
	}
}
