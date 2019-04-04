package test.generateFiles;
import java.util.ArrayList;
import test.generateFiles.Satellite;
public class Flotte extends repository.InstanceModel implements VisitableModel{
	ArrayList<Satellite> listS;
public Flotte(){
}
public Flotte(ArrayList<Satellite> listS){
this.listS	= listS;
}
@Override
public void accept(VisitorModel v) {
v.visit(this);
}
public ArrayList<Satellite> getlistS(){
return listS;
}
public void setlistS(ArrayList<Satellite> listS){
this.listS	=listS;
}
}
