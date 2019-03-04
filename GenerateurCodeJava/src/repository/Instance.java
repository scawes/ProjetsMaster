package repository;

import java.util.HashMap;
import java.util.Map;

import model.Attribute;
import model.Entity;

public class Instance {
	Integer id;
	Entity entity;
	Map<Attribute,String> valeurs;
	public Instance(Integer id,Entity entity){
		this.id	= id;
		this.entity	= entity;
		valeurs = new HashMap<Attribute,String>();
	}
	public void addvaleur(Attribute attr,String valeur){
		valeurs.put(attr, valeur);
	}
	public void setvaleurs(Map<Attribute,String> valeurs){
		this.valeurs	= valeurs;
	}
	public Integer getId() {
		return id;
	}
	public Entity getEntity() {
		return entity;
	}
	public Map<Attribute, String> getValeurs() {
		return valeurs;
	}
	
}
