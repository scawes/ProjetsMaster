package repository;

import dependance.DependanceClass;
import dependance.DependancePrimitive;
import model.Attribute;
import model.Entity;
import model.Model;
import model.attribute.collection.AttrArray;
import model.attribute.collection.AttrList;
import model.attribute.simple.AttrAssociation;
import model.attribute.simple.AttrUndefind;
import model.attribute.simple.basic.AttrInteger;
import model.attribute.simple.basic.AttrString;
import model.heritage.HeritageEntity;
import model.heritage.HeritageUndefind;
import visitor.Visitor;

public class Repository extends AbstractRepository implements Visitor{

	@Override
	public void visit(Attribute attribute) {
		resultXML += toXML(attribute);
	}
	
	@Override
	public void visit(Object obj) {
		//obj.accept(this);
	}
	
	public String toXML(Attribute attribute) {
		Integer id = mapInstanceId.get(attribute);
		String name = attribute.getName();
		String type = "#"+mapInstanceId.get(attribute.getType());
				//attribute.getType().toString();
		String valeur = attribute.getValeur();
		String balise = "";
		balise+="<";
		balise+="Attribute";
		//id
		balise+=" ";
		balise+="id";
		balise+="='";
		balise+=id;
		balise+="'";
		//attr
		balise+=" ";
		balise+="name";
		balise+="='";
		balise+=name;
		balise+="'";
		//attr
		balise+=" ";
		balise+="type";
		balise+="='";
		balise+=type;
		balise+="'";
		//attr
		balise+=" ";
		balise+="valeur";
		balise+="='";
		balise+=valeur;
		balise+="'";
		//fin
		balise+="/>\n";
		return balise;		
	}
	public void toObject(String attribute) {
	}

	@Override
	public void visit(Model model) {
		
	}
	
	

	@Override
	public void visit(Entity entity) {
		
	}

	@Override
	public void visit(AttrList attrList) {
		
	}

	@Override
	public void visit(AttrArray attrArray) {
			
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
			
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
				
	}

	@Override
	public void visit(AttrString attrString) {
			
	}

	@Override
	public void visit(AttrInteger attrInteger) {
			
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
			
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
				
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
			
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
			
	}
}
