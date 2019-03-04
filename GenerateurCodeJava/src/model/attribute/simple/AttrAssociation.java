package model.attribute.simple;

import model.Entity;
import visitor.Visitor;

public class AttrAssociation extends TypeSimple{

	Entity typeOf;
	
	public AttrAssociation(Entity typeOf) {
		super(typeOf.getName());
		this.typeOf	= typeOf;
	}
	
	public Entity getTypeOf() {
		return typeOf;
	}



	public void setTypeOf(Entity typeOf) {
		this.typeOf = typeOf;
	}



	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
