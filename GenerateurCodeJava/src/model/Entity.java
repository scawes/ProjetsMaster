package model;

import java.util.ArrayList;
import java.util.List;

import model.heritage.Heritage;
import model.heritage.HeritageUndefind;
import visitor.Visitable;
import visitor.Visitor;

public class Entity implements Visitable{

	List<Attribute> listAttribute;
	String name;
	Heritage heritage;
	
	public Entity() {
		heritage = new HeritageUndefind();
		this.listAttribute = new ArrayList<Attribute>();
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

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
