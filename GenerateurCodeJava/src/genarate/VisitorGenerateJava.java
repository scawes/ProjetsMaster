package genarate;
import java.util.ArrayList;
import java.util.List;

import dependance.Dependance;
import dependance.DependanceClass;
import dependance.DependancePrimitive;
import model.Attribute;
import model.Entity;
import model.Model;
import model.attribute.collection.AttrArray;
import model.attribute.collection.AttrList;
import model.attribute.simple.AttrAssociation;
import model.attribute.simple.AttrUndefind;
import model.attribute.simple.basic.AttrInteger;
import model.attribute.simple.basic.AttrString;
import model.heritage.HeritageEntity;
import model.heritage.HeritageUndefind;
import visitor.Visitor;

public class VisitorGenerateJava implements Visitor{

	List<String> ListClass;
	String uneClass; 
	
	public VisitorGenerateJava() {
		ListClass=new ArrayList<String>();
		uneClass = "";
	}
	
	public List<String> getListResult(){
		return ListClass;
	}
	public String getResult(){
		String result = "";
		for(String classFile:ListClass) {
			result+=classFile;
		}
		return result;
	}
	
	@Override
	public void visit(Attribute attribute) {
		uneClass+="\t";
		attribute.getType().accept(this);
		uneClass+=" ";
		uneClass+=attribute.getName();
		if(!attribute.getValeur().isEmpty()) {
			uneClass+="\t";
			uneClass+="= ";
			uneClass+=attribute.getValeur().replace("'", "\"");
		}
		uneClass+=";\n";
	}

	@Override
	public void visit(Model model) {
		for(Entity entity:model.getListEntity()) {
			entity.accept(this);
			//ajout de la classe
			ListClass.add(uneClass);
			uneClass="";
		}
	}

	@Override
	public void visit(Entity entity) {
		uneClass+="package ";
		uneClass+=entity.getClassPackage();
		uneClass+=";\n";
		//dependence
		for(Dependance dependance:entity.getDependances()) {
			dependance.accept(this);
		}
		//declaration class
		uneClass+="public class ";
		uneClass+=entity.getName();
		entity.getHeritage().accept(this);
		uneClass+="{\n";
		//declaration variable
		for(Attribute attribute:entity.getListAttribute()) {
			attribute.accept(this);
		}
		//constructor
		VisitorGenerateConstructor constructeur = new VisitorGenerateConstructor();
		entity.accept(constructeur);
		uneClass+=constructeur.getResult();
		//getter setter
		VisitorGenerateGetSet getset = new VisitorGenerateGetSet();
		entity.accept(getset);
		uneClass+=getset.getResult();
		uneClass+="}\n\n";
		
	}

	@Override
	public void visit(AttrList attrList) {
		uneClass+=attrList.getName();
		uneClass+="<";
		attrList.getListOf().accept(this);
		uneClass+=">";
	}

	@Override
	public void visit(AttrArray attrArray) {
		uneClass+= attrArray.getName();
		uneClass+="<";
		attrArray.getListOf().accept(this);
		uneClass+=">";
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		uneClass+="Undefind";
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		uneClass+=attrAssociation.getTypeOf().getName();
	}

	@Override
	public void visit(AttrString attrString) {
		uneClass+=attrString.getName();
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		uneClass+=attrInteger.getName();
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		uneClass+=" extends "+heritageEntity.getEntity().getName();
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
		uneClass+="import "+dependancePrimitive.getPath();
		uneClass+=";\n";
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
		uneClass+="import "+dependanceClass.getPath();
		uneClass+=";\n";
	}

}
