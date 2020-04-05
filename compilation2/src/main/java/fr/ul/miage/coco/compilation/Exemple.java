package fr.ul.miage.coco.compilation;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ul.miage.arbre.Affectation;
import fr.ul.miage.arbre.Bloc;
import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Ecrire;
import fr.ul.miage.arbre.Egal;
import fr.ul.miage.arbre.Fonction;
import fr.ul.miage.arbre.Idf;
import fr.ul.miage.arbre.Lire;
import fr.ul.miage.arbre.Multiplication;
import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.NoeudObj;
import fr.ul.miage.arbre.Plus;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.Si;
import fr.ul.miage.arbre.Superieur;
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
				
				Fonction n1 = new Fonction("main");
				
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
				
				Fonction n2 = new Fonction("main");
				
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
				
				Fonction n3_1 = new Fonction("main");
				
				Affectation n3_2 = new Affectation();
				
				Idf n3_3 = new Idf("k");
				
				n3_2.ajouterUnFils(n3_3);
				
				Const n3_4 = new Const(2);
				
				n3_2.ajouterUnFils(n3_4);
				
				n3_1.ajouterUnFils(n3_2);
				
				Affectation n3_5 = new Affectation();
				
				Idf n3_6 = new Idf("l");
				
				n3_5.ajouterUnFils(n3_6);
				
				Plus n3_7 = new Plus();
				
				Idf n3_8 = new Idf("i");
				
				n3_7.ajouterUnFils(n3_8);
				
				Multiplication n3_9 = new Multiplication();
				
				Const n3_10 = new Const(3);
				
				n3_9.ajouterUnFils(n3_10);
				
				Idf n3_11 = new Idf("j");
				
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
				
				Fonction n4_1 = new Fonction("main");
				
				Affectation n4_2 = new Affectation();
				
				Idf n4_3 = new Idf("i");
				
				n4_2.ajouterUnFils(n4_3);
				
				Lire n4_4 = new Lire();
				
				n4_2.ajouterUnFils(n4_4);
				
				n4_1.ajouterUnFils(n4_2);
				
				Ecrire n4_5 = new Ecrire();
				
				Plus n4_6 = new Plus();
				
				Idf n4_7 = new Idf("i");
				
				n4_6.ajouterUnFils(n4_7);
				
				Idf n4_8 = new Idf("j");
				
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
				
				Fonction n5_1 = new Fonction("main");
				
				Affectation n5_2 = new Affectation();
				
				Idf n5_3 = new Idf("k");
				
				n5_2.ajouterUnFils(n5_3);
				
				Lire n5_4 = new Lire();
				
				n5_2.ajouterUnFils(n5_4);
				
				n5_1.ajouterUnFils(n5_2);
				
				Si n5_5 = new Si(1);
				
				Superieur n5_6 = new Superieur();
				
				Idf n5_7 = new Idf("i");

				n5_6.ajouterUnFils(n5_7);
				
				Const n5_8 = new Const(10);
				
				n5_6.ajouterUnFils(n5_8);
				
				n5_5.ajouterUnFils(n5_6);
				
				Bloc n5_9 = new Bloc();
				
				Ecrire n5_10 = new Ecrire();
				
				Const n5_11 = new Const(1);
				
				n5_10.ajouterUnFils(n5_11);
				
				n5_9.ajouterUnFils(n5_10);
				
				n5_5.ajouterUnFils(n5_9);
				
				Bloc n5_12 = new Bloc();
				
				Ecrire n5_13 = new Ecrire();
				
				Const n5_14 = new Const(2);
				
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
