package test;

import common.Constants;
import common.ConstructFile;
import genarate.GenerateInterfaceVisit;
import genarate.VisitorGenerateFileJava;
import genarate.VisitorGenerateJava;
import genarate.VisitorVerification;
import model.Model;


public class Execution {

	
	
	public static void main(String[] args) {

		//String fichierModel = "programme1.xml";
		//affichage();
		generateFile();
		//affichage();
	}
	
	static void affichage() {
		Model racine = ConstructFile.generationInstance();
		VisitorVerification verif = new VisitorVerification();
		racine.accept(verif);
		if(!verif.getResult()) {
			System.out.println("---> ERROR");
			System.out.println(verif.getDetail());
			return;
		}
		GenerateInterfaceVisit.generate(racine);
		VisitorGenerateJava v = new VisitorGenerateJava();
		racine.accept(v);
		System.out.println("---> CODE :");
		System.out.println(v.getResult());
		//extractionInstance(racine);
		//TestInstance(racine);
	}
	
	static void generateFile() {
		Model racine = ConstructFile.generationInstance();
		VisitorVerification verif = new VisitorVerification();
		racine.accept(verif);
		if(!verif.getResult()) {
			System.out.println("---> ERROR");
			System.out.println(verif.getDetail());
			return;
		}
		GenerateInterfaceVisit.generate(racine);
		VisitorGenerateFileJava v = new VisitorGenerateFileJava(Constants.PATH_GENERATE_FILE);
		racine.accept(v);
		//TestInstance(racine);
	}
	
	
	
	
	
}
