package genarate;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import dependance.Dependance;
import dependance.DependanceClass;
import dependance.DependancePrimitive;
import model.Attribute;
import model.Entity;
import model.Model;
import model.attribute.collection.AttrArray;
import model.attribute.collection.AttrList;
import model.attribute.simple.AttrAssociation;
import model.attribute.simple.AttrUndefind;
import model.attribute.simple.basic.AttrInteger;
import model.attribute.simple.basic.AttrString;
import model.heritage.HeritageEntity;
import model.heritage.HeritageUndefind;
import visitor.Visitor;

public class VisitorGenerateFileJava implements Visitor{
	PrintWriter writer;
	String path;

	public VisitorGenerateFileJava(String path) {
		this.path = path;
	}

	
	@Override
	public void visit(Attribute attribute) {
		writer.print("\t");
		attribute.getType().accept(this);
		writer.print(" ");
		writer.print(attribute.getName());
		if(!attribute.getValeur().isEmpty()) {
			writer.print("\t");
			writer.print("= ");
			writer.print(attribute.getValeur().replace("'", "\""));
		}
		writer.println(";");
	}

	@Override
	public void visit(Model model) {
		
		//result+="package ";
		//result+=model.getName();
		//result+="\n";
		for(Entity entity:model.getListEntity()) {
			entity.accept(this);
		}
	}

	@Override
	public void visit(Entity entity) {
		try {
			writer = new PrintWriter(path+entity.getName()+".java", "UTF-8");
			writer.print("package ");
			writer.print(entity.getClassPackage());
			writer.println(";");
			//dependence
			for(Dependance dependance:entity.getDependances()) {
				dependance.accept(this);
			}
		
			//declaration class
			
			writer.print("public class ");
			writer.print(entity.getName());
			entity.getHeritage().accept(this);
			writer.println("{");
			
			//declaration variable
			for(Attribute attribute:entity.getListAttribute()) {
				attribute.accept(this);
			}
			//constructor
			VisitorGenerateConstructor constructeur = new VisitorGenerateConstructor();
			entity.accept(constructeur);
			writer.print(constructeur.getResult());
			//getter setter
			VisitorGenerateGetSet getset = new VisitorGenerateGetSet();
			entity.accept(getset);
			writer.print(getset.getResult());
			writer.println("}");
			//ajout de la classe
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void visit(AttrList attrList) {
		writer.print(attrList.getName());
		writer.print("<");
		attrList.getListOf().accept(this);
		writer.print(">");
	}

	@Override
	public void visit(AttrArray attrArray) {
		writer.print(attrArray.getName());
		writer.print("<");
		attrArray.getListOf().accept(this);
		writer.print(">");
	}

	@Override
	public void visit(AttrUndefind attrUndefind) {
		writer.print("Undefind");
	}

	@Override
	public void visit(AttrAssociation attrAssociation) {
		writer.print(attrAssociation.getTypeOf().getName());
	}

	@Override
	public void visit(AttrString attrString) {
		writer.print(attrString.getName());
	}

	@Override
	public void visit(AttrInteger attrInteger) {
		writer.print(attrInteger.getName());
	}

	@Override
	public void visit(HeritageUndefind heritageUndefind) {
		
	}

	@Override
	public void visit(HeritageEntity heritageEntity) {
		writer.print(" extends "+heritageEntity.getEntity().getName());
	}

	@Override
	public void visit(DependancePrimitive dependancePrimitive) {
		writer.print("import "+dependancePrimitive.getPath());
		writer.println(";");
	}

	@Override
	public void visit(DependanceClass dependanceClass) {
		writer.print("import "+dependanceClass.getPath());
		writer.println(";");
	}

}
