package fr.ul.miage.coco.compilation;


import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import fr.ul.miage.arbre.*;
import fr.ul.miage.arbre.Noeud.Categories;
import fr.ul.miage.tds.*;

public class Main {
	private static final Logger LOG = Logger.getLogger(Tds.class.getName());
	public static String newLigne = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		Exemple e = new Exemple(6);
		//appel pour génération
		System.out.print(generer_programme(e.a, e.t));
	}
	
	public static String generer_programme(Prog a, Tds t) {
		String res = "";
		res += ".include beta.uasm" + newLigne + 
			   ".include intio.uasm" + newLigne + 
			   ".options tty" + newLigne + 
			   "CMOVE(pile, SP)" + newLigne + 
			   "BR(debut)";
		res += generer_data(t);
		res += generer_code(a, t);
		res += newLigne + "debut : " + 
						  "CALL(main)" + newLigne + 
						  "HALT()" + newLigne + 
						  "pile :";
		return res;
	}
	
	public static String generer_data(Tds t) {
		String res = "";
		Set<String> set = t.table.keySet();
		for(String s : set) {
			int init = 0;
			List<Symbole> listSym = t.table.get(s);
			for (Symbole sym : listSym) {
				if (sym.getCat().equals("int") && sym.getScope().equals("global")){
					if (sym.get_valeur() != 0) {
						init = sym.get_valeur();
					}
					res += newLigne + s + " : LONG(" + init + ")"; 
				}
			}
		}
		return res; 
	}
	
	public static String generer_code(Noeud a, Tds t) {
		String res = "";
		for(Noeud  f : a.getFils() ){
			res += newLigne + generer_fonction(f, t);
		}
		return res;
	}

	public static String generer_fonction(Noeud n, Tds t){
		String res ="";
		res += n.getLabel() + " : PUSH(LP)" + 
				   newLigne + "PUSH(BP)" + 
				   newLigne + "MOVE(SP, BP)";
		if (n.getFils() != null) {
			for(Noeud f : n.getFils()) {
				res += newLigne + generer_bloc(f, t);
			}
		}
		res += newLigne + "MOVE(BP, SP)" + 
			   newLigne + "POP(BP)" + 
			   newLigne + "POP(LP)" + 
			   newLigne + "RTN()";
		return res;
	}
	public static String generer_expression(Noeud a, Tds t) {
		String res;
		res = "";
		switch(a.getCat()) {
		/*La racine de l'arbre en paramètre est une constante*/
		case CONST:
			res += "CMOVE("+a.getValeur()+", R0)" + newLigne + 
				   "PUSH(R0)";
			break;
		/*La racine de l'arbre en paramètre est un identifiant*/
		case IDF:
			res += "LD("+a.getValeur()+", R0)" + newLigne + 
				   "PUSH(R0)";
			break;
		/*La racine de l'arbre en paramètre est une opération : ADDITION*/
		case PLUS:
			res += generer_expression(a.getFils().get(0), t) + newLigne;
			res += generer_expression(a.getFils().get(1), t) + newLigne;
			res += "POP(R2)" + newLigne +
				   "POP(R1)" + newLigne +
				   "ADD(R1, R2, R3)" + newLigne +
				   "PUSH(R3)";
			break;
		/*La racine de l'arbre en paramètre est une opération : SOUSTRACTION*/
		case MOINS:
			res += generer_expression(a.getFils().get(0), t) + newLigne;
			res += generer_expression(a.getFils().get(1), t) + newLigne;
			res += "POP(R2)" + newLigne +
				   "POP(R1)" + newLigne +
				   "SUB(R1, R2, R3)" + newLigne +
				   "PUSH(R3)";
			break;
		/*La racine de l'arbre en paramètre est une opération : MULTIPLICATION*/
		case MUL:
			res += generer_expression(a.getFils().get(0), t) + newLigne;
			res += generer_expression(a.getFils().get(1), t) + newLigne;
			res += "POP(R2)" + newLigne +
				   "POP(R1)" + newLigne +
				   "MUL(R1, R2, R3)" + newLigne +
				   "PUSH(R3)";
			break;
		/*La racine de l'arbre en paramètre est une opération : DIVISION*/
		case DIV:
			res += generer_expression(a.getFils().get(0), t) + newLigne;
			res += generer_expression(a.getFils().get(1), t) + newLigne;
			res += "POP(R2)" + newLigne +
				   "POP(R1)" + newLigne +
				   "DIV(R1, R2, R3)" + newLigne +
				   "PUSH(R3)";
			break;
		/*La racine de l'arbre en paramètre est une lecture*/
		case LIRE :
			res += generer_lire(a,t);
			break;
		/*La racine de l'arbre en paramètre ne correspond à aucune catégorie utilisable dans cette méthode*/
		default:
			System.out.println("Erreur l'arbre ne correspond pas à une expression");
			break;
		}
		return res;
	}
	
	public static String generer_affectation(Noeud a, Tds t) {
		String res;
		res = "";
		res += generer_expression(a.getFils().get(1), t);
		res += newLigne + "POP(R0)" + 
			   newLigne + "ST(R0, " + 
			   a.getFils().get(0).getLabel() + 
			   					")";
		return res;
	}

	public static String generer_instruction(Noeud a, Tds t) {
		String res = "";
		switch(a.getCat()){
			case AFF :
				res += generer_affectation(a, t);
				break;
			case ECR :
				res += generer_ecrire(a,t);
				break;
			case LIRE :
				res += generer_lire(a,t);
				break;
			case SI :
				res += generer_si((Si)a,t);
				break;
			case TQ :
				res += generer_tq((TantQue)a,t);
				break;
		default:
			System.out.println("Erreur l'arbre ne correspond pas à une instruction");
			break;
		}
		return res;
	}
	
	public static String generer_ecrire(Noeud a, Tds t) {
		String res;
		res = "";
		res += generer_expression(a.getFils().get(0), t) + newLigne;
		res += "POP(R0)" + newLigne + 
			   "WRINT()";
		return res;
	}
	
	public static String generer_lire(Noeud a, Tds t) {
		String res;
		res = "";
		res += "RDINT()" + newLigne +
			   "PUSH(R0)";
		return res;
	}
	
	public static String generer_si(Si a, Tds t) {
		String res = "";
		/*Bloc SI*/
		res += generer_expression_boolean(a.getCondition(), t) + newLigne;
		if (a.getCondition().getCat() == Categories.DIF) {
			res += "POP(R0)" + newLigne +
				   "BT(R0, sinon" + a.getValeur() + ")" + newLigne;
		}
		else {
			res += "POP(R0)" + newLigne +
				   "BF(R0, sinon" + a.getValeur() + ")" + newLigne;
		}
		/*Bloc ALORS*/
		for(Noeud f : a.getBlocAlors().getFils()) {
			res += generer_bloc(f, t) + newLigne;
		}
		//res += generer_bloc(a.getBlocAlors(), t) + newLigne;
		res += "BR(fsi" + a.getValeur() + ")" + newLigne +
			   "sinon" + a.getValeur() + " : ";
		/*Bloc SINON*/
		for(Noeud f : a.getBlocSinon().getFils()) {
			res += generer_bloc(f, t) + newLigne;
		}
		//res += generer_bloc(a.getBlocSinon(), t) + newLigne;
		res += "fsi" + a.getValeur() + " : ";
		return res;
	}
	
	public static String generer_tq(TantQue a, Tds t) {
		String res = "";
		res += "TQ" + a.getValeur() + " : ";
		/*Bloc TQ*/
		res += generer_expression_boolean(a.getCondition(), t) + newLigne;
		if (a.getCondition().getCat() == Categories.DIF) {
			res += "POP(R0)" + newLigne +
				   "BT(R0, TQF" + a.getValeur() + ")" + newLigne;
		}
		else {
			res += "POP(R0)" + newLigne +
				   "BF(R0, TQF" + a.getValeur() + ")" + newLigne;
		}
		/*FAIRE*/
		for(Noeud f : a.getBlocFaire().getFils()) {
			res += generer_bloc(f, t) + newLigne;
		}
		res += "BR(TQ" + a.getValeur() + ")" + newLigne +
			   "TQF" + a.getValeur() + " : ";
		return res;
	}
	
	public static String generer_bloc(Noeud a, Tds t) {
		String res = "";
		res += generer_instruction(a, t);
		return res;
	}
	
	public static String generer_expression_boolean(Noeud a, Tds t) {
		String res = "";
		switch(a.getCat()) {
				/* > */
			case SUP :
				res += generer_expression(a.getFils().get(0),t) + newLigne;
				res += generer_expression(a.getFils().get(1),t) + newLigne;
				res += "POP(R2)" + newLigne + 
	   				   "POP(R1)" + newLigne + 
					   "CMPLT(R2, R1, R3)" + newLigne + 
					   "PUSH(R3)";
				break;
				/* >= */	
			case SUPE :
				res += generer_expression(a.getFils().get(0),t) + newLigne;
				res += generer_expression(a.getFils().get(1),t) + newLigne;
				res += "POP(R2)" + newLigne + 
					   "POP(R1)" + newLigne + 
					   "CMPLE(R2, R1, R3)" + newLigne + 
					   "PUSH(R3)";
				break;
				/* < */
			case INF :
				res += generer_expression(a.getFils().get(0),t) + newLigne;
				res += generer_expression(a.getFils().get(1),t) + newLigne;
				res += "POP(R2)" + newLigne + 
					   "POP(R1)" + newLigne + 
					   "CMPLT(R1, R2, R3)" + newLigne + 
					   "PUSH(R3)";
				break;
				/* <= */
			case INFE :
				res += generer_expression(a.getFils().get(0),t) + newLigne;
				res += generer_expression(a.getFils().get(1),t) + newLigne;
				res += "POP(R2)" + newLigne + 
					   "POP(R1)" + newLigne + 
					   "CMPLE(R1, R2, R3)" + newLigne + 
					   "PUSH(R3)";
				break;
				/* == */
			case EG :
				res += generer_expression(a.getFils().get(0),t) + newLigne;
				res += generer_expression(a.getFils().get(1),t) + newLigne;
				res += "POP(R2)" + newLigne + 
					   "POP(R1)" + newLigne + 
					   "CMPEQ(R1, R2, R3)" + newLigne + 
					   "PUSH(R3)";
				break;
				/* != */
			case DIF :
				res += generer_expression(a.getFils().get(0),t) + newLigne;
				res += generer_expression(a.getFils().get(1),t) + newLigne;
				res += "POP(R2)" + newLigne + 
					   "POP(R1)" + newLigne + 
					   "CMPEQ(R1, R2, R3)" + newLigne + 
					   "PUSH(R3)";
				break;
				/*La racine de l'arbre en paramètre ne correspond à aucun boolean utilisable dans cette méthode*/	
			default:
				System.out.println("Erreur l'arbre ne correspond pas à un boolean");
				break;
			}
		return res;
	}
}
