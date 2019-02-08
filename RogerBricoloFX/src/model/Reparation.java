package model;

import java.util.Date;

import visitor.Visitable;
import visitor.Visiteur;

public class Reparation implements Visitable{
	Date date_arrive;
	String motif;
	public Date getDate_arrive() {
		return date_arrive;
	}
	public String getMotif() {
		return motif;
	}
	public Reparation(Date date_arrive,String motif){
		this.date_arrive=date_arrive;
		this.motif=motif;
	}
	public Reparation(){
	}
	public void setDate_arrive(Date date_arrive) {
		this.date_arrive = date_arrive;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	@Override
	public void accept(Visiteur v) {
		v.visit(this);
	}
}
