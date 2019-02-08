package model.variable;

import model.Element;
import model.ExpUnaire;

public abstract class Reference extends ExpUnaire {

	private String nom;
	
	public Reference(Element parent,String nom) {
		super(parent);
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	
}
