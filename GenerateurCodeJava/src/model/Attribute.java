package model;

import model.attribute.Type;
import visitor.Visitable;
import visitor.Visitor;

public class  Attribute implements Visitable{
	private String name;
	private Type type;
	public Attribute() {
		
	}
	
	public Attribute(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
