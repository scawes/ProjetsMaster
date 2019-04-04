package genarate;

import common.Constants;
import common.ConstructFile;
import model.Entity;
import model.Model;

public class GenerateInterfaceVisit {
	private GenerateInterfaceVisit() {
		
	}
	
	public static void generate(Model model) {
		String pathVisitor = Constants.PATH_GENERATE_FILE+Constants.VISITOR_MODEL_NAME+".java";
		ConstructFile.createFile(pathVisitor, generateVisitor(model));
		String pathVisitable = Constants.PATH_GENERATE_FILE+Constants.VISITABLE_MODEL_NAME+".java";
		ConstructFile.createFile(pathVisitable, generateVisitable());
	}
	
	static String generateVisitor(Model model) {
		String visitor = "";
		visitor += "package ";
		visitor += Constants.PACKAGE_NAME_GENERATE_FILE;
		visitor += ";\n";
		visitor += "public interface ";
		visitor += Constants.VISITOR_MODEL_NAME;
		visitor +=" {\n";
		for(Entity entity:model.getListEntity()) {
			visitor+="void visit(";
			visitor+=entity.getName();
			visitor+=" instance);\n";
		}
		visitor += "}";
		return visitor;
	}
	
	static String generateVisitable() {
		String visitor = "";
		visitor += "package ";
		visitor += Constants.PACKAGE_NAME_GENERATE_FILE;
		visitor += ";\n";
		visitor += "public interface ";
		visitor += Constants.VISITABLE_MODEL_NAME;
		visitor +=" {\n";
		visitor += "public void accept(";
		visitor +=Constants.VISITOR_MODEL_NAME;
		visitor +=" v);\n";
		visitor += "}";
		return visitor;
	}
}
