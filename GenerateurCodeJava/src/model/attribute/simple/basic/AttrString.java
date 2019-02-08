package model.attribute.simple.basic;

import visitor.Visitor;

public class AttrString extends TypeBasic{

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
