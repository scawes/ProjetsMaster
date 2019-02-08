package pile;

import java.util.Stack;

import model.ExpUnaire;
import model.operateur.EgalExp;
import model.operateur.MultExp;
import model.operateur.PlusExp;
import model.valeur.BoolExp;
import model.valeur.DoubleExp;
import model.valeur.IntExp;
import model.valeur.StringExp;

public class PileCalcul {

	private Stack<ExpUnaire> pile;
	
	public PileCalcul() {
		pile = new Stack<ExpUnaire>();
	}
	
	public void put(ExpUnaire valeur) {
		pile.push(valeur);
	}
	
	public void put(PlusExp valeur) {
		ExpUnaire v1 = pile.pop();
		ExpUnaire v2 = pile.pop();

		switch (v1.getClass().getSimpleName()+";"+v2.getClass().getSimpleName()) {
		case "IntExp;IntExp" :
			IntExp i1 = (IntExp) v1;
			IntExp i2 = (IntExp) v2;
			put(new IntExp(valeur,i1.getValue()+i2.getValue()));
			break;
		case "IntExp;DoubleExp" :
			IntExp i3 = (IntExp) v1;
			DoubleExp d1 = (DoubleExp) v2;
			put(new DoubleExp(valeur,i3.getValue()+d1.getValue()));
			break;
		case "DoubleExp;IntExp" :
			DoubleExp d2 = (DoubleExp) v1;
			IntExp i4 = (IntExp) v2;
			put(new DoubleExp(valeur,d2.getValue()+i4.getValue()));
			break;
		case "DoubleExp;DoubleExp" :
			DoubleExp d3 = (DoubleExp) v1;
			DoubleExp d4 = (DoubleExp) v2;
			put(new DoubleExp(valeur,d3.getValue()+d4.getValue()));
			break;
			
		case "StringExp;StringExp" :
			StringExp s3 = (StringExp) v1;
			StringExp s4 = (StringExp) v2;
			put(new StringExp(valeur,s3.getValue()+s4.getValue()));
			break;
		default :
			break;
		}		
		
	}
	
	public void put(MultExp valeur) {
		ExpUnaire v1 = pile.pop();
		ExpUnaire v2 = pile.pop();
		
		switch (v1.getClass().getSimpleName()+";"+v2.getClass().getSimpleName()) {
		case "IntExp;IntExp" :
			IntExp i1 = (IntExp) v1;
			IntExp i2 = (IntExp) v2;
			put(new IntExp(valeur,i1.getValue()*i2.getValue()));
			break;
		case "IntExp;DoubleExp" :
			IntExp i3 = (IntExp) v1;
			DoubleExp d1 = (DoubleExp) v2;
			put(new DoubleExp(valeur,i3.getValue()*d1.getValue()));
			break;
		case "DoubleExp;IntExp" :
			DoubleExp d2 = (DoubleExp) v2;
			IntExp i4 = (IntExp) v1;
			put(new DoubleExp(valeur,d2.getValue()*i4.getValue()));
			break;
		case "DoubleExp;DoubleExp" :
			DoubleExp d3 = (DoubleExp) v2;
			DoubleExp d4 = (DoubleExp) v1;
			put(new DoubleExp(valeur,d3.getValue()*d4.getValue()));
			break;
		default :
			break;
		
		}	
	}
	
	public ExpUnaire get() {
		return pile.peek();
	}
	
	public void reset() {
		pile.clear();
	}

	public void put(EgalExp egalExp) {
		ExpUnaire v1 = pile.pop();
		ExpUnaire v2 = pile.pop();
		
		switch (v1.getClass().getSimpleName()+";"+v2.getClass().getSimpleName()) {
		case "IntExp;IntExp" :
			IntExp i1 = (IntExp) v1;
			IntExp i2 = (IntExp) v2;
			put(new BoolExp(egalExp,i1.getValue()==i2.getValue()));
			break;
		case "IntExp;DoubleExp" :
			IntExp i3 = (IntExp) v1;
			DoubleExp d1 = (DoubleExp) v2;
			put(new BoolExp(egalExp,i3.getValue()==d1.getValue()));
			break;
		case "DoubleExp;IntExp" :
			DoubleExp d2 = (DoubleExp) v2;
			IntExp i4 = (IntExp) v1;
			put(new BoolExp(egalExp,d2.getValue()==i4.getValue()));
			break;
		case "DoubleExp;DoubleExp" :
			DoubleExp d3 = (DoubleExp) v2;
			DoubleExp d4 = (DoubleExp) v1;
			put(new BoolExp(egalExp,d3.getValue()==d4.getValue()));
			break;
		case "StringExp;StringExp" :
			StringExp s3 = (StringExp) v1;
			StringExp s4 = (StringExp) v2;
			put(new BoolExp(egalExp,s3.getValue()==s4.getValue()));
			break;
		default :
			break;
		
		}	
	}
	
	
}
