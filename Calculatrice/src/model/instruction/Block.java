package model.instruction;

import java.util.ArrayList;
import java.util.List;

import model.Element;
import visiteur.Visiteur;

public class Block extends Instruction{

	List<Element> instructions;
	
	public Block(Element parent) {
		super(parent);
		instructions = new ArrayList<Element>();
	}
	
	public List<Element> getInstructions() {
		return instructions;
	}
	
	public void addInstruction(Element instruction) {
		instructions.add(instruction);
	}
	
	@Override
	public void accept(Visiteur v) {
		v.visite(this);	
	}

}
