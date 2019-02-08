package model.heritage;

import model.Entity;
import visitor.Visitor;

public class HeritageEntity extends Heritage{

	Entity entity;
	
	public HeritageEntity(Entity entity) {
		super();
		this.entity = entity;
	}

	
	
	public Entity getEntity() {
		return entity;
	}



	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
