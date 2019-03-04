package model.attribute.collection;

import model.attribute.Type;

public abstract class TypeCollection extends Type{

	Type listOf;

	
	public TypeCollection(String name,Type listOf) {
		super(name);
		this.listOf = listOf;
	}

	public Type getListOf() {
		return listOf;
	}

	public void setListOf(Type listOf) {
		this.listOf = listOf;
	}
	
	
}
