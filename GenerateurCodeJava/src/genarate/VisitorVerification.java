package genarate;

import java.util.List;

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

public class VisitorVerification implements Visitor {

	List<Entity> listHeritage;
	List<String> listAttribute;
	@Override
	public void visit(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrList attrList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrArray attrArray) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrString attrString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AttrInteger attrInteger) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		// TODO Auto-generated method stub

	}

}
