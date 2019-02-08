package visiteur;

import model.Element;
import model.ExpUnaire;
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
import pile.PileCalcul;

public class VisiteurExecution implements Visiteur{

	PileCalcul result;
	
	public VisiteurExecution() {
		result=new PileCalcul();
	}
	
	public ExpUnaire getResult() {
		return result.get();
	}
	
	@Override
	public void visite(IntExp intExp) {
		result.put(intExp);
	}

	@Override
	public void visite(MultExp multExp) {
		multExp.getOpg().accept(this);
		multExp.getOpd().accept(this);
		result.put(multExp);
	}

	@Override
	public void visite(PlusExp plusExp) {
		plusExp.getOpg().accept(this);
		plusExp.getOpd().accept(this);
		result.put(plusExp);
	}

	@Override
	public void visite(DoubleExp doubleExp) {
		result.put(doubleExp);
	}

	@Override
	public void visite(StringExp stringExp) {
		result.put(stringExp);
	}

	@Override
	public void visite(VariableRef variableRef) {
		VariableDef vardef= variableRef.getDefinition();
		ExpUnaire valeur = variableRef.getVar(vardef);
		valeur.accept(this);
	}

	@Override
	public void visite(UnresolveSymbol unresolveSymbol) {
		VariableRef var = unresolveSymbol.getReference(unresolveSymbol);
		if(var!=null) {
			var.accept(this);
		}else {
			 System.out.println("Unknow reference "+unresolveSymbol.getNom());
		}
	}

	@Override
	public void visite(Affectation affectation) {
		VariableRef varRef= affectation.getReference(affectation.getVariable().getNom());	
		VariableDef varDef = varRef.getDefinition();
		result.reset();
		affectation.getValeur().accept(this);
		affectation.setVar(varDef, getResult());
	}

	@Override
	public void visite(SortieStandard sortieStandard) {
		sortieStandard.getValeur().accept(this);
		VisiteurAffichage affichage = new VisiteurAffichage();
		ExpUnaire maValeur = this.getResult();
		maValeur.accept(affichage);
		System.out.println(affichage.getResult());
	}

	@Override
	public void visite(BoolExp boolExp) {
		result.put(boolExp);
	}

	@Override
	public void visite(EgalExp egalExp) {
		egalExp.getOpg().accept(this);
		egalExp.getOpd().accept(this);
		result.put(egalExp);
	}

	@Override
	public void visite(Block block) {
		for(Element instruction : block.getInstructions()) {
			instruction.accept(this);
		}
	}

	@Override
	public void visite(If if1) {
		result.put(new BoolExp(if1,true));
		if1.getExpr().accept(this);
		BoolExp traitement = (BoolExp) result.get();
		if(traitement.getValue()) {
			if1.getBlock().accept(this);
		}
		
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
