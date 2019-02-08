package model.variable;

import model.Element;
import model.instruction.Instruction;
import visiteur.Visiteur;

public class VariableDef extends Instruction {

	String nom;
	private String type;
	private String visibilite;
	
	public VariableDef(Element parent,String nom,String type,String visibilite) {
		super(parent);
		this.nom = nom;
		this.type= type;
		this.visibilite = visibilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibilite() {
		return visibilite;
	}

	public void setVisibilite(String visibilite) {
		this.visibilite = visibilite;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);
		
	}
	
	
}
