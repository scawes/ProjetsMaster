package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.ExpUnaire;
import model.Programme;
import model.operateur.MultExp;
import model.operateur.PlusExp;
import model.valeur.DoubleExp;
import model.valeur.IntExp;
import visiteur.VisiteurExecution;

class TestVisiteur {

	Programme programme;
	@Before
	void before() {
		programme = new Programme(null);
	}
	
	@Test
	void test1() {
		IntExp e = new IntExp(programme,5);
		VisiteurExecution v = new VisiteurExecution();
		e.accept(v);
		if( v.getResult() instanceof IntExp) {
			IntExp i = (IntExp) v.getResult();
			assertTrue(i.getValue() == 5);
		} else {
			assertFalse(true);
		}
		
	}
	
	@Test
	void test2() {
		PlusExp e = new PlusExp(programme);
		e.setOpg(new IntExp(e,5));
		e.setOpd(new IntExp(e,3));
		
		VisiteurExecution v = new VisiteurExecution();
		e.accept(v);
		ExpUnaire valeur = v.getResult();
		if( valeur instanceof IntExp) {
			IntExp i = (IntExp) v.getResult();
			assertTrue(i.getValue() == 8);
		} else {
			assertFalse(true);
		}
	}
	
	@Test
	void test3() {
		MultExp e1 = new MultExp(programme);
		
		PlusExp e2 = new PlusExp(e1);
		e2.setOpg(new IntExp(e2,5));
		e2.setOpd(new IntExp(e2,3));

		e1.setOpg(e2);
		e1.setOpd(new IntExp(e1,2));
			
		VisiteurExecution v = new VisiteurExecution();
		e1.accept(v);
		if( v.getResult() instanceof IntExp) {
			IntExp i = (IntExp) v.getResult();
			assertTrue(i.getValue() == 16);
		} else {
			assertFalse(true);
		}
	}

	@Test
	void test4() {
		MultExp e1 = new MultExp(programme);
		PlusExp e2 =new PlusExp(e1);
		e2.setOpg(new IntExp(e2,5));
		e2.setOpd(new DoubleExp(e2,3));

		e1.setOpg(e2);
		e1.setOpd(new IntExp(e1,2));
		
		VisiteurExecution v = new VisiteurExecution();
		e1.accept(v);
		if( v.getResult() instanceof DoubleExp) {
			DoubleExp i = (DoubleExp) v.getResult();
			assertTrue(i.getValue() == 16);
		} else {
			assertFalse(true);
		}
	}
}
