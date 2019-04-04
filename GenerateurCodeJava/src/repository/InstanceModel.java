package repository;

import test.generateFiles.VisitableModel;

public abstract class InstanceModel implements VisitableModel {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
