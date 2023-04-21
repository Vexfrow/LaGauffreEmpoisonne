package Joueur;

import java.util.Arrays;

public class Etat{
    public int hash;
    public boolean joueur;

    public Etat(int[][] terrain, boolean joueur){
        this.joueur = joueur;
        this.hash = Arrays.deepHashCode(terrain);
    }
}