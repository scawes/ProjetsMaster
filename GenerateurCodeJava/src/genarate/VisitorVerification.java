package genarate;

import java.util.ArrayList;
import java.util.List;

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

public class VisitorVerification implements Visitor {

	List<Entity> listHeritage;
	List<String> listAttribute;
	boolean result;
	String details;
	
	public VisitorVerification () {
		result	= true;
		details	= "";
	}
	
	public boolean getResult() {
		return result;
	}
	public String getDetail() {
		return details;
	}
	
	@Override
	public void visit(Attribute attribute) {
		if(listAttribute.contains(attribute.getName())) {
			result = false;
			details += "Variable \""+attribute.getName()+"\" duppliqué\n";
		} else {
			listAttribute.add(attribute.getName());
		}
	}

	@Override
	public void visit(Model model) {
		for(Entity entity:model.getListEntity()) {
			listHeritage 	= new ArrayList<Entity>();
			listAttribute 	= new ArrayList<String>();
			entity.accept(this);
		}
	}

	@Override
	public void visit(Entity entity) {
		if(listHeritage.contains(entity)) {
			result = false;
			details += "Class \""+entity.getName()+"\" heritage rebouclé\n";
		} else {
			listHeritage.add(entity);
			entity.getHeritage().accept(this);
			for(Attribute attribute:entity.getListAttribute()) {
				attribute.accept(this);
			}
		}
		
		
		
	}

	@Override
	public void visit(AttrList attrList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrArray attrArray) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrString attrString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrInteger attrInteger) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		heritageEntity.getEntity().accept(this);
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
		// TODO Auto-generated method stub
		
	}

}
