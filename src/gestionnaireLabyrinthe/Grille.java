/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaireLabyrinthe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author p1500925
 */
public class Grille {

    public int i, j;
    public Case[][] tab;
    public int dernierId;
    public boolean win = false;
    public Chemin chemin;
    private List<Chemin> chemins;
    private int nbChemin = 0;

    public Grille(int i, int j) {
        this.i = i;
        this.j = j;
        tab = new Case[i][j];
        for (int k = 0; k < this.i; k++) {
            for (int l = 0; l < this.j; l++) {
                tab[k][l] = new Case(k, l);
            }
        }
        dernierId = 0;
        chemins = new ArrayList<>(2);
        chemins.add(0, null);
        chemins.add(1, null);
    }

    public void createSymbols(int x1, int y1, int x2, int y2) {
        tab[x1][y1].setSymbole(dernierId);
        tab[x2][y2].setSymbole(dernierId);
        dernierId++;
    }

    public void startDD(int c, int r) {
        chemin = new Chemin();
                if (tab[c][r].estSymbole() && chemins.get(tab[c][r].symbole.ordinal()) != null) {
                    chemins.get(tab[c][r].symbole.ordinal()).destroy();
                    chemins.add(tab[c][r].symbole.ordinal(), null);
                }
    }

    public void parcoursDD(int c, int r) {
        if (chemin != null && !chemin.contains(tab[c][r])) {
            if (tab[c][r].estSymbole()) {
                if (chemin.isEmpty() || tab[c][r].symbole == chemin.get(0).symbole) {
                    chemin.add(tab[c][r]);
                } else {
                    chemin.destroy();
                    chemin = null;
                }
            } else {
                chemin.add(tab[c][r]);
            }
        } else {
            chemin.destroy();
            chemin = null;
        }
    }

    public void stopDD() {
        if (chemin != null) {
            if (!chemin.get(chemin.size() - 1).estSymbole()) {
                chemin.destroy();
            } else {
                if (chemins.get(chemin.get(0).symbole.ordinal()) != null) {
                    chemins.get(chemin.get(0).symbole.ordinal()).destroy();
                    chemins.add(chemin.get(0).symbole.ordinal(), null);
                }
                chemins.add(chemin.get(0).symbole.ordinal(), chemin);
                nbChemin++;
            }
        }
        win = checkVictory();
    }

    public boolean checkVictory() {
        for (Case[] row : tab) {
            for (Case c : row) {
                if (c.estVide()) {
                    return false;
                }
            }
        }
        return true;
    }

}
