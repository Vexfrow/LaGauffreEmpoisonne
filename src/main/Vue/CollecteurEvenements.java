package Vue;

import Interface.FenetreJeu;

//Interface pour gérer les actions possibles avec la gaufre
public interface CollecteurEvenements {

    //Lorsqu'on clique à un endroit sur la gaufre
    void clicSouris(int l, int c);
    void traiterCommande(String commande, String param);

    void ajouteInterface(FenetreJeu fj);
}

