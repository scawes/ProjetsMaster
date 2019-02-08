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

public class VisiteurVerification implements Visiteur{

	boolean error= false;
	
	public VisiteurVerification() {
	}
	
	public boolean getResult() {
		return error;
	}
	
	@Override
	public void visite(IntExp intExp) {
		
	}

	@Override
	public void visite(MultExp multExp) {
		if(multExp.getOpg() == null) {
			System.out.println("error id");
			error = true;
		}
		else {
			multExp.getOpg().accept(this);
		}
		if(multExp.getOpd() == null) {
			System.out.println("error id");
			error = true;
		}
		else {
			multExp.getOpd().accept(this);
		}
		
		
	}

	@Override
	public void visite(PlusExp plusExp) {
		
		if(plusExp.getOpg() == null) {
			System.out.println("error id");
			error = true;
		}
		else {
			plusExp.getOpg().accept(this);
		}
		if(plusExp.getOpd() == null) {
			System.out.println("error id");
			error = true;
		}
		else {
			plusExp.getOpd().accept(this);
		}
	}

	@Override
	public void visite(DoubleExp doubleExp) {

	}

	@Override
	public void visite(StringExp stringExp) {

	}

	@Override
	public void visite(VariableRef variableRef) {

	}

	@Override
	public void visite(UnresolveSymbol unresolveSymbol) {
		VariableRef var = unresolveSymbol.getReference(unresolveSymbol);
		if(var!=null) {
			var.accept(this);
		}else {
			 System.out.println("Unknow reference "+unresolveSymbol.getNom());
			 error = true;
		}
	}

	@Override
	public void visite(Affectation affectation) {
		VariableRef varRef= affectation.getReference(affectation.getVariable().getNom());	
		if(varRef==null) {
			System.out.println("Unknow reference  "+affectation.getVariable().getNom());
			 error = true;
		}
		affectation.getValeur().accept(this);
	}

	@Override
	public void visite(SortieStandard sortieStandard) {
		if(sortieStandard.getValeur() == null) {
			System.out.println("error id");
			error = true;
		} else {
			sortieStandard.getValeur().accept(this);
		}
	}

	@Override
	public void visite(BoolExp boolExp) {
		
	}

	@Override
	public void visite(EgalExp egalExp) {
		if(egalExp.getOpg() == null) {
			System.out.println("error id");
			error = true;
		}
		else {
			egalExp.getOpg().accept(this);
		}
		if(egalExp.getOpd() == null) {
			System.out.println("error id");
			error = true;
		}
		else {
			egalExp.getOpd().accept(this);
		}
	}

	@Override
	public void visite(Block block) {
		for(Element instruction : block.getInstructions()) {
			instruction.accept(this);
		}
	}

	@Override
	public void visite(If if1) {
		if1.getExpr().accept(this);
		if1.getBlock().accept(this);
	}

	@Override
	public void visite(Programme programme) {
		programme.getBlock().accept(this);
	}

	@Override
	public void visite(VariableDef variableDef) {
		variableDef.getParent().addVariable(variableDef.getNom(), variableDef.getType(), variableDef.getVisibilite());
	}

}
