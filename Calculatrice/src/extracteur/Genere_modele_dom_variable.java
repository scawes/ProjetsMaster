package extracteur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Element;
import model.ExpBinaire;
import model.Expr;
import model.Programme;
import model.instruction.Affectation;
import model.instruction.Block;
import model.instruction.If;
import model.instruction.Instruction;
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

public class Genere_modele_dom_variable {

	Map<String, Node> tabId;
	List<Instruction> listInstruction;
	Programme programme;
	Document document;

	public Genere_modele_dom_variable(Document document) {
		//declaration
		listInstruction =  new ArrayList<Instruction>();
		tabId = new HashMap<String, Node>();
		this.document = document;
		//enregistrement par id
		parcoursId((Node) document.getDocumentElement());
		
		programme = new Programme(null);
		Block blockProgramme = new Block(programme);
		programme.setBlock(blockProgramme);
		//lecture des instruction
		LectureInstruction(blockProgramme,(Node) document.getDocumentElement());
	}

	public Programme getResult() {
		return programme;
	}
	
	public void parcoursId(Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return;
		//si le node possède un id
		if (element.getAttributes().getNamedItem("id") != null) {
			//ajouter le node à la table
			tabId.put(element.getAttributes().getNamedItem("id").getNodeValue(), element);
		}
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			parcoursId((Node) nl.item(i));
		}
	}
	
	//lecture des instructions
	public void LectureInstruction(Block block,Node element) {
		//retour si le l'element n'est pas un node
		if (element.getNodeType() != Node.ELEMENT_NODE)
			return;
		//si l'element ne possède pas d'id
		if(element.getAttributes().getNamedItem("id")!=null) return;
		
		//GenerationInstruction(parent,element);
		
		Instruction instruction = GenerationInstructionBlock(block,element);
		if(instruction!=null)
			block.addInstruction(instruction);
		
		//parcours de chaques elements du fichier xml
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			LectureInstruction(block,(Node) nl.item(i));
		}
	}
	
	public Expr GenerationExpression(Element parent,Node element) {
		if(element==null)return null;
		//intencier l'élément
		switch (element.getNodeName()) {
			//plus
			case "PlusExp":
				ExpBinaire expPlus = new PlusExp(parent);
				//retrouver le node depuis l'id dans la table
				String p1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				//generer l'élément gauche
				expPlus.setOpg(GenerationExpression(expPlus,tabId.get(p1)));
				//retrouver le node depuis l'id dans la table
				String p2 = element.getAttributes().getNamedItem("opd").getNodeValue();
				//generer lélément droit
				expPlus.setOpd(GenerationExpression(expPlus,tabId.get(p2)));
				return expPlus;
				
			//multiplié
			case "MultExp":
				ExpBinaire expMult = new MultExp(parent);
				//generer l'élément gauche
				String m1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				expMult.setOpg(GenerationExpression(expMult,tabId.get(m1)));
				//generer l'élément droit
				String m2 = element.getAttributes().getNamedItem("opd").getNodeValue();
				expMult.setOpd(GenerationExpression(expMult,tabId.get(m2)));
				return expMult;
			//egalité
			case "EgalExp":
				ExpBinaire boolMult = new EgalExp(parent);
				//generer l'élément gauche
				String b1 = element.getAttributes().getNamedItem("opg").getNodeValue();
				boolMult.setOpg(GenerationExpression(boolMult,tabId.get(b1)));
				//generer l'élément droit
				String b2 = element.getAttributes().getNamedItem("opd").getNodeValue();
				boolMult.setOpd(GenerationExpression(boolMult,tabId.get(b2)));
				return boolMult;
				
			//valeur
			case "IntExp":
				return new IntExp(parent,Integer.valueOf(element.getAttributes().getNamedItem("val").getNodeValue()));
			case "DoubleExp":
				return new DoubleExp(parent,Double.valueOf(element.getAttributes().getNamedItem("val").getNodeValue()));
			case "StringExp":
				return new StringExp(parent,String.valueOf(element.getAttributes().getNamedItem("val").getNodeValue()));
			case "BoolExp":
				return new BoolExp(parent,Boolean.valueOf(element.getAttributes().getNamedItem("val").getNodeValue()));
				
			//referenceVariable
			case "VariableRef":
				String nomVariable = element.getAttributes().getNamedItem("nom").getNodeValue();
				UnresolveSymbol symbole = new UnresolveSymbol(parent,nomVariable);
				return symbole;
				
				//referenceVariable
			
				
			default:
				return null;
			}
	}
	
	public Block GenerationBlock(Element parent,Node element) {
		Block block = new Block(parent);
		NodeList nl = element.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node nodeReference = (Node) nl.item(i);
			if(nodeReference.getNodeName()=="ReferenceElement") {
				String idReference = nodeReference.getAttributes().getNamedItem("ref").getNodeValue();
				Instruction instruction = GenerationInstructionBlock(parent,tabId.get(idReference));
				if(instruction!=null)
					block.addInstruction(instruction);
			}
			
		}
		return block;
		
	}
	
	public Instruction GenerationInstructionBlock(Element parent,Node element) {
		switch (element.getNodeName()) {
		//egal
		case "Affectation":
			
			Affectation affectation = new Affectation(parent);
			
			String nomRef = element.getAttributes().getNamedItem("var").getNodeValue();
			UnresolveSymbol symbole = new UnresolveSymbol(affectation,nomRef);
			affectation.setVariable(symbole);
			
			String idExprAffectation = element.getAttributes().getNamedItem("op").getNodeValue();
			Expr AffectExpression = GenerationExpression(affectation,tabId.get(idExprAffectation));
			affectation.setValeur(AffectExpression);
			return affectation;
		//definition de variable
		case "Definition":
			
			
			//recuperation des caracteristique
			String nomDefinition = element.getAttributes().getNamedItem("nom").getNodeValue();
			String typeDefinition = element.getAttributes().getNamedItem("type").getNodeValue();
			String visibiliteDefinition = element.getAttributes().getNamedItem("visibilite").getNodeValue();
			VariableDef varDef = new VariableDef(parent,nomDefinition, typeDefinition, visibiliteDefinition);
			
			return varDef;
		//Affichage
		case "SortieStandard":
			SortieStandard sortieStandard = new SortieStandard(parent);
			//recuperation des caracteristique
			String idExprSortieSTD = element.getAttributes().getNamedItem("op").getNodeValue();
			Expr SortieSTDExpression = GenerationExpression(sortieStandard,tabId.get(idExprSortieSTD));
			sortieStandard.setValeur(SortieSTDExpression);
			return sortieStandard;
		case "If":
			If instructionIf = new If(parent);
			//recuperation des caracteristique
			String idExprIf = element.getAttributes().getNamedItem("cond").getNodeValue();
			Expr IfExpression = GenerationExpression(instructionIf,tabId.get(idExprIf));
			instructionIf.setExpr(IfExpression);
			
			String idBlockIf = element.getAttributes().getNamedItem("block").getNodeValue();
			Block IfBlock = GenerationBlock(instructionIf,tabId.get(idBlockIf));
			instructionIf.setBlock(IfBlock);
			
			return instructionIf;
		default :
			return null;
		}
	}
}
