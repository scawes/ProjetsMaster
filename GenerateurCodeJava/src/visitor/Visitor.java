package visitor;

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

public interface Visitor {

	void visit(Attribute attribute);

	void visit(Model model);

	void visit(Entity entity);

	void visit(AttrList attrList);

	void visit(AttrArray attrArray);

	void visit(AttrUndefind attrUndefind);

	void visit(AttrAssociation attrAssociation);

	void visit(AttrString attrString);

	void visit(AttrInteger attrInteger);

	void visit(HeritageUndefind heritageUndefind);

	void visit(HeritageEntity heritageEntity);

	void visit(DependancePrimitive dependancePrimitive);

	void visit(DependanceClass dependanceClass);

	
}
