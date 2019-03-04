package repository;

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
import visitor.Visitable;
import visitor.Visitor;

public class VisitorAddInstance implements Visitor{

	List<Visitable> listIntance;
	
	public VisitorAddInstance() {
		listIntance= new ArrayList<Visitable>();
	}
	
	public List<Visitable> getResult(){
		return listIntance;
	}
	
	private void addInstance(Visitable obj) {
		listIntance.add(obj);
	}
	
	@Override
	public void visit(Attribute attribute) {
		attribute.getType().accept(this);
		addInstance(attribute);
	}
	

	@Override
	public void visit(Model model) {
		for(Entity entity : model.getListEntity())
			entity.accept(this);
		addInstance(model);
	}
	
	

	@Override
	public void visit(Entity entity) {
		for(Dependance dependance :entity.getDependances())
			dependance.accept(this);
		for(Attribute attribute:entity.getListAttribute())
			attribute.accept(this);
		entity.getHeritage().accept(this);
		addInstance(entity);
	}

	@Override
	public void visit(AttrList attrList) {
		addInstance(attrList);	
	}

	@Override
	public void visit(AttrArray attrArray) {
		addInstance(attrArray);		
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		addInstance(attrUndefind);		
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		addInstance(attrAssociation);		
	}

	@Override
	public void visit(AttrString attrString) {
		addInstance(attrString);		
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		addInstance(attrInteger);		
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		addInstance(heritageUndefind);		
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		addInstance(heritageEntity);		
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
		addInstance(dependancePrimitive);		
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
		addInstance(dependanceClass);		
	}
	
}
