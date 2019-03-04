package dependance;

import model.Entity;
import visitor.Visitor;

public class DependanceClass extends Dependance {
	Entity entity;
	
	public DependanceClass(String packageName,Entity entity) {
		super(packageName);
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}

	@Override
	public String getPath() {
		return packageName+"."+entity.getName();
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public boolean equals(Entity entity) {
		return this.entity.equals(entity);
	}

	@Override
	public String getName() {
		return entity.getName();
	}

	@Override
	public boolean equals(String name) {
		return false;
	}

	@Override
	public String getPackage() {
		return packageName;
	}
	
	
}
