package dependance;

import model.Entity;
import visitor.Visitor;

public class DependancePrimitive extends Dependance{

	String type;
	String name;
	
	public DependancePrimitive(String packageName,String name,String type) {
		super(packageName);
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String getPath() {
		return packageName;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public boolean equals(Entity entity) {
		return false;
	}

}
