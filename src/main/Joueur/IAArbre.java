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


    public int[][] jouer(Coup cp, int [][]terrain ){
        int l = cp.l;
        int c = cp.c;
        while( l < terrain[0].length){
            c= cp.c;
            while(c < terrain.length){
                terrain[l][c] = 1;
                c++;
            }
            l++;
        }
        return terrain;
    }

    @Override
    boolean elaboreCoup(){
        int [][]cases = cloneTerrain(j.terrain);
        int [][]terrainfils;
        int joueurfils;
        Coup cp;

        Hashtable<Integer, Etat> vu= new Hashtable<Integer, Etat>();
        Queue<Etat> file = new LinkedList<Etat>();

        Etat etatdep = new Etat(cases,j.joueurCourant);
        Etat etatcourant;
        Etat etatfils;


        file.add(etatdep);
        vu.put(etatdep.hash,etatdep);


        while ( (etatcourant =file.poll()) != null ){
            if(etatcourant.joueur==1){
                joueurfils=2;
            }else{
                joueurfils=1;
            }

            int l =0;
            int c =0;
            while( l < etatcourant.terrain[0].length){
                while(c < etatcourant.terrain.length){
                    cp = new Coup(l,c);
                    if(cp.estValide(etatcourant.terrain)){ // Si c un fils possible
                        if(!vu.containsKey(etatcourant.hash)){ // S'il  pas est dans la table de hashage
                            terrainfils =cloneTerrain(etatcourant.terrain);
                            terrainfils=jouer(cp,terrainfils);
                            etatfils = new Etat(terrainfils,joueurfils);
                            etatcourant.fils.add(etatfils);
                            file.add(etatfils);  
                            vu.put(etatfils.hash, etatdep);
                        }

                    }
                    c++;
                }
                l++;
            }

        }
    return true;
    }



}
