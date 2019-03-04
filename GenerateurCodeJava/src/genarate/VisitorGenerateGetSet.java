package genarate;
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

public class VisitorGenerateGetSet implements Visitor{

	String result;
	
	public VisitorGenerateGetSet() {
		result="";
	}
	
	public String getResult(){
		return result;
	}
	
	@Override
	public void visit(Attribute attribute) {
		//get
		result+="public ";
		attribute.getType().accept(this);
		result+=" get";
		result+=attribute.getName();
		result+="(){\n";
		result+="return ";
		result+=attribute.getName();
		result+=";\n}\n";
		//set
		result+="public void set";
		result+=attribute.getName();
		result+="(";
		attribute.getType().accept(this);
		result+=" ";
		result+=attribute.getName();
		result+="){\n";
		result+="this.";
		result+=attribute.getName();
		result+="\t=";
		result+=attribute.getName();
		result+=";\n}\n";
	}

	@Override
	public void visit(Model model) {

	}

	@Override
	public void visit(Entity entity) {
		for(Attribute attribute:entity.getListAttribute()) {
			attribute.accept(this);
		}
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
