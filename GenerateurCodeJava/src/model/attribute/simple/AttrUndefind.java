package model.attribute.simple;

import visitor.Visitor;

public class AttrUndefind extends TypeSimple {

	String type;
	public AttrUndefind(String name) {
		super(name);
	}
	public AttrUndefind() {
		super("");
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
