
package Controlleur;



import Jeu.Coup;
import Jeu.Jeu;
import Interface.demoFenetre;


public class Controleur{
    private demoFenetre window;
    private Jeu j;
    private int type;
    private static final int PVP = 1;
    private static final int PVE = 2;
    private static final int EVE = 3;


    public Controleur(Jeu j){
        this.j = j;
        
    }

    public void ajouteNiv(demoFenetre d){
        this.window = d;
    }

    public void joue(Coup c){
        this.j.joue(c);
        this.window.majNiveau(j.terrain);
        System.out.println(j);
    }

    public void generateCoup(int x, int y){
        joue(new Coup(x,y));
    }


}