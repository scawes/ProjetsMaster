package model.attribute.simple;

import visitor.Visitor;

public class AttrUndefind extends TypeSimple {

	String type;
	public AttrUndefind(String type) {
		this.type 	= type;
	}
	public AttrUndefind() {
		this.type 	= "";
	}
	public String getType() {
		return type;
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
