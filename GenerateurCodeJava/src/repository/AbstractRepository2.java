package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Attribute;
import model.Entity;
import model.Model;
import visitor.Visitable;

public abstract class AbstractRepository2{

	
	protected List<Instance> ListInstance;
	protected String resultXML;
	//private Integer action;
	protected Model model;
	
	public AbstractRepository2() {
		ListInstance = new ArrayList<Instance>();
		resultXML = "";
	}
	
	
	
	public void setModel(Model model) {
		this.model=model;
	}
	
	public Integer addInstance(Entity entity,Map<Attribute,String> valeurs) {
		Integer id = ListInstance.size();
		Instance instance = new Instance(id,entity);
		instance.setvaleurs(valeurs);
		ListInstance.add(instance);
		return id;
	}
	
	public Instance getInstance(Integer id){
		for(Instance instance:ListInstance) {
			if(instance.getId()==id) {
				return instance;
			}
		}
		return null;
	}
	
	
	public String exportListInstanceToXML() {
		String ListInstanceXML = "";
		ListInstanceXML += "<Instances>\n";
		for(Instance instance:ListInstance) {
			ListInstanceXML += exportInstanceToXML(instance);
			ListInstanceXML += "\n";
		}
		ListInstanceXML += "</Instances>\n";
		return ListInstanceXML;
	}
	public String exportInstanceToXML(Instance instance) {
		String InstanceXML = "";
		InstanceXML = "<";
		InstanceXML += instance.getEntity().getName();
		InstanceXML += " id=\"";
		InstanceXML += instance.getId();
		InstanceXML += "\"";
		for(Attribute attribute:instance.getEntity().getListAttribute()) {
			InstanceXML += " ";
			InstanceXML += exportAttributeToXML(attribute,instance.getValeurs().get(attribute));
			
		}
		InstanceXML += "/>";
		return InstanceXML;
	}
	public String exportAttributeToXML(Attribute attribute,String valeur) {
		String ArrtibuteXML = "";
		ArrtibuteXML = attribute.getName() + "=\"" + valeur + "\"";
		return ArrtibuteXML;
	}
	
	public List<Integer> getIds(String valeur){
		List<Integer> ids = new ArrayList<Integer>();
		valeur = valeur.replace(" ","");
		String[] valeurs = valeur.split("#");
		for(String id:valeurs) {
			if(!id.isEmpty()) {
				ids.add(Integer.parseInt(id));
			}
		}
		return ids;
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
	
	public String export() {
		String result = "";
		//System.out.println("---> INSTANCES :");
		/*for(Object obj:listInstance) {
			result += obj.toString();
			result += "\n";
		}*/
		return result;
	}
	
	public void MaterializeInstance(Document document) {
		//lecture des model
		ListInstance.clear();
		Node racine = (Node) document.getDocumentElement();
		NodeList nl = racine.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Instance instance = GenerationInstance((Node) nl.item(i));
			if(instance!=null) {
				ListInstance.add(instance);
			}
		}
	}
	
	public Instance GenerationInstance(Node element) {
		Instance instance;
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return null;
		String entityName = element.getNodeName();
		String attributeName;
		String attributeValue;
		String id = element.getAttributes().item(0).getNodeValue();
		instance = new Instance(Integer.parseInt(id), getEntity(entityName));
		//Instance instance = new Instance();
		for(int i = 1;i<element.getAttributes().getLength();i++) {
			attributeName	= element.getAttributes().item(i).getNodeName();
			attributeValue	= element.getAttributes().item(i).getNodeValue();
			instance.addvaleur(getAttribute(getEntity(entityName), attributeName), attributeValue);
		}
		return instance;
	}
	
	protected void action(Visitable obj) {
		/*switch (action) {
		case 1:
			addInstance(obj);
			break;
		case 2:
			
			break;
			
		case 3:
			
			break;
		default:
			
		}*/
	}
}
