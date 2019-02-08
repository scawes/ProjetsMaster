package model.heritage;

import visitor.Visitor;

public class HeritageUndefind extends Heritage{

	String name;
	
	public HeritageUndefind() {
		super();
		this.name = "";
	}
	
	public HeritageUndefind(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
