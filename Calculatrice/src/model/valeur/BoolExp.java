package model.valeur;

import model.Element;
import model.ExpUnaire;
import visiteur.Visiteur;

public class BoolExp extends ExpUnaire {
	boolean valeur;

	public BoolExp(Element parent,boolean valeur) {
		super(parent);
		this.valeur = valeur;
	}

	//@Override
	public boolean getValue() {
		return valeur;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
	}
}
