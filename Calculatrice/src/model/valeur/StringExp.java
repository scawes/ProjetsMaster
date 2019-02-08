package model.valeur;

import model.Element;
import model.ExpUnaire;
import visiteur.Visiteur;

public class StringExp extends ExpUnaire {
	String valeur;

	public StringExp(Element parent,String valeur) {
		super(parent);
		this.valeur = valeur;
	}

	public String getValue() {
		return valeur;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
	}
}
