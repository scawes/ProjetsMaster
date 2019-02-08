package model.variable;

import model.Element;
import visiteur.Visiteur;

public class VariableRef extends Reference{

	private VariableDef definition;
	
	public VariableRef(Element parent,VariableDef definition) {
		super(parent,definition.getNom());
		this.definition	= definition;
	}

	
	
	public VariableDef getDefinition() {
		return definition;
	}



	@Override
	public void accept(Visiteur v) {
		v.visite(this);		
	}

}
