package model;

public abstract class ExpBinaire extends Expr {

	Expr opg;

	Expr opd;
	
public ExpBinaire(Element parent,Expr opg,Expr opd) {
		super(parent);
		this.opg	= opg;
		this.opd	= opd;
	}

public ExpBinaire(Element parent) {
	super(parent);
}



public Expr getOpg() {
	return opg;
}
public void setOpg(Expr opg) {
	this.opg = opg;
}
public Expr getOpd() {
	return opd;
}
public void setOpd(Expr opd) {
	this.opd = opd;
}

public void addValeur(Expr valeur) {
	if(opg==null) {
		opg=valeur;
	}else {
		opd=valeur;
	}
}

}
