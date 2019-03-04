
package materialize;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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



public class MaterializeModel {

	Model model;
	

	public MaterializeModel(Document document) {
		//lecture des model
		model = GenerationModel((Node) document.getDocumentElement());
	}

	public Model getResult() {
		return model;
	}
	
	
	//lecture des instructions
	public Model GenerationModel(Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		
		Model model = new Model();
		String name = element.getAttributes().getNamedItem("name").getNodeValue();
		model.setName(name);
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Entity entity = GenerationEntity((Node) nl.item(i));
			if(entity!=null)
				model.addEntity(entity);
		}
		return model;
	}
	
	public Entity GenerationEntity(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		
		Entity entity = new Entity();
		
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
	}

	public Attribute GenerationAttribute(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		
		Attribute attribute = new Attribute();
		
		String name = element.getAttributes().getNamedItem("name").getNodeValue();
		attribute.setName(name);
		attribute.setType(GenerationType(element.getChildNodes().item(1)));
		if(element.getAttributes().getNamedItem("valeur")!=null) {
			attribute.setValeur(element.getAttributes().getNamedItem("valeur").getNodeValue());
		}
		return attribute;
	}
	
	
	public Type GenerationType(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return new AttrUndefind();
		switch (element.getNodeName()) {
		case "type":
			return GenerationTypeSimple(element);
		case "typeList":
			return GenerationTypeList(element);
		default:
			return new AttrUndefind();
		}
	}
	
	
	public Type GenerationTypeSimple(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return new AttrUndefind();
		String nameAttr  =element.getAttributes().getNamedItem("type").getNodeValue();
		switch (nameAttr) {
		case "Integer":
			return new AttrInteger(nameAttr);
		case "String":
			return new AttrString(nameAttr);
		default:
			return new AttrUndefind(nameAttr);
		}
	}
	
	public Type GenerationTypeList(Node element) {
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return new AttrUndefind();
		
		Type type ;
		String nameAttr  = element.getAttributes().getNamedItem("type").getNodeValue();
		switch (nameAttr) {
		case "Array":
			type = new AttrArray("ArrayList",GenerationType(element.getChildNodes().item(1)));
			break;
		case "List":
			type = new AttrList("List",GenerationType(element.getChildNodes().item(1)));
			break;
		default:
			type = new AttrUndefind(nameAttr);
			break;
		}
		return type;
	}
	
	
}
