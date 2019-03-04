package model.attribute;

import visitor.Visitable;

public abstract class Type implements Visitable{

	String name;
	public Type(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
