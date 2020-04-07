package fr.ul.miage.coco.compilation;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import fr.ul.miage.arbre.*;
import fr.ul.miage.arbre.Noeud.Categories;
import fr.ul.miage.tds.*;
/**
 * Classe que correspond à l'API GENERATION
 * @author Gabriel Couroux, Colleen Callonego, Chloé Soquet, Jérémy Picard
 */
public class Main {
	private static final Logger LOG = Logger.getLogger(Tds.class.getName());
	public static String newLigne = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		Exemple e = new Exemple(8);
		//appel pour génération
		System.out.print(generer_programme(e.a, e.t));
	}
	/**
	 * Génère le code assembleur d'un noeud PROG
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
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
	/**
	 * Génère le code assembleur des datas d'un programme
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_data(Tds t) {
		String res = "";
		Set<String> set = t.table.keySet();
		for(String s : set) {
			int init = 0;
			List<Symbole> listSym = t.table.get(s);
			for (Symbole sym : listSym) {
				if (sym.getType().equals("int") && sym.getScope().equals("global") && !sym.getCat().equals("fonction")){
					if (sym.get_valeur() != 0) {
						init = sym.get_valeur();
					}
					res += newLigne + s + " : LONG(" + init + ")"; 
				}
			}
		}
		return res; 
	}
	/**
	 * Génère le code assembleur des codes d'un programme
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_code(Noeud a, Tds t) {
		String res = "";
		for(Noeud  f : a.getFils() ){
			res += newLigne + generer_fonction(f, t);
		}
		return res;
	}
	/**
	 * Génère le code assembleur d'un noeud FONCT
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_fonction(Noeud a, Tds t){
		String res ="";
		res += a.getLabel() + " : PUSH(LP)" + 
				   newLigne + "PUSH(BP)" + 
				   newLigne + "MOVE(SP, BP)";
		int nb_locales = t.rechercher(a.getLabel(), "global").get_nbloc();
		if (nb_locales != 0) {
			res += newLigne + "ALLOCATE(" + nb_locales + ")";
		}
		if (a.getFils() != null) {
			for(Noeud f : a.getFils()) {
				res += newLigne + generer_bloc(f, t);
			}
		}
		if (a.getLabel().equals("main")){
			res += newLigne + "MOVE(BP, SP)" + 
				   newLigne + "POP(BP)" + 
				   newLigne + "POP(LP)" + 
				   newLigne + "RTN()";
		}
		else {
			res += newLigne + "MOVE(BP, SP)" + 
				   newLigne + "POP(BP)" + 
				   newLigne + "POP(LP)" + 
				   newLigne + "RTN()";
		}
		return res;
	}
	/**
	 * Calcule le nombre de variable locales pour une fonction donnée du programme
	 * @param Le tableau de données
	 * @param nom de la fonction
	 * @return nombre de variables locales de la fonction
	 */
	public static int nb_locales(Tds t, String nom_fonct) {
		int nb = 0;
		Set<String> set = t.table.keySet();
		for(String s : set) {
			List<Symbole> listSym = t.table.get(s);
			for (Symbole sym : listSym) {
				if (sym.getCat().equals("local") && sym.getScope().equals(nom_fonct)){
					nb ++;
				}
			}
		}
		return nb;
	}
	/**
	 * Calcule le nombre de paramètre pour une fonction donnée du programme
	 * @param Le tableau de données
	 * @param nom de la fonction
	 * @return nombre de paramètres de la fonction
	 */
	public static int nb_param(Tds t, String nom_fonct) {
		int nb = 0;
		Set<String> set = t.table.keySet();
		for(String s : set) {
			List<Symbole> listSym = t.table.get(s);
			for (Symbole sym : listSym) {
				if (sym.getCat().equals("param") && sym.getScope().equals(nom_fonct)){
					nb ++;
				}
			}
		}
		return nb;
	}
	/**
	 * Renvoie l'adresse dans la pile d'une variable locale
	 * @param rang de la variable locale dans la pile
	 * @return l'adresse de la variable locale
	 */
	public static int adresse_locale(int rang) {
		return rang * 4;
	}
	/**
	 * Renvoie l'adresse dans la pile d'un paramètre
	 * @param rang du paramètre dans la pile
	 * @return l'adresse du paramètre
	 */
	public static int adresse_parametre(int rang) {
		return -(rang+3) * 4;
	}
	/**
	 * Génère le code assembleur des noeuds correspondant à des expressions : CONST, IDF, PLUS, MOINS, MUL, DIV, LIRE, APPEL
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
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
			if (a.getScope().equals("global")) {
				res += "LD("+a.getValeur()+", R0)" + newLigne + 
					   "PUSH(R0)";
			}
			else {
				Symbole s = t.rechercher(a.getLabel(), a.getScope());
				if (s.getCat().equals("param")){
					res += "GETFRAME(" + adresse_parametre(s.get_rang()) + ", R0)" + newLigne + 
							   "PUSH(R0)";
				}
				else {
					res += "GETFRAME(" + adresse_locale(s.get_rang()) + ", R0)" + newLigne + 
							   "PUSH(R0)";
				}
			}
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
		/*La racine de l'arbre en paramètre est un appel*/
		case  APPEL:
			res += generer_appel((Appel)a,t);
			break;
		/*La racine de l'arbre en paramètre ne correspond à aucune catégorie utilisable dans cette méthode*/
		default:
			System.out.println("Erreur l'arbre ne correspond pas à une expression");
			break;
		}
		return res;
	}
	/**
	 * Génère le code assembleur d'un noeud AFF
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_affectation(Noeud a, Tds t) {
		String res;
		res = "";
		res += generer_expression(a.getFils().get(1), t);
		Symbole s = t.rechercher(a.getFils().get(0).getLabel(), "global");
		if (s == null) {
			Symbole s1 = t.rechercher(a.getFils().get(0).getLabel(), a.getFils().get(0).getScope());
			res += newLigne + "POP(R0)" +
				   newLigne + "PUTFRAME(R0," + adresse_locale(s1.get_rang()) + ")";
		}
		else {
			res += newLigne + "POP(R0)" + 
				   newLigne + "ST(R0, " + 
				   a.getFils().get(0).getLabel() + 
					   					")";
		}
		return res;
	}
	/**
	 * Génère le code assembleur des noeuds correspondant à des instructions : AFF, ECR, LIRE SI, TQ, APPEL, RET
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
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
			case  APPEL:
				res += generer_appel((Appel)a,t);
				break;
			case  RET:
				res += generer_retour((Retour)a,t);
				break;
		default:
			System.out.println("Erreur l'arbre ne correspond pas à une instruction");
			break;
		}
		return res;
	}
	/**
	 * Génère le code assembleur d'un noeud ECR
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_ecrire(Noeud a, Tds t) {
		String res;
		res = "";
		res += generer_expression(a.getFils().get(0), t) + newLigne;
		res += "POP(R0)" + newLigne + 
			   "WRINT()";
		return res;
	}
	/**
	 * Génère le code assembleur d'un noeud LIRE
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_lire(Noeud a, Tds t) {
		String res;
		res = "";
		res += "RDINT()" + newLigne +
			   "PUSH(R0)";
		return res;
	}
	/**
	 * Génère le code assembleur d'un noeud SI
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
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
	/**
	 * Génère le code assembleur d'un noeud TANTQUE
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
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
	/**
	 * Génère le code assembleur d'un noeud BLOC
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_bloc(Noeud a, Tds t) {
		String res = "";
		res += generer_instruction(a, t);
		return res;
	}
	/**
	 * Génère le code assembleur des noeuds correspondant à des expressions boolean: SUP, SUPE, INF, INFE, EG, DIF
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
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
	/**
	 * Génère le code assembleur d'un noeud APPEL
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_appel(Appel a, Tds t) {
		String res = "";
		if(t.rechercher((String)a.getValeur(), "globale").getType().equals("int")) {
			res += "ALLOCATE(1)" + newLigne;
		}
		if (!a.getFils().isEmpty()) {
			for(int i = a.getFils().size()-1; i >= 0; i--) {
				res += generer_expression(a.getFils().get(i), t) + newLigne;
				res += "POP(R0)" + newLigne + 
					   "PUSH(R0)" + newLigne;
			}
		}
		res += "CALL(" + a.getLabel() + ")" + newLigne + 
			   "DEALLOCATE(" + nb_param(t, a.getLabel()) + ")" + newLigne;
		res += "POP(R0)" + newLigne + 
			   "PUSH(R0)";
		return res;
	}
	/**
	 * Génère le code assembleur d'un noeud RETOUR
	 * @param L'arbre
	 * @param Le tableau de données
	 * @return Le code assembleur
	 */
	public static String generer_retour(Retour a, Tds t) {
		String res = "";
		res += generer_expression(a.getFils().get(0), t) + newLigne;
		res += "POP(R0)" + newLigne + 
			   "PUTFRAME(R0, " + adresse_parametre(nb_param(t, a.getLabel())) + ")";
		return res;
	}
}