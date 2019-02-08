package visiteur;

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

public interface Visiteur {

	void visite(IntExp intExp);

	void visite(MultExp multExp);

	void visite(PlusExp plusExp);

	void visite(DoubleExp doubleExp);

	void visite(StringExp stringExp);

	void visite(VariableRef variableRef);

	void visite(UnresolveSymbol unresolveSymbol);

	void visite(Affectation affectation);

	void visite(SortieStandard sortieStandard);

	void visite(BoolExp boolExp);

	void visite(EgalExp egalExp);

	void visite(Block block);

	void visite(If if1);

	void visite(Programme programme);

	void visite(VariableDef variableDef);

}
