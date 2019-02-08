package model.variable;

import java.util.HashMap;
import java.util.Map;

import model.Element;
import model.ExpUnaire;

public class GestionnaireVariable {
	//static GestionnaireVariable monGestionnaireVariable;
	Map<VariableDef,ExpUnaire> map;
	
	public GestionnaireVariable() {
		map = new HashMap<VariableDef, ExpUnaire>();
	}
	
	public VariableDef addVar(Element parent,String nom,String type,String visibilite) {
		VariableDef var = new VariableDef(parent,nom,type,visibilite);
		setVar(var, null);
		return var;
	}
	
	public void setVar(VariableDef var,ExpUnaire val) {
		map.put(var, val);
	}
	
	public ExpUnaire getVar(VariableDef var) {
		return map.get(var);
	}
	
	public VariableDef getDef(String nom) {
		for(VariableDef var : map.keySet()) {
			if (var.getNom().equals(nom)) {
				return var;
			}
		}
		return null;
	}
	
	public VariableRef getRef(Element parent,String nom) {
		VariableDef def = getDef(nom);
		
		if (def!=null) {
			return new VariableRef(parent,def);
		} else {
			return null;
		}
	}
	public VariableRef getRef(Element parent,UnresolveSymbol var) {
		return getRef(parent,var.getNom());
	}
	
	public void clear() {
		map.clear();
	}
	
	/*public static GestionnaireVariable getGestionnaireVariable() {
		if(monGestionnaireVariable!=null) {
			return monGestionnaireVariable;
		} else {
			monGestionnaireVariable= new GestionnaireVariable();
			return monGestionnaireVariable;
		}
	}*/
	
}
