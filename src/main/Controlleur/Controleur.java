package Controlleur;

import Jeu.Coup;
import Jeu.Jeu;
import Joueur.Human;
import Joueur.IAArbre;
import Joueur.IArandom;
import Joueur.Joueur;
import Interface.FenetreJeu;
import Vue.CollecteurEvenements;
import javax.swing.JFrame;



public class Controleur implements CollecteurEvenements {
    private FenetreJeu window;
    public Jeu j;
    private int type;
    public static final int PVP = 1;
    public static final int PVE = 2;
    public static final int EVE = 3;
    public static final int PVR = 4;
    private static final String DIRECTORY = "./rsc/sauvegarde/";
    private boolean lose; //Met fin à la partie
    private Joueur p1;
    private Joueur p2;


    public Controleur(Jeu j){
        this.j = j;
        choseType(PVP);
    }

    public Controleur(Jeu j, int type){
        this(j);
        choseType(type);
        
    }

    public void choseType(int t){
        lose = false;
        this.type = t;
        switch(t){
            case PVP:

                p1 = new Human(this);
                p2 = new Human(this);
                p1.setName("P1");
                p2.setName("P2");
                break;
            case PVE:
                p1 = new Human(this);
                p2 = new IArandom(this);
                p1.setName("P1");
                p2.setName("P2");
                break;
            case EVE:
                p1 = new IArandom(this);
                p2 = new IArandom(this);
                p1.setName("P1");
                p2.setName("P2");
                break;
            case PVR:
                p1 = new Human(this);
                p2 = new IAArbre(this);
                p1.setName("P1");
                p2.setName("P2");

        }
        System.out.println("Sortie creation");
        gameplay();


    }

    public int getType(){
        return type;
    }

    public void ajouteInterface(FenetreJeu d){
        this.window = d;
    }

    public void joue(Coup c){

        if(c.l ==0 && c.c == 0){
            lose = true;
            this.j.joue(c);
            this.window.maj();
            System.out.println("Perdu");
        }else{
            this.j.joue(c);
            this.window.maj();
        }
        gameplay();

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

    public void newJeu(int type, int x, int y){
        this.type = type;
        this.lose = false;
        this.j = new Jeu(x, y);
        choseType(type);
        gameplay();

    }

    public Jeu getJeu(){
        return this.j;
    }

    public void gameplay(){
        if(!lose){
            if(type == PVE){
                if(j.joueurCourant == 2){
                    //Coup d'une IA
                    System.out.println("A l'IA de jouer");
                    boolean bool = p2.elaboreCoup();
                    if(!bool){
                        System.out.println("IA perd");
                    }
                }else{
                    //Attendre le coup d'un joueur
                    System.out.println("Wait for it");
                }
            }

        }
    }


}