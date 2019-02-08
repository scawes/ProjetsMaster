package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Programme;
import model.operateur.MultExp;
import model.operateur.PlusExp;
import model.valeur.IntExp;
import visiteur.VisiteurAffichage;

class TestPile {

	Programme programme;
	
	@Before
	void before() {
		programme = new Programme(null);
	}
	
	@Test
	void test1() {
		IntExp e = new IntExp(programme,5);
		VisiteurAffichage v = new VisiteurAffichage();
		e.accept(v);
		assertTrue(v.getResult().equals("5"));
	}
	
	@Test
	void test2() {
		PlusExp e = new PlusExp(programme);
		e.setOpg(new IntExp(e,5));
		e.setOpd(new IntExp(e,3));
		VisiteurAffichage v = new VisiteurAffichage();
		e.accept(v);
		assertTrue(v.getResult().equals("5+3"));
	}
	
	@Test
	void test3() {
		MultExp e1 = new MultExp(programme);
		
		PlusExp e2 = new PlusExp(e1);
		e2.setOpg(new IntExp(e2,5));
		e2.setOpd(new IntExp(e2,3));
		
		e1.setOpg(e2);
		e1.setOpd(new IntExp(e1,2));
		
		VisiteurAffichage v = new VisiteurAffichage();
		e1.accept(v);
		assertTrue(v.getResult().equals("5+3*2"));
	}

}
