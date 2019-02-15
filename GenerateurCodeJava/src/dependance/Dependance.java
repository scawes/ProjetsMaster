package dependance;

import model.Entity;
import visitor.Visitable;

public abstract class Dependance implements Visitable{
	String packageName;
	
	public Dependance() {
		packageName = "";
	}
	
	public Dependance(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}
	
	public abstract String getPath();
	
	public abstract boolean equals(Entity entity);
}
