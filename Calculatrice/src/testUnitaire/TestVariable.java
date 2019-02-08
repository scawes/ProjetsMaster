package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ExpUnaire;
import model.Programme;
import model.instruction.Affectation;
import model.operateur.PlusExp;
import model.valeur.IntExp;
import model.variable.Reference;
import model.variable.VariableDef;
import model.variable.VariableRef;
import visiteur.VisiteurExecution;

class TestVariable {

	Programme programme;
	
	@BeforeEach
	void before() {
		programme = new Programme(null);
	}
	
	@Test
	void test1() {
		
		VisiteurExecution v = new VisiteurExecution();
		VariableDef var = programme.addVariable("a", "int", "public");
		programme.setVar(var, new IntExp(programme,5));
		
		VariableRef e =programme.getReference("a");
		
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
		
		VisiteurExecution v = new VisiteurExecution();
		VariableDef var = programme.addVariable("a", "int", "public");
		
		Affectation affectation = new Affectation(programme);
		
		VariableRef e = new VariableRef(programme,var);
		affectation.setVariable(e);
		ExpUnaire valeur = new IntExp(programme,5);
		affectation.setValeur(valeur);
		
		affectation.accept(v);
		ExpUnaire result = programme.getVar(var);
		if( result instanceof IntExp) {
			IntExp i = (IntExp) result;
			assertTrue(i.getValue() == 5);
		} else {
			assertFalse(true);
		}
	}
	
	@Test
	void test3() {
		
		VisiteurExecution v = new VisiteurExecution();
		VariableDef var = programme.addVariable("a", "int", "public");
		
		Affectation affectation = new Affectation(programme);
		
		VariableRef e = new VariableRef(affectation,var);
		affectation.setVariable(e);
		
		PlusExp valeur = new PlusExp(affectation);
		valeur.setOpg(new IntExp(valeur,5));
		valeur.setOpd(new IntExp(valeur,3));
		affectation.setValeur(valeur);
		
		affectation.accept(v);
		ExpUnaire result = programme.getVar(var);
		if( result instanceof IntExp) {
			IntExp i = (IntExp) result;
			assertTrue(i.getValue() == 8);
		} else {
			assertFalse(true);
		}
	}
	
	@Test
	void test4() {
		
		VisiteurExecution v = new VisiteurExecution();
		//var a
		programme.addVariable("a", "int", "public");
		
		Affectation affectation1 = new Affectation(programme);
		
		VariableRef ref1 = new VariableRef(affectation1,programme.getDef("a"));
		affectation1.setVariable(ref1);
		
		PlusExp valeur1 = new PlusExp(affectation1);
		valeur1.setOpg(new IntExp(valeur1,5));
		valeur1.setOpd(new IntExp(valeur1,3));
		affectation1.setValeur(valeur1);
		
		assertTrue(ref1!=null);
		if(ref1!=null) {
			affectation1.accept(v);
		}
		//var b
		Affectation affectation2 = new Affectation(programme);
		programme.addVariable("b", "int", "public");
		VariableRef ref2 = new VariableRef(affectation2,programme.getDef("b"));
		affectation2.setVariable(ref2);
		
		PlusExp valeur2 = new PlusExp(affectation2);
		valeur2.setOpg(new IntExp(valeur1,2));
		valeur2.setOpd(ref1);
		affectation2.setValeur(valeur2);
		
		assertTrue(ref2!=null);
		if(ref2!=null) {
			affectation2.accept(v);
		}
		
		ExpUnaire result = programme.getVar(programme.getDef("b"));
		if( result instanceof IntExp) {
			IntExp i = (IntExp) result;
			assertTrue(i.getValue() == 10);
		} else {
			assertFalse(true);
		}
	}
	
	@Test
	void test5() {
		
		VisiteurExecution v = new VisiteurExecution();
		//var a
		programme.addVariable("a", "int", "public");
		
		Affectation affectation1 = new Affectation(programme);
		
		Reference ref1 = affectation1.getReference("a");
		affectation1.setVariable(ref1);
		
		PlusExp valeur1 = new PlusExp(affectation1);
		valeur1.setOpg(new IntExp(valeur1,5));
		valeur1.setOpd(new IntExp(valeur1,3));
		affectation1.setValeur(valeur1);
		
		if(ref1 instanceof VariableRef) {
			affectation1.accept(v);
		}
		
		//var b
		programme.addVariable("b", "int", "public");
		
		Affectation affectation2 = new Affectation(programme);
		Reference ref2 = affectation2.getReference("b");
		affectation2.setVariable(ref2);
		PlusExp valeur2 = new PlusExp(affectation2);
		valeur2.setOpg(new IntExp(valeur2,2));
		valeur2.setOpd(ref1);
		affectation2.setValeur(valeur2);
		
		if(ref2 instanceof VariableRef) {
			affectation2.accept(v);
		}
		
		ExpUnaire result = programme.getVar(programme.getDef("b"));
		if( result instanceof IntExp) {
			IntExp i = (IntExp) result;
			assertTrue(i.getValue() == 10);
		} else {
			assertFalse(true);
		}
	}
}
