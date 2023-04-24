package Joueur;

import java.util.Arrays;
import java.util.ArrayList;

public class Etat{
    public int hash;
    public int joueur;
    public boolean gagnant;
    public ArrayList<Etat> fils;
    int [][] terrain;

    public Etat(int[][] terrain, int joueur){
        this.joueur = joueur;
        this.hash = Arrays.deepHashCode(terrain) + joueur;
        this.terrain = terrain;
        this.fils = new ArrayList<Etat>();
    }
}