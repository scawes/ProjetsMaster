package test.generateFiles;

import java.util.ArrayList;

import org.w3c.dom.Document;

import common.Constants;
import common.ConstructFile;
import dependance.GestionDependance;
import genarate.VisitorVerification;
import materialize.Gestion_dom;
import materialize.MaterializeDependance;
import materialize.MaterializeModel;
import model.Model;

public class ExecutionInstance {
	
	

	public static void main(String[] args) {
		Instanciation();
	}
	
	static void exportInstance(Model model) {
		Repository3 rep = new Repository3();
		rep.setModel(model);
		
		Satellite s2 = new Satellite("test2",null,null,102);
		Satellite s1 = new Satellite("test1",s2,null,101);
		ArrayList<Satellite> listS = new ArrayList<Satellite>();
		listS.add(s1);
		listS.add(s2);
		Flotte flotte = new Flotte();
		flotte.setlistS(listS);
				
		rep.addInstance(s2);
		rep.addInstance(s1);
		rep.addInstance(flotte);
		System.out.println("---> INSTANCES :");
		System.out.println(rep.exportListInstanceToXML());
		ConstructFile.createFile(Constants.FILE_INSTANCE, rep.exportListInstanceToXML());
		
	}
	
	static void importInstance(Model model) {
		
		Repository3 rep = new Repository3();
		rep.setModel(model);
		ConstructFile.generationInstanceRepo("Save1.xml",model,rep);
		System.out.println("---> INSTANCES :");
		System.out.println(rep.exportListInstanceToXML());
		Flotte flotte = (Flotte)rep.getInstance(1);
		//Flotte flotteRestore = rep.restoreFlotte(rep.getInstance(1));
		System.out.println(flotte.toString());
		//createFile("Save1.xml", rep.exportListInstanceToXML());
		//System.out.println(rep.export());
		
	}
	
	
	
	static void Instanciation() {
		Model racine = ConstructFile.generationModel(Constants.FILE_MODEL);
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
		exportInstance(racine);
		importInstance(racine);
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

}
