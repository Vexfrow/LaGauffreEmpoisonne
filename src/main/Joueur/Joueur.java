package Joueur;


import Jeu.Jeu;


public abstract class Joueur {
    Jeu j;


    Joueur(Jeu j){
        this.j = j;
    }


    boolean elaboreCoup(){
        return true;
    }

}