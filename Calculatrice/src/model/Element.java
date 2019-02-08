package model;

import model.variable.GestionnaireVariable;
import model.variable.UnresolveSymbol;
import model.variable.VariableDef;
import model.variable.VariableRef;
import visiteur.Visitable;

public abstract class Element implements Visitable{

	GestionnaireVariable variables;
	Element parent;
	
	public Element(Element parent) {
		this.parent = parent;
		variables = new GestionnaireVariable();
	}
	
	public VariableRef getReference(String nom) {
		VariableRef var = variables.getRef(this,nom);
		if(var!=null) 
			return var;
		if(parent!=null) {
			var = parent.getReference(nom);
			if(var!=null)
				return var;
		}
		return null;
	}
	
	public VariableRef getReference(UnresolveSymbol nom) {
		return getReference(nom.getNom());
	}
	
	public VariableDef addVariable(String nom,String type,String visibilite) {
		return variables.addVar(parent,nom,type,visibilite);
	}
	
	public void setVar(VariableDef var,ExpUnaire val) {
		VariableDef definition = variables.getDef(var.getNom());
		if(definition==null) {
			parent.setVar(var,val);
		} else {
			variables.setVar(var, val);
		}
		
	}
	
	public ExpUnaire getVar(VariableDef var) {
		VariableDef definition = variables.getDef(var.getNom());
		if(definition==null) {
			return parent.getVar(var);
		} else {
			return variables.getVar(var);
		}
	}
	
	public VariableDef getDef(String nom) {
		VariableDef definition = variables.getDef(nom);
		if(definition==null) {
			return parent.getDef(nom);
		} else {
			return variables.getDef(nom);
		}
	}
	
	public Element getParent() {
		return parent;
	}
}
