package model.operateur;

import model.Element;
import model.ExpBinaire;
import model.Expr;
import visiteur.Visiteur;

public class EgalExp extends ExpBinaire {

	public EgalExp(Element parent,Expr opg, Expr opd) {
		super(parent,opg, opd);
	}
	
	public EgalExp(Element parent) {
		super(parent);
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
	}
}
