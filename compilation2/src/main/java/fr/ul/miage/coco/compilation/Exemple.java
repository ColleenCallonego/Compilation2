package fr.ul.miage.coco.compilation;

import java.util.ArrayList;
import java.util.logging.Logger;

import fr.ul.miage.arbre.Affectation;
import fr.ul.miage.arbre.Appel;
import fr.ul.miage.arbre.Bloc;
import fr.ul.miage.arbre.Const;
import fr.ul.miage.arbre.Ecrire;
import fr.ul.miage.arbre.Egal;
import fr.ul.miage.arbre.Fonction;
import fr.ul.miage.arbre.Idf;
import fr.ul.miage.arbre.Inferieur;
import fr.ul.miage.arbre.Lire;
import fr.ul.miage.arbre.Multiplication;
import fr.ul.miage.arbre.Noeud;
import fr.ul.miage.arbre.NoeudObj;
import fr.ul.miage.arbre.Plus;
import fr.ul.miage.arbre.Prog;
import fr.ul.miage.arbre.Retour;
import fr.ul.miage.arbre.Si;
import fr.ul.miage.arbre.Superieur;
import fr.ul.miage.arbre.TantQue;
import fr.ul.miage.tds.Symbole;
import fr.ul.miage.tds.Tds;

//classe pour créer les différents exemples à tester

public class Exemple {
	private static final Logger LOG = Logger.getLogger(Tds.class.getName());
	public Prog a;
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
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
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
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(10);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(20);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("k", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("l", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
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
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(10);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(20);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("k", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("l", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
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
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
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
				
				Idf n5_3 = new Idf("i");
				
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
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 6 : //Exemple 6
				//Création de l'arbre
				a = new Prog();
				
				Fonction n6_1 = new Fonction("main");
				
				Affectation n6_2 = new Affectation();
				
				Idf n6_3 = new Idf("i");
				
				n6_2.setFilsGauche(n6_3);
				
				Const n6_4 = new Const(0);
				
				n6_2.setFilsDroit(n6_4);
				
				n6_1.ajouterUnFils(n6_2);
				
				TantQue n6_5 = new TantQue(1);
				
				Inferieur n6_6 = new Inferieur();
				
				Idf n6_7 = new Idf("i");
				
				n6_6.setFilsGauche(n6_7);
				
				Idf n6_8 = new Idf("n");
				
				n6_6.setFilsDroit(n6_8);
				
				n6_5.setCondition(n6_6);
				
				Bloc n6_9 = new Bloc();
				
				Ecrire n6_10 = new Ecrire();
				
				Idf n6_11 = new Idf("i");
				
				n6_10.setLeFils(n6_11);
				
				n6_9.ajouterUnFils(n6_10);
				
				Affectation n6_12 = new Affectation();
				
				Idf n6_13 = new Idf("i");
				
				n6_12.setFilsGauche(n6_13);
				
				Plus n6_14 = new Plus();
				
				Idf n6_15 = new Idf("i");
				
				n6_14.setFilsGauche(n6_15);
				
				Const n6_16 = new Const(1);
				
				n6_14.setFilsGauche(n6_16);
				
				n6_12.setFilsDroit(n6_14);
				
				n6_9.ajouterUnFils(n6_12);
				
				n6_5.setBlocFaire(n6_9);
				
				n6_1.ajouterUnFils(n6_5);
				
				a.ajouterUnFils(n6_1);
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("n", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(5);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 7 : //Exemple 7
				//Création de l'arbre
				a = new Prog();
				
				Fonction n7_1 = new Fonction("f");
				
				Affectation n7_2 = new Affectation();
				
				Idf n7_3 = new Idf("x");
				
				n7_2.setFilsGauche(n7_3);
				
				Const n7_4 = new Const(1);
				
				n7_2.setFilsDroit(n7_4);
				
				n7_1.ajouterUnFils(n7_2);
				
				Affectation n7_5 = new Affectation();
				
				Idf n7_6 = new Idf("y");
				
				n7_5.setFilsGauche(n7_6);
				
				Const n7_7 = new Const(1);
				
				n7_5.setFilsDroit(n7_7);
				
				n7_1.ajouterUnFils(n7_5);
				
				Affectation n7_8 = new Affectation();
				
				Idf n7_9 = new Idf("a");
				
				n7_8.setFilsGauche(n7_9);
				
				Plus n7_10 = new Plus();
				
				Idf n7_11 = new Idf("i");
				
				n7_10.setFilsGauche(n7_11);
				
				Plus n7_12 = new Plus();
				
				Idf n7_13 = new Idf("x");
				
				n7_12.setFilsGauche(n7_13);
				
				Idf n7_14 = new Idf("y");
				
				n7_12.setFilsDroit(n7_14);
				
				n7_10.setFilsDroit(n7_12);
				
				n7_8.setFilsDroit(n7_10);
				
				n7_1.ajouterUnFils(n7_8);
				
				a.ajouterUnFils(n7_1);
				
				Fonction n7_15 = new Fonction("main");
				
				Appel n7_16 = new Appel("f");
				
				Const n7_17 = new Const(3);
				
				n7_16.setFils(n7_17);
				
				n7_15.ajouterUnFils(n7_16);
				
				Ecrire n7_18 = new Ecrire();
				
				Idf n7_19 = new Idf("a");
				
				n7_18.setLeFils(n7_19);
				
				n7_15.ajouterUnFils(n7_18);
				
				a.ajouterUnFils(n7_15);
				
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("a", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(10);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("f", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
					s.set_nbparam(1);
					s.set_nbloc(2);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_PARAMETRE, "f", Symbole.TYPE_ENTIER);
					s.set_rang(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("x", Symbole.CAT_LOCAL, "f", Symbole.TYPE_ENTIER);
					s.set_rang(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("y", Symbole.CAT_LOCAL, "f", Symbole.TYPE_ENTIER);
					s.set_rang(1);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
			case 8 : //Exemple 8
				//Création de l'arbre
				a = new Prog();
				
				Fonction n8_1 = new Fonction("f");
				
				Affectation n8_2 = new Affectation();
				
				Idf n8_3 = new Idf("x");
				
				n8_2.setFilsGauche(n8_3);
				
				Plus n8_4 = new Plus();
				
				Idf n8_5 = new Idf("i");
				
				n8_4.setFilsGauche(n8_5);
				
				Idf n8_6 = new Idf("j");
				
				n8_4.setFilsDroit(n8_6);
				
				n8_2.setFilsDroit(n8_4);
				
				n8_1.ajouterUnFils(n8_2);
				
				Retour n8_7 = new Retour("f");
				
				Plus n8_8 = new Plus();
				
				Idf n8_9 = new Idf("x");
				
				n8_8.setFilsGauche(n8_9);
				
				Const n8_10 = new Const(10);
				
				n8_8.setFilsDroit(n8_10);
				
				n8_7.setLeFils(n8_8);
				
				n8_1.ajouterUnFils(n8_7);
				
				a.ajouterUnFils(n8_1);
				
				Fonction n8_11 = new Fonction("main");
				
				Affectation n8_12 = new Affectation();
			
				Idf n8_13 = new Idf("a");
				
				n8_12.setFilsGauche(n8_13);
				
				Appel n8_14 = new Appel("f");
			
				Const n8_15 = new Const(1);
				
				n8_14.setFils(n8_15);
				
				Const n8_16 = new Const(2);
				
				n8_14.setFils(n8_16);
				
				n8_12.setFilsDroit(n8_14);
				
				n8_11.ajouterUnFils(n8_12);
				
				Ecrire n8_17 = new Ecrire();
				
				Idf n8_18 = new Idf("a");
				
				n8_17.setLeFils(n8_18);
				
				n8_11.ajouterUnFils(n8_17);
				
				a.ajouterUnFils(n8_11);
				
				//Création du tds
				t = new Tds();
				try {
					Symbole s = t.ajouter("main", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_VOID);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("a", Symbole.CAT_GLOBAL, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_valeur(10);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("f", Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL, Symbole.TYPE_ENTIER);
					s.set_nbparam(2);
					s.set_nbloc(1);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("x", Symbole.CAT_LOCAL, "f", Symbole.TYPE_ENTIER);
					s.set_rang(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("i", Symbole.CAT_PARAMETRE, "f", Symbole.TYPE_ENTIER);
					s.set_rang(0);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				try {
					Symbole s = t.ajouter("j", Symbole.CAT_PARAMETRE, "f", Symbole.TYPE_ENTIER);
					s.set_rang(1);
				} catch (Exception e) {
					LOG.warning(e.getMessage());
				}
				break;
		}
	}
}
