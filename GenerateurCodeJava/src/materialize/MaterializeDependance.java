
package materialize;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dependance.Dependance;
import dependance.DependanceClass;
import dependance.DependancePrimitive;
import dependance.GestionDependance;
import model.Attribute;
import model.Entity;
import model.Model;
import model.attribute.Type;
import model.attribute.collection.AttrArray;
import model.attribute.collection.AttrList;
import model.attribute.simple.AttrUndefind;
import model.attribute.simple.basic.AttrInteger;
import model.attribute.simple.basic.AttrString;
import model.heritage.HeritageUndefind;



public class MaterializeDependance {

	Model model;
	GestionDependance dependances;

	public MaterializeDependance(Document document,Model model) {
		this.model = model;
		dependances = GenerationDependances((Node) document.getDocumentElement());
	}

	public GestionDependance getResult() {
		return dependances;
	}
	
	
	//lecture des instructions
	public GestionDependance GenerationDependances(Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		
		dependances = new GestionDependance();
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Dependance dependance = GenerationDependance((Node) nl.item(i));
			if(dependance!=null)
				dependances.addDependance(dependance);
		}
		return dependances;
	}
	
	/*public Dependance GenerationDependance(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		
		Dependance dependance = new Dependance();
		
		String name = element.getAttributes().getNamedItem("name").getNodeValue();
		entity.setName(name);
		Node nodeHeritage = element.getAttributes().getNamedItem("subtype");
		if(nodeHeritage!=null) {
			String heritage = nodeHeritage.getNodeValue();
			entity.setHeritage(new HeritageUndefind(heritage));
		}
		
		
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Attribute attribute = GenerationAttribute((Node) nl.item(i));
			if(attribute!=null)
				entity.addAttribute(attribute);
		}
		return entity;
	}*/

	/*public Attribute GenerationAttribute(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		
		Attribute attribute = new Attribute();
		
		String name = element.getAttributes().getNamedItem("name").getNodeValue();
		attribute.setName(name);
		attribute.setType(GenerationType(element.getChildNodes().item(1)));
		return attribute;
	}*/
	
	
	public Dependance GenerationDependance(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		switch (element.getNodeName()) {
		case "model":
			return GenerationDependanceClass(element);
		case "primitive":
			return GenerationDependancePrimitive(element);
		default:
			return null;
		}
	}
	
	
	public Dependance GenerationDependanceClass(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		String name  = element.getAttributes().getNamedItem("name").getNodeValue();
		String packageName  = element.getAttributes().getNamedItem("package").getNodeValue();
		for(Entity entityModel : model.getListEntity()) {
			if(entityModel.getName().equals(name)) {
				return new DependanceClass(packageName, entityModel);
			}
		}
		return null;
	}
	
	public Dependance GenerationDependancePrimitive(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		String name  = element.getAttributes().getNamedItem("name").getNodeValue();
		//String packageName  = element.getAttributes().getNamedItem("package").getNodeValue();
		String type  = element.getAttributes().getNamedItem("type").getNodeValue();
		return new DependancePrimitive("", name,type);

	}
	
	
}
