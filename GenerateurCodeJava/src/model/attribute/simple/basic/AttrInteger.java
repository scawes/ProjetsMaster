package model.attribute.simple.basic;

import visitor.Visitor;

public class AttrInteger extends TypeBasic{

	public AttrInteger(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
