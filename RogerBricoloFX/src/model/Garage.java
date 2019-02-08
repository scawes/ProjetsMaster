package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visitor.Visitable;
import visitor.Visiteur;

public class Garage implements Visitable{
	List<Reparation> List_Reparation;
	String nom;
	String adresse;
	
	public String getNom() {
		return nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Garage(){
		List_Reparation = new ArrayList<Reparation>();
	}
	public Garage(String nom,String adresse){
		this();
		this.nom	= nom;
		this.adresse= adresse;
	}
	public Voiture add_voiture(Date date_arrive,String motif,Date controle_technique){
		Voiture voiture = new Voiture(date_arrive,motif,controle_technique);
		List_Reparation.add(voiture);
		return voiture;
	}
	public Moto add_moto(Date date_arrive,String motif,boolean sd){
		Moto moto = new Moto(date_arrive,motif,sd);
		List_Reparation.add(moto);
		return moto;
	}
	public Reparation add_reparation(Reparation reparation){
		List_Reparation.add(reparation);
		return reparation;
	}
	public List<Reparation> get_List_Reparation(){
		return List_Reparation;
	}
	@Override
	public void accept(Visiteur v) {
		v.visit(this);
	}
	public String toString() {
		return "Garage";
	}
}
