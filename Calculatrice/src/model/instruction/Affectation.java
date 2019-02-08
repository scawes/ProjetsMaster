package model.instruction;

import model.Element;
import model.Expr;
import model.variable.Reference;
import visiteur.Visiteur;

public class Affectation extends Instruction{

	Expr valeur;
	Reference variable;
	
	public Affectation(Element parent,Reference variable,Expr valeur) {
		super(parent);
		this.variable = variable;
		this.valeur = valeur;
	}
	
	public Affectation(Element parent) {
		super(parent);
	}

	
	
	public Expr getValeur() {
		return valeur;
	}



	public void setValeur(Expr valeur) {
		this.valeur = valeur;
	}



	public Reference getVariable() {
		return variable;
	}



	public void setVariable(Reference variable) {
		this.variable = variable;
	}



	@Override
	public void accept(Visiteur v) {
		v.visite(this);	
		
	}
}
