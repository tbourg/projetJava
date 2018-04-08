/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaireLabyrinthe;

/**
 *
 * @author p1500925
 */
public class Grille {

    public int i, j;
    public Case[][] tab;
    public int dernierId;
    public Chemin chemin;

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
    }

    public void createSymbols(int x1, int y1, int x2, int y2) {
        tab[x1][y1].setSymbole(dernierId);
        tab[x2][y2].setSymbole(dernierId);
        dernierId++;
    }

    public void startDD() {
        chemin = new Chemin();
    }

    public void parcoursDD(int c, int r) {
        if (tab[c][r].estSymbole()) {
            if (chemin.isEmpty() || tab[c][r].symbole == chemin.get(0).symbole) {
                chemin.add(tab[c][r]);
            } else {
                chemin.destroy();
            }
        } else {
            chemin.add(tab[c][r]);
        }
    }

    public void stopDD() {
        if(!chemin.get(chemin.size()-1).estSymbole()){
            chemin.destroy();
        }
    }

}
