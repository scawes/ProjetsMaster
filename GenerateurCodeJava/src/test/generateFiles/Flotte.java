package test.generateFiles;
import java.util.ArrayList;
import test.generateFiles.Satellite;
public class Flotte{
	ArrayList<Satellite> listS;
public Flotte(){
}
public Flotte(ArrayList<Satellite> listS){
this.listS	= listS;
}
public ArrayList<Satellite> getlistS(){
return listS;
}
public void setlistS(ArrayList<Satellite> listS){
this.listS	=listS;
}
}
