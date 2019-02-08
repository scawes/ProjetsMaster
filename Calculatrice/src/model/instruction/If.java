package model.instruction;

import model.Element;
import model.Expr;
import visiteur.Visiteur;

public class If extends Instruction{

	Block block;
	Expr expr;
	
	public If(Element parent,Block block,Expr expr) {
		super(parent);
		this.block = block;
		this.expr = expr;
	}
	
	public If(Element parent) {
		super(parent);
	}
	
	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	@Override
	public void accept(Visiteur v) {
		v.visite(this);	
	}

}
