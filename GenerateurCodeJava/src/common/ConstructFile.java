package common;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.w3c.dom.Document;

import dependance.GestionDependance;
import genarate.VisitorLiaison;
import materialize.Gestion_dom;
import materialize.MaterializeDependance;
import materialize.MaterializeModel;
import model.Model;
import test.generateFiles.Repository2;
import test.generateFiles.Repository3;

public class ConstructFile {
	
	private ConstructFile() {
		
	}
	
	public static Model generationModel(String fichier){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeModel materializeModel = new MaterializeModel(document);
		return materializeModel.getResult();
	}
	
	public static GestionDependance generationDependance(String fichier,Model model){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeDependance materializeDependance = new MaterializeDependance(document,model);
		return materializeDependance.getResult();
	}
	
	public static Model generationInstance() {
		Model model = generationModel(Constants.FILE_MODEL);
		GestionDependance dependances = generationDependance(Constants.FILE_DEPENDANCE, model);
		VisitorLiaison visitor = new VisitorLiaison(model,dependances);
		model.accept(visitor);
		return model;
	}
	
	public static void createFile(String pathFile,String value) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(pathFile, "UTF-8");
			writer.write(value);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void generationInstanceRepo(String fichier,Model model,Repository3 rep) {
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		rep.materializeInstance(document);
	}
}
