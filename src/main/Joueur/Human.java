package Joueur;

import Controlleur.Controleur;
import Jeu.Jeu;

public class Human extends Joueur {

    Controleur c;
    public Human(Controleur c){
        super(c.getJeu());
        this.c = c;
    }

    public void setName(String s){
        this.name = s;
    }




}
