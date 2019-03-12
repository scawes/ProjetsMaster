package repository;

import dependance.DependanceClass;
import dependance.DependancePrimitive;
import model.Attribute;
import model.Entity;
import model.Model;
import model.attribute.Type;
import model.attribute.collection.AttrArray;
import model.attribute.collection.AttrList;
import model.attribute.simple.AttrAssociation;
import model.attribute.simple.AttrUndefind;
import model.attribute.simple.basic.AttrInteger;
import model.attribute.simple.basic.AttrString;
import model.heritage.HeritageEntity;
import model.heritage.HeritageUndefind;
import test.generateFiles.Flotte;
import visitor.Visitor;

public class VisitorGenerateRepository implements Visitor {

	String fichier;
	boolean saveMode;
	Entity myEntity;
	
	public VisitorGenerateRepository() {
		fichier="";
		saveMode=false;
	}
	
	@Override
	public void visit(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Model model) {
		String finalFile = "package test.generateFiles;\r\n" + 
				"\r\n" + 
				"import java.util.ArrayList;\r\n" + 
				"import java.util.HashMap;\r\n" + 
				"import java.util.Map;\r\n" + 
				"\r\n" + 
				"import model.Attribute;\r\n" + 
				"import repository.AbstractRepository2;\r\n" + 
				"import repository.Instance;\r\n" + 
				"\r\n" + 
				"public class Repository2 extends AbstractRepository2 {";
		for(Entity entity:model.getListEntity()) {
			entity.accept(this);
		}
		saveMode=true;
		for(Entity entity:model.getListEntity()) {
			entity.accept(this);
		}
		finalFile+="}";
	}

	@Override
	public void visit(Entity entity) {
		myEntity=entity;
		fichier += new String("public Integer saveInstance(%1 entity) {").replace("%1", entity.getName());
		for(Attribute attribute:entity.getListAttribute()) {
			
			fichier += new String("Attribute attribute = getAttribute(getEntity(\"%1\"), \"%2\");\r\n")
					.replace("%1", myEntity.getName()).replace("%2", attribute.getName());
			
			attribute.accept(this);
		}
		fichier += new String("return addInstance(getEntity(\"%1\"),valeur);}").replace("%1", entity.getName());
	}

	@Override
	public void visit(AttrList attrList) {
		/*fichier += new String("Attribute attribute = getAttribute(getEntity(\"%1\"), \"%2\");\r\n")
				.replace("%1", myEntity.getName()).replace("%2", attributeName);*/
		//attributeClassName.accept(this);
		String result = "for(%3 entityList:entity.get%2()) {\r\n" + 
"			ids+=\"#\"+saveInstance(entityList)+\" \";\r\n" + 
"		}";
		/*fichier += new String("valeur.put(attribute, \"#\"+saveInstance(ids));")
				.replace("%1", myEntity.getName()).replace("%2", attributeName);*/
	}

	@Override
	public void visit(AttrArray attrArray) {
		
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		fichier += new String("valeur.put(attribute, \"#\"+saveInstance(entity.get%2()));")
				.replace("%1", myEntity.getName()).replace("%2", attrAssociation.getName());
	}

	@Override
	public void visit(AttrString attrString) {
		fichier += new String("valeur.put(attribute, entity.get%2());")
				.replace("%1", myEntity.getName()).replace("%2", attrString.getName());
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		fichier += new String("valeur.put(attribute, entity.get%2().toString());")
				.replace("%1", myEntity.getName()).replace("%2", attrInteger.getName());
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DependanceClass dependanceClass) {
		// TODO Auto-generated method stub

	}
	
	private void writeAttributeSave(String entityName,String attributeName) {
		fichier += new String("Attribute attribute = getAttribute(getEntity(\"%1\"), \"%2\");\r\n"+
				"valeur.put(attribute, entity.get%2().toString());")
				.replace("%1", entityName).replace("%2", attributeName);
	}
	
	private void writeAttributeLiaisonSave(String entityName,String attributeName) {
		fichier += new String("Attribute attribute = getAttribute(getEntity(\"%1\"), \"%2\");\r\n"+
				"valeur.put(attribute, \"#\"+saveInstance(entity.get%2()));")
				.replace("%1", entityName).replace("%2", attributeName);
	}
	
	private void writeAttributeListSave(String entityName,String attributeName,Type attributeClassName) {
		fichier += new String("Attribute attribute = getAttribute(getEntity(\"%1\"), \"%2\");\r\n")
				.replace("%1", entityName).replace("%2", attributeName);
		attributeClassName.accept(this);
		/*String result = "Attribute attribute = getAttribute(getEntity(\"%1\"), \"%2\");\r\n";
		result+="for(%3 entityList:entity.get%2()) {\r\n" + 
"			ids+=\"#\"+saveInstance(entityList)+\" \";\r\n" + 
"		}";*/
		fichier += new String("valeur.put(attribute, \"#\"+saveInstance(ids));")
				.replace("%1", entityName).replace("%2", attributeName);

	}

}
