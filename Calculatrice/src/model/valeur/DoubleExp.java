package model.valeur;

import model.Element;
import model.ExpUnaire;
import visiteur.Visiteur;

public class DoubleExp extends ExpUnaire {
	double valeur;

	public DoubleExp(Element parent,double valeur) {
		super(parent);
		this.valeur = valeur;
	}

	public double getValue() {
		return valeur;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
	}
}
