package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import visitor.Visitable;

public abstract class AbstractRepository{

protected List<Visitable> listModel;
protected List<Object> listInstance;
protected String resultXML;
protected Map<Object,Integer> mapInstanceId;
protected Map<Integer,Object> mapIdInstance;
private Integer action;
	
	public AbstractRepository() {
		listModel = new ArrayList<Visitable>();
		listInstance = new ArrayList<Object>();
		resultXML = "";
		mapInstanceId = new HashMap<Object,Integer>();
		mapIdInstance = new HashMap<Integer,Object>();
	}
	
	public void addModel(Visitable model) {
		listModel.add(model);
	}
	
	public void addInstance(Visitable instance) {
		listInstance.add(instance);
	}
	
	public void addAllInstance(Visitable model) {
		VisitorAddInstance visitor = new VisitorAddInstance();
		model.accept(visitor);
		listInstance.addAll(visitor.getResult());
	}
	
	public String exportInstanceToXML() {
		resultXML = "";
		mapIdInstance.clear();
		for(int i=0;i<listInstance.size();i++) {
			mapInstanceId.put(listInstance.get(i), i);
		}
		for(Object obj:listInstance) {
			visit(obj);
		}
		
		return resultXML;
	}
	
	public abstract void visit(Object obj);
	
	public String export() {
		String result = "";
		//System.out.println("---> INSTANCES :");
		for(Object obj:listInstance) {
			result += obj.toString();
			result += "\n";
		}
		return result;
	}
	
	protected void action(Visitable obj) {
		switch (action) {
		case 1:
			addInstance(obj);
			break;
		case 2:
			
			break;
			
		case 3:
			
			break;
		default:
			
		}
	}
}
