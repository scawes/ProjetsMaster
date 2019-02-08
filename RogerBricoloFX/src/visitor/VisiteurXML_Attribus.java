package visitor;

import model.Garage;
import model.Moto;
import model.Reparation;
import model.Societe;
import model.Voiture;

public class VisiteurXML_Attribus implements Visiteur{
	String valeur;
	
	public VisiteurXML_Attribus(){
		valeur = "";
	}
	
	public String to_xml() {
		return valeur;
	}
	public String to_dtd() {
		String dtd = "";
		dtd	+="<!DOCTYPE Societe [";
		dtd	+="<!ELEMENT Societe (Garage*)>";
		dtd	+="<!ATTLIST Societe nom_societe CDATA #REQUIRED >";
		dtd	+="<!ELEMENT Garage (Voiture*,Moto*)>";
		dtd	+="<!ATTLIST Garage Nom_Garage CDATA #REQUIRED Adresse_Garage CDATA #REQUIRED>";
		dtd	+="<!ELEMENT Voiture EMPTY>";
		dtd	+="<!ATTLIST Voiture Date_arrive CDATA #REQUIRED Motif CDATA #REQUIRED ControleTechnique CDATA #REQUIRED>";
		dtd	+="<!ELEMENT Moto EMPTY>";
		dtd	+="<!ATTLIST Moto Date_arrive CDATA #REQUIRED Motif CDATA #REQUIRED SD CDATA #REQUIRED>";
		dtd	+="]>";
		return dtd;
	}
	
	
	@Override
	public void visit(Garage o) {
		valeur 	+= "<Garage"
				+ InitialiseXML.retourneAttribut("Nom_Garage",o.getNom()) 
				+ InitialiseXML.retourneAttribut("Adresse_Garage",o.getAdresse())
				+">";
		for(Reparation obj : o.get_List_Reparation()){
			obj.accept(this);
		}
		valeur 	+= "</Garage>";
	}

	@Override
	public void visit(Reparation o) {

	}

	@Override
	public void visit(Voiture o) {
		valeur 	+= "<Voiture"
				+ InitialiseXML.retourneAttribut("Date_arrive",o.getDate_arrive().toString()) 
				+ InitialiseXML.retourneAttribut("Motif",o.getMotif())
				+ InitialiseXML.retourneAttribut("ControleTechnique",o.getControle_technique().toString())
				+"/>";
	}

	@Override
	public void visit(Moto o) {
		valeur 	+= "<Moto"
				+ InitialiseXML.retourneAttribut("Date_arrive",o.getDate_arrive().toString()) 
				+ InitialiseXML.retourneAttribut("Motif",o.getMotif())
				+ InitialiseXML.retourneAttribut("SD",String.valueOf(o.isSd()))
				+"/>";

	}

	@Override
	public void visit(Societe societe) {
		valeur 	+= "<Societe"
				+ InitialiseXML.retourneAttribut("nom_societe",societe.getNom()) 
				+">";
		for(Garage obj : societe.getList_garage()){
			obj.accept(this);
		}
		valeur 	+= "</Societe>";
	}

}
