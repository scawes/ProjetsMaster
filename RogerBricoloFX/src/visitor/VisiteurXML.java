package visitor;

import model.Garage;
import model.Moto;
import model.Reparation;
import model.Societe;
import model.Voiture;

public class VisiteurXML implements Visiteur{
	String valeur;
	
	public VisiteurXML(){
		valeur = "";
	}
	
	public String to_xml() {
		return valeur;
	}
	
	public String to_dtd() {
		String dtd = "";
		dtd	+="<!DOCTYPE Societe [";
		dtd	+="<!ELEMENT Societe (nom_societe,Garage*)>";
		dtd	+="<!ELEMENT nom_societe (#PCDATA)>";
		dtd	+="<!ELEMENT Garage (Nom_Garage,Adresse_Garage,Voiture*,Moto*)>";
		dtd	+="<!ELEMENT Nom_Garage (#PCDATA)>";
		dtd	+="<!ELEMENT Adresse_Garage (#PCDATA)>";
		dtd	+="<!ELEMENT Voiture (Date_arrive,Motif,ControleTechnique)>";
		dtd	+="<!ELEMENT Moto (Date_arrive,Motif,SD)>";
		dtd	+="<!ELEMENT Date_arrive (#PCDATA)>";
		dtd	+="<!ELEMENT Motif (#PCDATA)>";
		dtd	+="<!ELEMENT ControleTechnique (#PCDATA)>";
		dtd	+="<!ELEMENT SD (#PCDATA)>";
		dtd	+="]>";
		return dtd;
	}
	
	@Override
	public void visit(Garage o) {
		valeur 	+= "<Garage>";
		valeur 	+= InitialiseXML.retourneChamp("Nom_Garage", o.getNom());
		valeur 	+= InitialiseXML.retourneChamp("Adresse_Garage", o.getAdresse());
		for(Reparation obj : o.get_List_Reparation()){
			obj.accept(this);
		}
		valeur 	+= "</Garage>";
	}

	@Override
	public void visit(Reparation o) {
		valeur 	+= InitialiseXML.retourneChamp("Date_arrive", o.getDate_arrive().toString());
		valeur 	+= InitialiseXML.retourneChamp("Motif", o.getMotif());
	}

	@Override
	public void visit(Voiture o) {
		valeur 	+= "<Voiture>";
		valeur 	+= InitialiseXML.retourneChamp("Date_arrive", o.getDate_arrive().toString());
		valeur 	+= InitialiseXML.retourneChamp("Motif", o.getMotif());
		valeur 	+= InitialiseXML.retourneChamp("ControleTechnique", o.getControle_technique().toString());
		valeur 	+= "</Voiture>";
	}

	@Override
	public void visit(Moto o) {
		valeur 	+= "<Moto>";
		valeur 	+= InitialiseXML.retourneChamp("Date_arrive", o.getDate_arrive().toString());
		valeur 	+= InitialiseXML.retourneChamp("Motif", o.getMotif());
		valeur 	+= InitialiseXML.retourneChamp("SD", String.valueOf(o.isSd()));
		valeur 	+= "</Moto>";
	}

	@Override
	public void visit(Societe societe) {
		valeur 	+= "<Societe>";
		valeur 	+= InitialiseXML.retourneChamp("nom_societe", societe.getNom());
		for(Garage obj : societe.getList_garage()){
			obj.accept(this);
		}
		valeur 	+= "</Societe>";
	}

}
