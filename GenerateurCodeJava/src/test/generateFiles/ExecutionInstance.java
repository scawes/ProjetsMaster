package test.generateFiles;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.w3c.dom.Document;

import dependance.GestionDependance;
import genarate.VisitorLiaison;
import genarate.VisitorVerification;
import materialize.Gestion_dom;
import materialize.MaterializeDependance;
import materialize.MaterializeModel;
import model.Model;

public class ExecutionInstance {
	
	final static String fichierModel = "programme1.xml";
	final static String fichierDependance = "dependance.xml";
	final static String fichierInstance = "Save1.xml";

	public static void main(String[] args) {
		Instanciation();
	}
	
	static void exportInstance(Model model) {
		ArrayList<Satellite> listS = new ArrayList<Satellite>();
		listS.add(new Satellite("test1",null,null,101));
		Flotte flotte = new Flotte();
		flotte.setlistS(listS);
		Repository2 rep = new Repository2();
		rep.setModel(model);
		rep.saveInstance(flotte);
		System.out.println("---> INSTANCES :");
		System.out.println(rep.exportListInstanceToXML());
		createFile(fichierInstance, rep.exportListInstanceToXML());
		
	}
	
	static void importInstance(Model model) {
		
		Repository2 rep = new Repository2();
		rep.setModel(model);
		generationInstanceRepo("Save1.xml",model,rep);
		System.out.println("---> INSTANCES :");
		System.out.println(rep.exportListInstanceToXML());
		Flotte flotteRestore = rep.restoreFlotte(rep.getInstance(1));
		System.out.println(flotteRestore.toString());
		//createFile("Save1.xml", rep.exportListInstanceToXML());
		//System.out.println(rep.export());
		
	}
	
	static void generationInstanceRepo(String fichier,Model model,Repository2 rep) {
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		rep.MaterializeInstance(document);
		//MaterializeModel materializeModel = new MaterializeModel(document);
		//return materializeModel.getResult();
	}
	
	static void Instanciation() {
		Model racine = generationModel();
		VisitorVerification verif = new VisitorVerification();
		racine.accept(verif);
		if(!verif.getResult()) {
			System.out.println("---> ERROR");
			System.out.println(verif.getDetail());
			return;
		}
		//VisitorGenerateJava v = new VisitorGenerateJava();
		//racine.accept(v);
		//System.out.println("---> CODE :");
		//System.out.println(v.getResult());
		//extractionInstance(racine);
		importInstance(racine);
	}
	
	static Model generationModel() {
		Model model = XMLtoModel(fichierModel);
		GestionDependance dependances = XMLtoDependance(fichierDependance, model);
		VisitorLiaison visitor = new VisitorLiaison(model,dependances);
		model.accept(visitor);
		return model;
	}
	
	static Model XMLtoModel(String fichier){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeModel materializeModel = new MaterializeModel(document);
		return materializeModel.getResult();
	}
	
	static GestionDependance XMLtoDependance(String fichier,Model model){
		Gestion_dom dom = new Gestion_dom(fichier);
		Document document = dom.generer_document();
		MaterializeDependance materializeDependance = new MaterializeDependance(document,model);
		return materializeDependance.getResult();
	}
	
	static void createFile(String pathFile,String value) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(pathFile, "UTF-8");
			writer.write(value);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
