package genarate;
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

	String result;
	
	public VisitorGenerateJava() {
		result="";
	}
	
	public String getResult(){
		return result;
	}
	
	@Override
	public void visit(Attribute attribute) {
		result+="\t";
		attribute.getType().accept(this);
		result+=" ";
		result+=attribute.getName();
		result+=";\n";
	}

	@Override
	public void visit(Model model) {
		
		result+="package ";
		result+=model.getName();
		result+="\n";
		for(Entity entity:model.getListEntity()) {
			entity.accept(this);
		}
	}

	@Override
	public void visit(Entity entity) {
		for(Dependance dependance:entity.getDependances()) {
			dependance.accept(this);
		}
		result+="public class ";
		result+=entity.getName();
		entity.getHeritage().accept(this);
		result+="{\n";
		for(Attribute attribute:entity.getListAttribute()) {
			attribute.accept(this);
		}
		result+="}\n";
	}

	@Override
	public void visit(AttrList attrList) {
		result+="List<";
		attrList.getListOf().accept(this);
		result+=">";
	}

	@Override
	public void visit(AttrArray attrArray) {
		result+="Array<";
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
		result+="String";
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		result+="Integer";
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		result+=" extends "+heritageEntity.getEntity().getName();
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
		result+=" import "+dependancePrimitive.getPath();
		result+="\n";
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
		result+=" import "+dependanceClass.getPath();
		result+="\n";
	}

}
