package model;

import model.attribute.Type;
import visitor.Visitable;
import visitor.Visitor;

public class  Attribute implements Visitable{
	private String name;
	private String valeur;
	private Type type;
	public Attribute() {
		this.valeur = "";
	}
	
	public Attribute(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
