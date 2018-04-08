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
public class Case {

    public int x, y;
    /**
     * etat: 0 = vide 1 = symbole 2 = lien
     */
    public int etat;
    public Symbole symbole;
    public Lien lien;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.etat = 0;
    }

    public void setSymbole(int id) {
        this.etat = 1;
        this.symbole = Symbole.values()[id];
    }

    public boolean estSymbole() {
        return (etat == 1);
    }

    public void setLien(Lien lien) {
        this.etat = 2;
        this.lien = lien;
    }

    public boolean estVide() {
        return (etat == 0);
    }

    public enum Lien {
        Horizontal("src/Symboles/Horizontal.png"),
        Vertical("src/Symboles/Vertical.png"),
        Angle_sup_gauche("src/Symboles/Angle de haut a gauche.png"),
        Angle_sup_droit("src/Symboles/Angle de haut a droite.png"),
        Angle_inf_gauche("src/Symboles/Angle de bas a gauche.png"),
        Angle_inf_droit("src/Symboles/Angle de bas a droite.png");
        
        String path;

        private Lien(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    public enum Symbole {
        Etoile("src/Symboles/Etoile.png"),
        Spirale("src/Symboles/Spirale.png");

        String path;

        private Symbole(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}
