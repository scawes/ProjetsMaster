package model.attribute.simple.basic;

import visitor.Visitor;

public class AttrInteger extends TypeBasic{

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
