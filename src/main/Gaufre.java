import Controlleur.Controleur;
import Interface.FenetreJeu;

import Jeu.Jeu;

public class Gaufre {
    public static void main(String[] args){
        //On crée un jeu avec les paramètres passé en paramètre
        Jeu j = new Jeu(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        //On crée un controleur à partir de ce jeu
        Controleur c = new Controleur(j);
        //On lance l'affichage de l'interface
        FenetreJeu.demarrer(j,c);
    }
}
