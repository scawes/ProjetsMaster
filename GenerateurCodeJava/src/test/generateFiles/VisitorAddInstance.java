package test.generateFiles;

import java.util.ArrayList;
import java.util.List;

public class VisitorAddInstance{

	List<Object> listIntance;
	
	public VisitorAddInstance() {
		listIntance= new ArrayList<Object>();
	}
	
	public List<Object> getResult(){
		return listIntance;
	}
	
	private void addInstance(Object obj) {
		listIntance.add(obj);
	}
	
	public void visit(Flotte obj) {
		for(Satellite s :obj.getlistS())
			visit(s);
		addInstance(obj);
	}
	

	public void visit(Satellite obj) {
		for(Satellite s :obj.getlistS())
			visit(s);
		visit(obj.getvoisin());
		visit(obj.getvoisin2());
		addInstance(obj);
	}
	
}
