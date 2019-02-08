package model.attribute.collection;

import model.attribute.Type;
import visitor.Visitor;

public class AttrArray extends TypeCollection{

	public AttrArray(Type listOf) {
		super(listOf);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
