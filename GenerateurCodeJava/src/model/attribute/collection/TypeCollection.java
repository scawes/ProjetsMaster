package model.attribute.collection;

import model.Attribute;
import model.attribute.Type;

public abstract class TypeCollection extends Type{

	Type listOf;

	
	public TypeCollection(Type listOf) {
		super();
		this.listOf = listOf;
	}

	public Type getListOf() {
		return listOf;
	}

	public void setListOf(Type listOf) {
		this.listOf = listOf;
	}
	
	
}
