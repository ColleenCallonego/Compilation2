package fr.ul.miage.coco.compilation;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.NoeudObj;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.Noeud.Categories;
import fr.ul.miage.arbre.Noeud0;
import fr.ul.miage.arbre.NoeudInt;
import fr.ul.miage.tds.Symbole;
import fr.ul.miage.tds.Tds;

//classe pour créer les différents exemples à tester

public class Exemple {
	private static final Logger LOG = Logger.getLogger(Tds.class.getName());
	public Noeud a;
	public Tds t;
	
	public Exemple (int numero) {
		switch(numero){
			case 1 : //Exemple 1
				//Création de l'arbre
				a = new Prog();
				
				NoeudObj n1 = new NoeudObj();
				n1.setCat(Categories.FONCTION);
				n1.setValeur("main");
				
				a.ajouterUnFils(n1);
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.TYPE_VOID, Symbole.CAT_FONCTION);
					s.set_nbparam(0);
					s.set_nbloc(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 2 : //Exemple 2
				//Création de l'arbre
				a = new Prog();
				
				NoeudObj n2 = new NoeudObj();
				n2.setCat(Categories.FONCTION);
				n2.setValeur("main");
				
				a.ajouterUnFils(n2);
				
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.TYPE_VOID, Symbole.CAT_FONCTION);
					s.set_nbparam(0);
					s.set_nbloc(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
					s.set_valeur(10);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
					s.set_valeur(20);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("k", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("l", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 3 : //Exemple 3
				//Création de l'arbre
				a = new Prog();
				
				NoeudObj n3_1 = new NoeudObj();
				n3_1.setCat(Categories.FONCTION);
				n3_1.setValeur("main");
				n3_1.setFils(new ArrayList<>());
				
				Noeud0 n3_2 = new Noeud0();
				n3_2.setCat(Categories.AFF);
				n3_2.setFils(new ArrayList<>());
				
				NoeudObj n3_3 = new NoeudObj();
				n3_3.setCat(Categories.IDF);
				n3_3.setValeur("k");
				n3_3.setFils(new ArrayList<>());
				
				n3_2.ajouterUnFils(n3_3);
				
				NoeudInt n3_4 = new NoeudInt();
				n3_4.setCat(Categories.CONST);
				n3_4.setValeur(2);
				n3_4.setFils(new ArrayList<>());
				
				n3_2.ajouterUnFils(n3_4);
				
				n3_1.ajouterUnFils(n3_2);
				
				Noeud0 n3_5 = new Noeud0();
				n3_5.setCat(Categories.AFF);
				n3_5.setFils(new ArrayList<>());
				
				NoeudObj n3_6 = new NoeudObj();
				n3_6.setCat(Categories.IDF);
				n3_6.setValeur("l");
				n3_6.setFils(new ArrayList<>());
				
				n3_5.ajouterUnFils(n3_6);
				
				Noeud0 n3_7 = new Noeud0();
				n3_7.setCat(Categories.PLUS);
				n3_7.setFils(new ArrayList<>());
				
				NoeudObj n3_8 = new NoeudObj();
				n3_8.setCat(Categories.IDF);
				n3_8.setValeur("i");
				n3_8.setFils(new ArrayList<>());
				
				n3_7.ajouterUnFils(n3_8);
				
				Noeud0 n3_9 = new Noeud0();
				n3_9.setCat(Categories.MUL);
				n3_9.setFils(new ArrayList<>());
				
				NoeudObj n3_10 = new NoeudObj();
				n3_10.setCat(Categories.CONST);
				n3_10.setValeur(3);
				n3_10.setFils(new ArrayList<>());
				
				n3_9.ajouterUnFils(n3_10);
				
				NoeudObj n3_11 = new NoeudObj();
				n3_11.setCat(Categories.IDF);
				n3_11.setValeur("j");
				n3_11.setFils(new ArrayList<>());
				
				n3_9.ajouterUnFils(n3_11);
				
				n3_7.ajouterUnFils(n3_9);
				
				n3_5.ajouterUnFils(n3_7);
				
				n3_1.ajouterUnFils(n3_5);
				
				a.ajouterUnFils(n3_1);
				
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.TYPE_VOID, Symbole.CAT_FONCTION);
					s.set_nbparam(0);
					s.set_nbloc(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
					s.set_valeur(10);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
					s.set_valeur(20);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("k", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("l", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 4 : //Exemple 4
				//Création de l'arbre
				a = new Prog();
				
				NoeudObj n4_1 = new NoeudObj();
				n4_1.setCat(Categories.FONCTION);
				n4_1.setValeur("main");
				n4_1.setFils(new ArrayList<>());
				
				Noeud0 n4_2 = new Noeud0();
				n4_2.setCat(Categories.AFF);
				n4_2.setFils(new ArrayList<>());
				
				NoeudObj n4_3 = new NoeudObj();
				n4_3.setCat(Categories.IDF);
				n4_3.setValeur("i");
				n4_3.setFils(new ArrayList<>());
				
				n4_2.ajouterUnFils(n4_3);
				
				Noeud0 n4_4 = new Noeud0();
				n4_4.setCat(Categories.LIRE);
				n4_4.setFils(new ArrayList<>());
				
				n4_2.ajouterUnFils(n4_4);
				
				n4_1.ajouterUnFils(n4_2);
				
				Noeud0 n4_5 = new Noeud0();
				n4_5.setCat(Categories.ECR);
				n4_5.setFils(new ArrayList<>());
				
				Noeud0 n4_6 = new Noeud0();
				n4_6.setCat(Categories.PLUS);
				n4_6.setFils(new ArrayList<>());
				
				NoeudObj n4_7 = new NoeudObj();
				n4_7.setCat(Categories.IDF);
				n4_7.setValeur("i");
				n4_7.setFils(new ArrayList<>());
				
				n4_6.ajouterUnFils(n4_7);
				
				NoeudObj n4_8 = new NoeudObj();
				n4_8.setCat(Categories.IDF);
				n4_8.setValeur("j");
				n4_8.setFils(new ArrayList<>());
				
				n4_6.ajouterUnFils(n4_8);
				
				n4_5.ajouterUnFils(n4_6);
				
				n4_1.ajouterUnFils(n4_5);
				
				a.ajouterUnFils(n4_1);
				
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.TYPE_VOID, Symbole.CAT_FONCTION);
					s.set_nbparam(0);
					s.set_nbloc(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
					s.set_valeur(20);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 5 : //Exemple 5
				//Création de l'arbre
				a = new Prog();
				
				NoeudObj n5_1 = new NoeudObj();
				n5_1.setCat(Categories.FONCTION);
				n5_1.setValeur("main");
				n5_1.setFils(new ArrayList<>());
				
				Noeud0 n5_2 = new Noeud0(); 
				n5_2.setCat(Categories.AFF);
				n5_2.setFils(new ArrayList<>());
				
				NoeudObj n5_3 = new NoeudObj();
				n5_3.setCat(Categories.IDF);
				n5_3.setValeur("k");
				n5_3.setFils(new ArrayList<>());
				
				n5_2.ajouterUnFils(n5_3);
				
				Noeud0 n5_4 = new Noeud0();
				n5_4.setCat(Categories.LIRE);
				n5_4.setFils(new ArrayList<>());
				
				n5_2.ajouterUnFils(n5_4);
				
				n5_1.ajouterUnFils(n5_2);
				
				NoeudInt n5_5 = new NoeudInt();
				n5_5.setCat(Categories.SI);
				n5_5.setValeur("#1");
				n5_5.setFils(new ArrayList<>());
				
				Noeud0 n5_6 = new Noeud0();
				n5_6.setCat(Categories.SUP);
				n5_6.setFils(new ArrayList<>());
				
				NoeudObj n5_7 = new NoeudObj();
				n5_7.setCat(Categories.IDF);
				n5_7.setValeur("i");
				n5_7.setFils(new ArrayList<>());

				n5_6.ajouterUnFils(n5_7);
				
				NoeudInt n5_8 = new NoeudInt();
				n5_8.setCat(Categories.CONST);
				n5_8.setValeur(10);
				n5_8.setFils(new ArrayList<>());
				
				n5_6.ajouterUnFils(n5_8);
				
				n5_5.ajouterUnFils(n5_6);
				
				Noeud0 n5_9 = new Noeud0();
				n5_9.setCat(Categories.BLOC);
				n5_9.setFils(new ArrayList<>());
				
				Noeud0 n5_10 = new Noeud0();
				n5_10.setCat(Categories.ECR);
				n5_10.setFils(new ArrayList<>());
				
				NoeudInt n5_11 = new NoeudInt();
				n5_11.setCat(Categories.CONST);
				n5_11.setValeur(1);
				n5_11.setFils(new ArrayList<>());
				
				n5_10.ajouterUnFils(n5_11);
				
				n5_9.ajouterUnFils(n5_10);
				
				n5_5.ajouterUnFils(n5_9);
				
				Noeud0 n5_12 = new Noeud0();
				n5_12.setCat(Categories.BLOC);
				n5_12.setFils(new ArrayList<>());
				
				Noeud0 n5_13 = new Noeud0();
				n5_13.setCat(Categories.ECR);
				n5_13.setFils(new ArrayList<>());
				
				NoeudInt n5_14 = new NoeudInt();
				n5_14.setCat(Categories.CONST);
				n5_14.setValeur(2);
				n5_14.setFils(new ArrayList<>());
				
				n5_13.ajouterUnFils(n5_14);
				
				n5_12.ajouterUnFils(n5_13);
				
				n5_5.ajouterUnFils(n5_12);
				
				n5_1.ajouterUnFils(n5_5);
				
				a.ajouterUnFils(n5_1);
				
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.TYPE_VOID, Symbole.CAT_FONCTION);
					s.set_nbparam(0);
					s.set_nbloc(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.TYPE_ENTIER, Symbole.SCOPE_GLOBAL);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 6 : //Exemple 6
				
				break;
			case 7 : //Exemple 7
				
				break;
			case 8 : //Exemple 8
				
				break;
		}
	}
}
