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
        grille.startDD();
        setChanged();
        notifyObservers();
    }

    public void stopDD(int c, int r) {
        // TODO
        /*
        if (!chemin.isEmpty() && chemin.get(0).symboleId == chemin.get(chemin.size() - 1).symboleId) {*/
            // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
            System.out.println("stopDD : " + c + "-" + r + " -> " + lastC + "-" + lastR);
            /*System.out.println(chemin);
            for(int i = 1; i < chemin.size() - 1; i++){
                int x = chemin.get(i).x;
                int y = chemin.get(i).y;
                int x_p = chemin.get(i - 1).x;
                int y_p = chemin.get(i - 1).y;
                int x_s = chemin.get(i + 1).x;
                int y_s = chemin.get(i + 1).y;
                int d1, d2;
                d1 = ((x == x_p) ? ((y == y_p - 1) ? 3 : 
                        ((y == y_p + 1) ? 1 : 0)) :  
                        ((y == y_p) ? ((x == x_p - 1) ? 2 : 
                        ((x == x_p + 1) ? 4 : 0)) : 0));
                    d2 = ((x == x_s) ? ((y == y_s - 1) ? 3 : 
                        ((y == y_s + 1) ? 1 : 0)) :  
                        ((y == y_s) ? ((x == x_s - 1) ? 2 : 
                        ((x == x_s + 1) ? 4 : 0)) : 0));
                chemin.get(i).setChemin(d1, d2);
            }
            chemin.clear();*/
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
