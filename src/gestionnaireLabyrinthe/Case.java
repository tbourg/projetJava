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
         * etat: 0 = vide 1 = symbole 2 = chemin
         */
        public int etat;
        public int symboleId = -1;
        /**
         * direction: 1 = au-desssus 2 = droite 3 = au-dessous 4 = gauche
         */
        public int d_1 = 0, d_2 = 0;

        public Case(int x, int y) {
            this.x = x;
            this.y = y;
            this.etat = 0;
        }

        public void setSymbole(int id) {
            this.etat = 1;
            this.symboleId = id;
        }

        public boolean estSymbole() {
            return (etat == 1);
        }

        public void setChemin(int d_1, int d_2) {
            this.etat = 2;
            this.d_1 = d_1;
            this.d_2 = d_2;
        }

        public boolean estVide() {
            return (etat == 0);
        }
    }
