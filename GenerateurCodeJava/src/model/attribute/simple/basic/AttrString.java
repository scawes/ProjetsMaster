package model.attribute.simple.basic;

import visitor.Visitor;

public class AttrString extends TypeBasic{

	public AttrString(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
