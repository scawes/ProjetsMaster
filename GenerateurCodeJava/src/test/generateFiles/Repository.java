package test.generateFiles;

import repository.AbstractRepository;

public class Repository extends AbstractRepository {

	public void visit(Flotte attribute) {
		resultXML += toXML(attribute);
	}
	
	public String toXML(Flotte obj) {
		Integer id = mapInstanceId.get(obj);
		String balise = "";
		balise+="<";
		balise+="Flotte";
		//id
		balise+=" ";
		balise+="id";
		balise+="='";
		balise+=id;
		balise+="'";
		//attr
		balise+=" ";
		balise+="listS";
		balise+="='(";
		for(Satellite s:obj.getlistS()) {
			balise+="#"+mapInstanceId.get(s);
			balise+=" ";
		}
		balise+="')";
		//fin
		balise+="/>\n";
		return balise;		
	}
	public void toObject(String attribute) {
		
	}

	@Override
	public void visit(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	public void initInstance(Flotte obj) {
		test.generateFiles.VisitorAddInstance visitor = new test.generateFiles.VisitorAddInstance();
		visitor.visit(obj);
		listInstance.addAll(visitor.getResult());
	}
}
