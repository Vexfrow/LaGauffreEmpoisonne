package Joueur;


import Jeu.Jeu;


public abstract class Joueur {
    Jeu j;
    public String name;

    Joueur(Jeu j){
        this.j = j;
    }


    public boolean elaboreCoup(){
        return false;
    }

    public void setName(String s){}

}