/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import gestionnaireLabyrinthe.Case;
import gestionnaireLabyrinthe.Grille;

/**
 *
 * @author fred
 */
public class Modele extends Observable {

    int lastC, lastR;
    Grille grille;
    List<Case> chemin;

    public Modele() {
        this.grille = new Grille(3, 3);
        chemin = new ArrayList<>();
        grille.createSymbols(0, 0, 1, 2);
        grille.createSymbols(2, 0, 2, 2);
    }

    public void startDD(int c, int r) {
        // TODO
        if (grille.tab[c][r].estSymbole()) {
            System.out.println("startDD : " + c + "-" + r);
            setChanged();
            notifyObservers();
        }
    }

    public void stopDD(int c, int r) {
        // TODO
        if (chemin.get(0).symboleId == chemin.get(chemin.size() - 1).symboleId) {
            // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
            System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
            setChanged();
            notifyObservers();
        }
    }

    public void parcoursDD(int c, int r) {
        // TODO
        Case caseCourante = grille.tab[c][r];
        if (caseCourante.estVide() || (caseCourante.estSymbole() && caseCourante.symboleId == chemin.get(0).symboleId)) {
            lastC = c;
            lastR = r;
            System.out.println("parcoursDD : " + c + "-" + r);
            chemin.add(caseCourante);
        } else {
            chemin.clear();
        }
        setChanged();
        notifyObservers();

    }

}
