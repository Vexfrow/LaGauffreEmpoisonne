
package Controlleur;



import Jeu.Coup;
import Jeu.Jeu;
import Joueur.Human;
import Joueur.Joueur;
import Interface.demoFenetre;


public class Controleur{
    private demoFenetre window;
    private Jeu j;
    private int type;
    private static final int PVP = 1;
    private static final int PVE = 2;
    private static final int EVE = 3;

    private Joueur p1;
    private Joueur p2;


    public Controleur(Jeu j){
        this.j = j;
        this.type = Controleur.PVP;
        p1 = new Human(j);
        p2 = new Human(j);
        
    }

    public void choseType(int t){
        this.type = t;
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

    public void annule(){
        this.j.annule();
        this.window.majNiveau(j.terrain);
    }

    public void rejoue(){
        this.j.refaire();
        this.window.majNiveau(j.terrain);
    }


}