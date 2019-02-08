package model.variable;

import model.Element;
import visiteur.Visiteur;

public class UnresolveSymbol extends Reference{

	public UnresolveSymbol(Element parent,String nom) {
		super(parent,nom);
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);		
	}

}
