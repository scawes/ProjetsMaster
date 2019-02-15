package dependance;

import java.util.ArrayList;
import java.util.List;

import model.Entity;

public class GestionDependance {
	List<Dependance> listDependance;

	public GestionDependance() {
		listDependance = new ArrayList<Dependance>();
	}
	
	public List<Dependance> getListDependance() {
		return listDependance;
	}
	
	public void addDependance(Dependance dependance) {
		listDependance.add(dependance);
	}
	
	public Dependance getDependance(Entity entity) {
		for(Dependance dependance : listDependance) {
			if(dependance.equals(entity)) {
				return dependance;
			}
		}
		return null;
	}
}
