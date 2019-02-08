package model.instruction;

import model.Element;
import model.Expr;
import visiteur.Visiteur;

public class SortieStandard extends Instruction{

	Expr valeur;
	
	public SortieStandard(Element parent,Expr valeur) {
		super(parent);
		this.valeur = valeur;
	}
	
	public SortieStandard(Element parent) {
		super(parent);
	}
	
	public Expr getValeur() {
		return valeur;
	}
	
	public void setValeur(Expr valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public void accept(Visiteur v) {
		v.visite(this);	
	}

}
