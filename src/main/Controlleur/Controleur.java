package Controlleur;

import Jeu.Coup;
import Jeu.Jeu;
import Joueur.Human;
import Joueur.IArandom;
import Joueur.Joueur;
import Interface.FenetreJeu;
import Vue.CollecteurEvenements;


public class Controleur implements CollecteurEvenements {
    private FenetreJeu window;
    public Jeu j;
    private int type;
    private static final int PVP = 1;
    private static final int PVE = 2;
    private static final int EVE = 3;
    private static final String DIRECTORY = "./rsc/sauvegarde/";

    private Joueur p1;
    private Joueur p2;


    public Controleur(Jeu j){
        this.j = j;
        this.type = Controleur.PVP;
    }

    public void choseType(int t){
        this.type = t;
        switch(this.type){
            case PVP:
                p1 = new Human(j);
                p2 = new Human(j);
                break;
            case PVE:
                p1 = new Human(j);
                p2 = new IArandom(j);
                break;
            case EVE:
                p1 = new IArandom(j);
                p2 = new IArandom(j);
                break;
        }

    }


    public void ajouteInterface(FenetreJeu d){
        this.window = d;
    }

    public void joue(Coup c){
        this.j.joue(c);
        this.window.maj();
    }

    public void generateCoup(int x, int y){
        joue(new Coup(x,y));
    }


    public void rejoue(){
        this.j.refaire();
    }

    public void sauvegarder(String fileName){
        j.sauvegarde(DIRECTORY + fileName);
    }

    public void charger(String fichier){
        j.charger(fichier);
    }


    public void annule(){
        this.j.annule();
    }



    //-----Clique Souris-----
    @Override
    public void clicSouris(int l, int c) {
        Coup coup = new Coup(l,c);
        if(!j.peutJouer(coup)){
            System.out.println("Ne peut pas jouer");
        }else{
            joue(coup);
        }
    }

    //----------------------------


    public void traiterCommande(String commande, String param){
        if(commande.equals("annule")){
            annule();
        }else if(commande.equals("rejoue")){
            rejoue();
        }else if(commande.equals("sauvegarde")){
            sauvegarder(param);
        }else if(commande.equals("charger")){
            charger(param);
        }
        this.window.maj();

    }

}