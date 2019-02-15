package model;

import java.util.ArrayList;
import java.util.List;

import dependance.Dependance;
import model.heritage.Heritage;
import model.heritage.HeritageUndefind;
import visitor.Visitable;
import visitor.Visitor;

public class Entity implements Visitable{

	List<Attribute> listAttribute;
	List<Dependance> listDependance;
	String name;
	Heritage heritage;
	
	public Entity() {
		heritage = new HeritageUndefind();
		this.listAttribute = new ArrayList<Attribute>();
		this.listDependance = new ArrayList<Dependance>();
	}
	
	public Entity(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attribute> getListAttribute() {
		return listAttribute;
	}
	
	public void addAttribute(Attribute attribute) {
		listAttribute.add(attribute);
	}
	
	public Heritage getHeritage() {
		return heritage;
	}

	public void setHeritage(Heritage heritage) {
		this.heritage = heritage;
	}
	
	public void addDependance(Dependance dependance) {
		listDependance.add(dependance);
	}
	
	public List<Dependance> getDependances(){
		return listDependance;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
