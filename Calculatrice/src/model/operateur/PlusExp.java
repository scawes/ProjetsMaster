package model.operateur;

import model.Element;
import model.ExpBinaire;
import model.Expr;
import visiteur.Visiteur;

public class PlusExp extends ExpBinaire {

	public PlusExp(Element parent,Expr opg, Expr opd) {
		super(parent,opg, opd);
	}
	
	public PlusExp(Element parent) {
		super(parent);
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
	}
}
