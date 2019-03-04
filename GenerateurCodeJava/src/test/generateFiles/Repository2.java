package test.generateFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Attribute;
import repository.AbstractRepository2;
import repository.Instance;

public class Repository2 extends AbstractRepository2 {

	public Integer saveInstance(Flotte flotte) {
		Attribute attribute = getAttribute(getEntity("Flotte"), "listS");
		Map<Attribute,String> valeur = new HashMap<Attribute,String>();
		String ids = "";
		for(Satellite satellite:flotte.getlistS()) {
			ids+="#"+saveInstance(satellite)+" ";
		}
		valeur.put(attribute, ids);
		return addInstance(getEntity("Flotte"),valeur);
	}
	public Integer saveInstance(Satellite satellite) {
		//Attribute attribute = getAttribute(getEntity("Flotte"), "getlistS");
		Map<Attribute,String> valeur = new HashMap<Attribute,String>();
		return addInstance(getEntity("Satellite"),valeur);
	}
	public Flotte restoreFlotte(Instance instance) {
		String ids = instance.getValeurs().get(getAttribute(getEntity("Flotte"), "listS"));
		ArrayList<Satellite> listS = new ArrayList<Satellite>();
		for(Integer id:getIds(ids)) {
			listS.add(restoreSatellite(getInstance(id)));
		}
		
		Flotte flotte = new Flotte(listS);
		return flotte;
	}
	
	public Satellite restoreSatellite(Instance instance) {
		
		Satellite Satellite = new Satellite();
		return Satellite;
	}
}
