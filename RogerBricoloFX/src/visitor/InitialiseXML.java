package visitor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class InitialiseXML {
	PrintWriter writer;
	
	public InitialiseXML(String NomFichier) {
		
		try {
			writer = new PrintWriter(NomFichier+".xml");
			writer.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public InitialiseXML(String NomFichier,String dtd) {
		this(NomFichier);
		writer.println(dtd);
	}
	public void AjouteElementXML(String element) {
			writer.println(element);
	}
	
	
	public static String retourneList(String Balise,List<String> list) {
		String valeur = "";
		valeur	= "<List_"+Balise+">";
		for(String d : list) {
			valeur	+= retourneChamp(Balise,d);
		}
		valeur	+= "</List_"+Balise+">";
		return valeur;
	}
	public static String retourneChamp(String Balise,String Valeur) {
		return "<"+Balise+">"+Valeur+"</"+Balise+">";
	}
	public static String retourneChamp(String Balise) {
		return "<"+Balise+"/>";
	}
	public static String retourneChampOptionnel(String Balise,String Valeur) {
		if(Valeur!=null) {
			return "<"+Balise+">"+Valeur+"</"+Balise+">";
		}
		return "";
	}
	public static String retourneAttribut(String Attribut,String Valeur) {
		if(Valeur==null){
			Valeur="";
		}
		return " "+Attribut+"=\""+Valeur+"\"";
	}
	public static String retourneListAttribut(String Balise,String Attribut,List<String> list) {
		String valeur = "";
		valeur	= "<List_"+Balise+">";
		for(String d : list) {
			valeur	+= retourneChamp(Balise+retourneAttribut(Attribut,d));
		}
		valeur	+= "</List_"+Balise+">";
		return valeur;
	}
	
	public void Ferme() {
		writer.close();
	}
}
