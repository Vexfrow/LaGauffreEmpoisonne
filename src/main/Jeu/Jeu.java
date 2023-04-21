

import java.util.ArrayList;

public class Jeu {
    public char[][] terrain;
    private int nbligne;
    private int nbcolonne;
    private ArrayList<Coup> coupJoue;
    private ArrayList<Coup> coupAnnule;

    // Construire le jeu via une sauvegarde
    Jeu(String fichier){

    }

    Jeu(int nbligne, int nbcolonne){
        terrain = new char[nbligne][nbcolonne];
        this.nbligne = nbligne;
        this.nbcolonne = nbcolonne;

    }

    public void annule(){

    }


    public boolean peutannuler(){
        return true;

    }
    public void refaire(){

    }
    public boolean peutrefaire(){
        return true;

    }
    public void joue(Coup cp){
        int l = cp.l;
        int c = cp.c;
        while( l < this.nbligne){
            c= cp.c;
            while(c < this.nbcolonne){
                terrain[l][c] = 1;
                c++;
            }
            l++;
        }


    }


    // name du fichier*
    public void sauvegarde(String name){


    } 



}