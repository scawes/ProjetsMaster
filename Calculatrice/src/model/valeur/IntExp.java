package model.valeur;

import model.Element;
import model.ExpUnaire;
import visiteur.Visiteur;

public class IntExp extends ExpUnaire {
	int valeur;

	public IntExp(Element parent,int valeur) {
		super(parent);
		this.valeur = valeur;
	}

	public int getValue() {
		return valeur;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
	}
}
