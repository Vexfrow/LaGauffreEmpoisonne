package Joueur;

import java.util.Arrays;
import Joueur.Etat;
import Jeu.Coup;
import Jeu.Jeu;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;



class IAArbre extends Joueur {


IAArbre(Jeu j){
    super(j);

}

    public int hash(int [][]terrain, int joueur){
        int hash = Arrays.deepHashCode(terrain);
        hash += joueur;
        return hash;
    }

    public int[][] cloneTerrain(int[][]terrain){
        int nbcolonne = terrain[0].length;
        int nbligne = terrain.length;
        int cases[][] = new int[nbligne][nbcolonne];
        for(int i =0; i< nbligne; i++){
            for(int j=0; j<nbcolonne; j++){
                cases[i][j] = terrain[i][j];
            }
        }

        return cases;
    }


    @Override
    void elaboreCoup(){
        int [][]cases = cloneTerrain(j.terrain);

        Hashtable<Integer, Etat> hashTable= new Hashtable<Integer, Etat>();
        Queue<Etat> file = new LinkedList<Etat>();
        Etat etat = new Etat(cases,j.joueurCourant);




    }



}
