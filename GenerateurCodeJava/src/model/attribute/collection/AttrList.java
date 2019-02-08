package model.attribute.collection;

import model.attribute.Type;
import visitor.Visitor;

public class AttrList extends TypeCollection{

	public AttrList(Type listOf) {
		super(listOf);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
