package model;

import model.instruction.Block;
import visiteur.Visiteur;

public class Programme extends Element {

	Block block;
	
	public Programme(Element parent) {
		super(parent);
	}
	
	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);	
	}

}
