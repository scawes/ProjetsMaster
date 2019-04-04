package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Attribute;
import model.Entity;
import model.Model;

public abstract class AbstractRepository3{

	
	protected List<InstanceModel> ListInstance;
	protected String resultXML;
	protected Model model;
	
	public AbstractRepository3() {
		ListInstance = new ArrayList<>();
		resultXML = "";
	}
	
	public void clear() {
		ListInstance.clear();
	}
	
	public void setModel(Model model) {
		this.model=model;
	}
	
	public Integer addInstance(InstanceModel instance) {
		Integer id = ListInstance.size();
		instance.setId(id);
		ListInstance.add(instance);
		return id;
	}
	
	public InstanceModel getInstance(Integer id){
		for(InstanceModel instance:ListInstance) {
			if(instance.getId()==id) {
				return instance;
			}
		}
		return null;
	}
	
	
	public String exportListInstanceToXML() {
		String listInstanceXML = "";
		listInstanceXML += "<Instances>\n";
		for(InstanceModel instance:ListInstance) {
			listInstanceXML += exportInstanceToXML(instance);
			listInstanceXML += "\n";
		}
		listInstanceXML += "</Instances>\n";
		return listInstanceXML;
	}
	
	public abstract String exportInstanceToXML(InstanceModel instance);
	
	public List<Integer> getIds(String valeur){
		
		List<Integer> ids = new ArrayList<>();
		if(valeur.isEmpty()) {
			return ids;
		}
		valeur = valeur.replace(" ","");
		String[] valeurs = valeur.split("#");
		for(String id:valeurs) {
			if(!id.isEmpty()) {
				ids.add(Integer.parseInt(id));
			}
		}
		return ids;
	}
	
	public Integer getId(String valeur) {
		if(valeur.isEmpty())
			return 0;
		valeur = valeur.replace(" ","");
		valeur = valeur.replace("#","");
		return Integer.parseInt(valeur);
	}
	
	public Entity getEntity(String entityName) {
		for(Entity entity:model.getListEntity()) {
			if (entity.getName().equals(entityName)) {
				return entity;
			}
		}
		return null;
	}
	public Attribute getAttribute(Entity entity,String attributeName) {
		for(Attribute attribute:entity.getListAttribute()) {
			if (attribute.getName().equals(attributeName)) {
				return attribute;
			}
		}
		return null;
	}
	
	public void materializeInstance(Document document) {
		//lecture des model
		ListInstance.clear();
		Node racine = document.getDocumentElement();
		NodeList nl = racine.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			InstanceModel instance = generationInstance((Node) nl.item(i));
			if(instance!=null) {
				ListInstance.add(instance);
			}
		}
	}
	
	public InstanceModel generationInstance(Node element) {
		InstanceModel instance;
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		String entityName = element.getNodeName();
		String attributeName;
		String attributeValue;
		String id = element.getAttributes().item(0).getNodeValue();
		
		Map<String,String> value = new HashMap<>();
		for(int i = 1;i<element.getAttributes().getLength();i++) {
			attributeName	= element.getAttributes().item(i).getNodeName();
			attributeValue	= element.getAttributes().item(i).getNodeValue();
			value.put(attributeName, attributeValue);
		}
		//System.out.println(entityName);
		instance = generateInstance(entityName,value);
		instance.setId(Integer.parseInt(id));
		return instance;
	}
	
	public abstract InstanceModel generateInstance(String className,Map<String,String> value);
}
