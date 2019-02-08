package visiteur;

import model.Element;
import model.Programme;
import model.instruction.Affectation;
import model.instruction.Block;
import model.instruction.If;
import model.instruction.SortieStandard;
import model.operateur.EgalExp;
import model.operateur.MultExp;
import model.operateur.PlusExp;
import model.valeur.BoolExp;
import model.valeur.DoubleExp;
import model.valeur.IntExp;
import model.valeur.StringExp;
import model.variable.UnresolveSymbol;
import model.variable.VariableDef;
import model.variable.VariableRef;

public class VisiteurAffichage implements Visiteur{

	String result;
	
	public VisiteurAffichage() {
		result="";
	}
	
	public String getResult() {
		return result;
	}
	
	@Override
	public void visite(IntExp intExp) {
		result+=intExp.getValue();
	}

	@Override
	public void visite(MultExp multExp) {
		multExp.getOpg().accept(this);
		result+="*";
		multExp.getOpd().accept(this);
	}

	@Override
	public void visite(PlusExp plusExp) {
		plusExp.getOpg().accept(this);
		result+="+";
		plusExp.getOpd().accept(this);
	}

	@Override
	public void visite(DoubleExp doubleExp) {
		result+=doubleExp.getValue();
	}

	@Override
	public void visite(StringExp stringExp) {
		result+=stringExp.getValue();
	}

	@Override
	public void visite(VariableRef variableRef) {
		result+=variableRef.getNom();
	}

	@Override
	public void visite(UnresolveSymbol unresolveSymbol) {
		result+=unresolveSymbol.getNom();
	}

	@Override
	public void visite(Affectation affectation) {
		result+=affectation.getVariable().getNom()+":=";
		affectation.getValeur().accept(this);
		result+=".\n";
		
	}

	@Override
	public void visite(SortieStandard sortieStandard) {
		result+="print(";
		sortieStandard.getValeur().accept(this);
		result+=").\n";
	}

	@Override
	public void visite(BoolExp boolExp) {
		result+=boolExp.getValue();
	}

	@Override
	public void visite(EgalExp egalExp) {
		egalExp.getOpg().accept(this);
		result+="=";
		egalExp.getOpd().accept(this);
	}

	@Override
	public void visite(Block block) {
		for(Element instruction : block.getInstructions()) {
			instruction.accept(this);
		}
	}

	@Override
	public void visite(If if1) {
		result+="if(";
		if1.getExpr().accept(this);
		result+="){\n";
		if1.getBlock().accept(this);
		result+="}\n";
	}

	@Override
	public void visite(Programme programme) {
		programme.getBlock().accept(this);
	}

	@Override
	public void visite(VariableDef variableDef) {
		result+=":";
		result+=variableDef.getNom();
		result+=".\n";
	}

}
