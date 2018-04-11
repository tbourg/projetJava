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
 * @author tbour_000
 */
public class Chemin {

    List<Case> list;

    /**
     * Constructeur
     */
    public Chemin() {
        list = new ArrayList<>();
    }

    public void add(Case c) {
        list.add(c);
        System.err.println("adde ");
            System.err.println(list.size());
        if (list.size() > 2) {
            Case act = list.get(list.size() - 2), prec = list.get(list.size() - 3);
            int x_s = c.x, y_s = c.y;
            int x = act.x, y = act.y;
            int x_p = prec.x, y_p = prec.y;
            if (x_s == x && x == x_p) {
                act.setLien(Case.Lien.Vertical);
            }
            if (y_p == y && y == y_s) {
                act.setLien(Case.Lien.Horizontal);
            }
            if (y_p == y + 1 && x_p == x && y == y_s) {
                if (x == x_s - 1) {
                    act.setLien(Case.Lien.Angle_inf_droit);//
                }
                if (x == x_s + 1) {
                    act.setLien(Case.Lien.Angle_inf_gauche);
                }
            }
            if (y_p == y - 1 && x_p == x && y == y_s) {
                if (x == x_s - 1) {
                    act.setLien(Case.Lien.Angle_sup_droit);//
                }
                if (x == x_s + 1) {
                    act.setLien(Case.Lien.Angle_sup_gauche);//
                }
            }
            if (y_s == y + 1 && x_s == x && y == y_p) {
                if (x == x_p - 1) {
                    act.setLien(Case.Lien.Angle_inf_droit);//
                }
                if (x == x_p + 1) {
                    act.setLien(Case.Lien.Angle_inf_gauche);//
                }
            }
            if (y_s == y - 1 && x_s == x && y == y_p) {
                if (x == x_p - 1) {
                    act.setLien(Case.Lien.Angle_sup_droit);//
                }
                if (x == x_p + 1) {
                    act.setLien(Case.Lien.Angle_sup_gauche);
                }
            }
        }
    }

    public Case get(int index) {
        return list.get(index);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size(){
        return list.size();
    }
    
    public boolean contains(Case c){
        return list.contains(c);
    }

    public void destroy() {
        System.err.println("destroyed");
        for(int i = 0; i < list.size(); i++){
            Case c = list.get(i);
            if(!c.estSymbole()){
                c.etat = 0;
                c.lien = null;
            }
        }
    }

}
