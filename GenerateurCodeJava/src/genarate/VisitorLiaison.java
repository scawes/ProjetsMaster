package genarate;

import dependance.Dependance;
import dependance.DependanceClass;
import dependance.DependancePrimitive;
import dependance.GestionDependance;
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
import visitor.Visitor;

public class VisitorLiaison implements Visitor{

	Model model;
	AttrAssociation liaison = null;
	HeritageEntity heritage = null;
	GestionDependance dependances;
	Entity entityEnCours;
	
	public VisitorLiaison(Model model,GestionDependance dependances) {
		this.model = model;
		this.dependances	= dependances;
	}
	
	private void matchDependance(Type type) {
		Dependance dependance = dependances.getDependance(type.getName());
		if(dependance == null) {
			System.out.println("ERROR --> Type undefind :"+type.getName());
			return;
		}
		
		if(dependance.getPackageName().isEmpty())return;
		if(!entityEnCours.getDependances().contains(dependance)) {
			entityEnCours.addDependance(dependance);
		}
	}
	
	private void addDependance(Entity entity) {
		Dependance dependance = dependances.getDependance(entity);
		if(dependance == null) {
			System.out.println("ERROR --> Type undefind :"+entity.getName());
			return;
		}
		if(entity.equals(entityEnCours))return;
		if(!entityEnCours.getDependances().contains(dependance)) {
			entityEnCours.addDependance(dependance);
		}
	}
	
	@Override
	public void visit(Attribute attribute) {
		attribute.getType().accept(this);
		if(liaison!=null) {
			attribute.setType(liaison);
			liaison = null;
		}
	}

	@Override
	public void visit(Model model) {
		for(Entity entity:model.getListEntity()) {
			entity.accept(this);
		}
	}

	@Override
	public void visit(Entity entity) {
		entityEnCours = entity;
		entityEnCours.setClassPackage(dependances.getDependance(entity).getPackage());
		entity.getHeritage().accept(this);
		if(heritage!=null) {
			entity.setHeritage(heritage);
			heritage = null;
		}
		
		for(Attribute attribute:entity.getListAttribute()) {
			attribute.accept(this);
		}
	}

	@Override
	public void visit(AttrList attrList) {
		matchDependance(attrList);
		attrList.getListOf().accept(this);
		if(liaison!=null) {
			attrList.setListOf(liaison);
			liaison = null;
		}
	}

	@Override
	public void visit(AttrArray attrArray) {
		matchDependance(attrArray);
		attrArray.getListOf().accept(this);
		if(liaison!=null) {
			attrArray.setListOf(liaison);
			liaison = null;
		}
		
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		for(Entity entity:model.getListEntity()) {
			if(entity.getName().equals(attrUndefind.getName())) {
				liaison = new AttrAssociation(entity);
				addDependance(entity);
			}
		}
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {

	}

	@Override
	public void visit(AttrString attrString) {
		matchDependance(attrString);
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		matchDependance(attrInteger);
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		for(Entity entity:model.getListEntity()) {
			if(entity.getName().equals(heritageUndefind.getName())) {
				heritage = new HeritageEntity(entity);
				addDependance(entity);
			}
		}
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {

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
