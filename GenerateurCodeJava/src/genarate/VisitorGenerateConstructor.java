package genarate;
import common.Constants;
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

public class VisitorGenerateConstructor implements Visitor{

	String result;
	
	public VisitorGenerateConstructor() {
		result="";
	}
	
	public String getResult(){
		return result;
	}
	
	@Override
	public void visit(Attribute attribute) {
		result+=attribute.getName();
	}

	@Override
	public void visit(Model model) {

	}

	@Override
	public void visit(Entity entity) {
		//constructeur vide
		result+="public ";
		result+=entity.getName();
		result+="(){\n";
		result+="}\n";
		//constructeur plein
		result+="public ";
		result+=entity.getName();
		result+="(";
		//parametres
		for(int i =0;i<entity.getListAttribute().size();i++) {
			entity.getListAttribute().get(i).getType().accept(this);
			result+=" ";
			entity.getListAttribute().get(i).accept(this);
			if(i<entity.getListAttribute().size()-1)
				result+=",";
		}
		result+=")";
		result+="{\n";
		//initialisation
		for(Attribute attribute:entity.getListAttribute()) {
			result+="this.";
			attribute.accept(this);
			result+="\t= ";
			attribute.accept(this);
			result+=";\n";
		}
		result+="}\n";
		
		//fonction visitable
		result+="@Override\r\n";
		result+="public void accept(";
		result+= Constants.VISITOR_MODEL_NAME;
		result+= " v) {\r\n";
		result+="v.visit(this);\r\n";
		result+="}\n";
	}

	@Override
	public void visit(AttrList attrList) {
		result+=attrList.getName();
		result+="<";
		attrList.getListOf().accept(this);
		result+=">";
	}

	@Override
	public void visit(AttrArray attrArray) {
		result+= attrArray.getName();
		result+="<";
		attrArray.getListOf().accept(this);
		result+=">";
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		result+="Undefind";
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		result+=attrAssociation.getTypeOf().getName();
	}

	@Override
	public void visit(AttrString attrString) {
		result+=attrString.getName();
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		result+=attrInteger.getName();
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
	}

}
