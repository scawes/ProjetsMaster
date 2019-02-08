package model;

import java.util.Date;

import visitor.Visitable;
import visitor.Visiteur;

public class Voiture extends Reparation implements Visitable{
	Date controle_technique;
	public Date getControle_technique() {
		return controle_technique;
	}
	public void setControle_technique(Date controle_technique) {
		this.controle_technique = controle_technique;
	}
	public Voiture(Date date_arrive, String motif,Date controle_technique) {
		super(date_arrive, motif);
		this.controle_technique=controle_technique;
	}
	public Voiture() {
	}
	@Override
	public void accept(Visiteur v) {
		v.visit(this);
	}

	public String toString() {
		return "Voiture";
	}
}
