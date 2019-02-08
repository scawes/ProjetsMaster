package model;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitable;
import visitor.Visiteur;

public class Societe implements Visitable {

	String nom;
	List<Garage> list_garage;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Garage> getList_garage() {
		return list_garage;
	}

	public Societe() {
		list_garage = new ArrayList<Garage>();
	}
	
	public Societe(String nom) {
		this();
		this.nom	= nom;
	}
	
	public Garage add_garage(Garage garage) {
		list_garage.add(garage);
		return garage;
	}
	
	public Garage add_garage(String nom,String adresse) {
		Garage garage = new Garage(nom,adresse);
		list_garage.add(garage);
		return garage;
	}

	@Override
	public void accept(Visiteur v) {
		v.visit(this);
	}
	public String toString() {
		return "Societe";
	}
}
