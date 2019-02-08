package model;

import java.util.Date;

import visitor.Visitable;
import visitor.Visiteur;

public class Moto extends Reparation implements Visitable{

	boolean sd;
	
	public boolean isSd() {
		return sd;
	}
	public void setSd(boolean sd) {
		this.sd = sd;
	}
	public Moto(Date date_arrive, String motif,boolean sd) {
		super(date_arrive, motif);
		this.sd = sd;
	}
	public Moto() {

	}
	@Override
	public void accept(Visiteur v) {
		v.visit(this);
	}
	public String toString() {
		return "Moto";
	}
}
