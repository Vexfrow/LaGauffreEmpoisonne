
package Controlleur;



import Jeu.Coup;
import Jeu.Jeu;
import Joueur.Human;
import Joueur.IArandom;
import Joueur.Joueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Interface.demoFenetre;


public class Controleur{
    private demoFenetre window;
    private Jeu j;
    private int type;
    public static final int PVP = 1;
    public static final int PVE = 2;
    public static final int EVE = 3;
    private static final String DIRECTORY = "./rsc/sauvegarde/";
    public boolean play; //Play =true signifie au premier joueur de jouer
    public boolean played; //indique si un joueur humain a joué
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
        play = true;
        played = false;
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
        }
        gameplay();
        

    }

    public int getType(){
        return type;
    }

    public void ajouteNiv(demoFenetre d){
        this.window = d;
    }

    public void joue(Coup c){
        
		if(c.l ==0 && c.c == 0){
			lose = true;
            this.j.joue(c);
            this.window.majNiveau(j.terrain);
            System.out.println("Perdu");
		}else{
            this.j.joue(c);
            this.window.majNiveau(j.terrain);
            switchPlayer();
		}
        gameplay();
        

        
        this.j.joue(c);
        this.window.majNiveau(j.terrain);
        this.window.majHistorique(j.getCoupJoue());
        System.out.println(j);

    }

    public void switchPlayer(){
        play = !play;
    }

    public void annule(){
        this.j.annule();
        this.window.majNiveau(j.terrain);
    }

    public void rejoue(){
        this.j.refaire();
        this.window.majNiveau(j.terrain);
    }

    public void sauvegarder(String fileName){
        j.sauvegarde(DIRECTORY + fileName);
        System.out.println("Sauvegarde appuyé");
    }

    public void load(String fichier){
        j.charger(fichier);
        this.window.majNiveau(j.terrain);
    }

    public void newGame(JFrame jframe){        
        this.window.newGameMenu(jframe);
    }

    public void newJeu(){
        this.j = new Jeu(this.window.getLigne(), this.window.getColonne());
    }

    public Jeu getJeu(){
		return this.j;
	}

    public void gameplay(){
        if(!lose){
            if(type == PVE){
                if(!play){
                    //Coup d'une IA
                    p2.elaboreCoup();
                }else{
                    //Attendre le coup d'un joueur
                    System.out.println("Wait for it");
                }
            }
            
        }
    }


}