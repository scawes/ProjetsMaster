package test.generateFiles;
import test.generateFiles.Flotte;
public class Satellite extends Flotte implements VisitableModel{
	String nom	= "space";
	Satellite voisin;
	Satellite voisin2;
	Integer idSatellite	= 7;
public Satellite(){
}
public Satellite(String nom,Satellite voisin,Satellite voisin2,Integer idSatellite){
this.nom	= nom;
this.voisin	= voisin;
this.voisin2	= voisin2;
this.idSatellite	= idSatellite;
}
@Override
public void accept(VisitorModel v) {
v.visit(this);
}
public String getnom(){
return nom;
}
public void setnom(String nom){
this.nom	=nom;
}
public Satellite getvoisin(){
return voisin;
}
public void setvoisin(Satellite voisin){
this.voisin	=voisin;
}
public Satellite getvoisin2(){
return voisin2;
}
public void setvoisin2(Satellite voisin2){
this.voisin2	=voisin2;
}
public Integer getidSatellite(){
return idSatellite;
}
public void setidSatellite(Integer idSatellite){
this.idSatellite	=idSatellite;
}
}
