/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.ArrayList;
import java.util.Observable;

import gestionnaireLabyrinthe.Grille;

/**
 *
 * @author fred
 */
public class Modele extends Observable {

    int lastC, lastR;
    Grille grille;

    public Modele() {
        this.grille = new Grille(3, 3);
        grille.createSymbols(0, 0, 1, 2);
        grille.createSymbols(2, 0, 2, 2);
    }

    public void startDD(int c, int r) {
        // TODO
        System.out.println("startDD : " + c + "-" + r);
        grille.startDD(c, r);
        setChanged();
        notifyObservers();
    }

    public void stopDD(int c, int r) {
        System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
        grille.stopDD();
        setChanged();
        notifyObservers();
    }

    public void parcoursDD(int c, int r) {
        // TODO
        /*Case caseCourante = grille.tab[c][r];
        if (caseCourante.estVide() || (caseCourante.estSymbole() && (chemin.isEmpty() || caseCourante.symboleId == chemin.get(0).symboleId))) {
            lastC = c;
            lastR = r;*/
        System.out.println("parcoursDD : " + c + "-" + r);
        /*chemin.add(caseCourante);
        } else {
            chemin.clear();
        }*/
        grille.parcoursDD(c, r);
        setChanged();
        notifyObservers();

    }

}
