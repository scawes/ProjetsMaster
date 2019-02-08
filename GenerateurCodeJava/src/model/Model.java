package model;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitable;
import visitor.Visitor;

public class Model implements Visitable{

	List<Entity> listEntity;
	String name;
	
	public Model() {
		listEntity=new ArrayList<Entity>();
	}
	
	public Model(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Entity> getListEntity() {
		return listEntity;
	}
	
	public void addEntity(Entity entity) {
		listEntity.add(entity);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
