package test.generateFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import repository.AbstractRepository3;
import repository.InstanceModel;

public class Repository3 extends AbstractRepository3 implements VisitorModel {

	String resultXML = "";

	@Override
	public String exportInstanceToXML(InstanceModel instance) {
		resultXML = "";
		instance.accept(this);
		return resultXML;
	}

	@Override
	public InstanceModel generateInstance(String className, Map<String, String> value) {
		switch (className) {
		case "Satellite":
			return generateSatellite(value);
		case "Flotte":
			return generateFlotte(value);
		default:
			return null;
		}
	}
	
	private Satellite generateSatellite(Map<String, String> value) {
		Satellite instance = new Satellite();
		instance.setidSatellite(stringToInteger(value.get("idSatellite")));
		instance.setnom(stringToString(value.get("nom")));
		//System.out.println(value.keySet());
		instance.setvoisin(stringToInstance(value.get("voisin"), instance));
		instance.setvoisin2(stringToInstance(value.get("voisin2"), instance));
		return instance;
	}
	
	private Flotte generateFlotte(Map<String, String> value) {
		Flotte instance = new Flotte();
		instance.setlistS(stringToList(value.get("listS"),new Satellite()));
		return instance;
	}

	@Override
	public void visit(Satellite instance) {
		String instanceXML = "";
		instanceXML = "<";
		instanceXML += instance.getClass().getSimpleName();
		instanceXML += " id=\"";
		instanceXML += instance.getId();
		instanceXML += "\"";
		instanceXML += attributeToString("idSatellite",instance.getidSatellite());
		instanceXML += attributeToString("voisin",instance.getvoisin());
		instanceXML += attributeToString("voisin2",instance.getvoisin2());
		instanceXML += attributeToString("listS",cast(instance.getlistS()));
		instanceXML += "/>";
		resultXML+=instanceXML;
	}
	
	
	@Override
	public void visit(Flotte instance) {
		
		String instanceXML = "";
		instanceXML = "<";
		instanceXML += instance.getClass().getSimpleName();
		instanceXML += " id=\"";
		instanceXML += instance.getId();
		instanceXML += "\"";
		instanceXML += attributeToString("listS",cast(instance.getlistS()));
		instanceXML += "/>";
		resultXML+=instanceXML;
	}
	
	String attributeToString(String parameterName,Integer value) {
		String result=" ";
		result+=parameterName;
		result+="=\"";
		result+=attributeToString(value);
		result+="\"";
		return result;
	}
	
	List<InstanceModel> cast(List<? extends InstanceModel> list){
		List<InstanceModel> liste = new ArrayList<>();
		if(list==null)
			return liste;
		for(int i =0;i<list.size();i++) {
			liste.add(list.get(i));
		}
		return liste;
	}

	
	String attributeToString(Integer value) {
		String result="";
		result+=Integer.toString(value);
		return result;
	}
	
	String attributeToString(String parameterName,String value) {
		String result=" ";
		result+=parameterName;
		result+="=\"";
		result+=attributeToString(value);
		result+="\"";
		return result;
	}
	
	String attributeToString(String value) {
		String result="";
		result+=value;
		return result;
	}
	
	
	String attributeToString(String parameterName,InstanceModel value) {
		String result=" ";
		result+=parameterName;
		result+="=\"";
		if(value!=null)
			result+=attributeToString(value);
		result+="\"";
		return result;
	}
	
	String attributeToString(InstanceModel value) {
		String result="";
		result+="#";
		result+=value.getId();
		return result;
	}
	
	String attributeToString(String parameterName,List<InstanceModel> value) {
		String result=" ";
		result+=parameterName;
		result+="=\"";
		result+=attributeToString(value);
		result+="\"";
		return result;
	}
	
	String attributeToString(List<InstanceModel> list) {
		String result="";
		for(int i=0;i<list.size();i++) {
			result+=attributeToString(list.get(i));
			result+=" ";
		}
		return result;
	}
	
	Integer stringToInteger(String value) {
		Integer result=0;
		result=Integer.valueOf(value);
		return result;
	}
	
	String stringToString(String value) {
		String result="";
		result=value;
		return result;
	}
	
	<E extends InstanceModel> ArrayList<E> stringToList(String value,E var) {
		ArrayList<E> result = new ArrayList<>();
		for(Integer id:getIds(value)) {
			result.add((E) getInstance(id));
		}
		return result;
	}
	
	<E extends InstanceModel> E stringToInstance(String value,E var) {
		E result;
		result=(E) getInstance(getId(value));
		return result;
	}
	
	public Satellite stringToSatellite(String value){
		return (Satellite) getInstance(getId(value));
	}
}
