package Vue;

import Interface.FenetreJeu;
import Jeu.Jeu;

//Interface pour gérer les actions possibles avec la gaufre
public interface CollecteurEvenements {

    //Lorsqu'on clique à un endroit sur la gaufre
    void clicSouris(int l, int c);
    void traiterCommande(String commande, String param);

    void ajouteInterface(FenetreJeu fj);

    void newJeu(int type, int x, int y);

    Jeu getJeu();
}

