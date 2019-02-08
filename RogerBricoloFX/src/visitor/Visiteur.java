package visitor;

import model.Garage;
import model.Moto;
import model.Reparation;
import model.Societe;
import model.Voiture;

public interface Visiteur {
	void visit(Garage o);
    void visit(Reparation o);
    void visit(Voiture o);
    void visit(Moto o);
	void visit(Societe societe);
}
