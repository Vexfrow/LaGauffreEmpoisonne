import javax.swing.SwingUtilities;

import Controlleur.Controleur;
import Interface.demoFenetre;

import Jeu.Jeu;

public class Gaufre {
    public static void main(String args[]){
        Jeu j = new Jeu(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        Controleur c = new Controleur(j, Controleur.PVE);
        //System.out.println(j);

        demoFenetre window = new demoFenetre(j, c);
        c.ajouteNiv(window);
        //System.out.println(j);
        window.majNiveau(j.terrain);
        
    }
}
