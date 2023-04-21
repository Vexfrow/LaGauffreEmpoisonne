package Jeu;

import java.util.Arrays;
import java.util.ArrayList;

public class Jeu {
    public int[][] terrain;
    public int nbligne;
    public int nbcolonne;
    private ArrayList<Coup> coupJoue;
    private ArrayList<Coup> coupAnnule;

    // Construire le jeu via une sauvegarde
    Jeu(String fichier){

    }

Jeu(int nbligne, int nbcolonne){
    terrain = new int[nbligne][nbcolonne];
    this.nbligne = nbligne;
    this.nbcolonne = nbcolonne;
    coupAnnule = new ArrayList<Coup>();
    coupJoue = new ArrayList<Coup>();

    }

    public void annule(){
        if(peutAnnuler()){
            Jeu jeu = new Jeu(nbligne,nbcolonne);
            int i = 0;
            coupAnnule.add(coupJoue.get(coupJoue.size()));
            coupJoue.remove(coupJoue.size());

            while( i < coupJoue.size()){
                jeu.joue(coupJoue.get(i));
            }
            this.terrain = jeu.terrain;

        }

        

    }


    public boolean peutAnnuler(){
        return (!(coupJoue.size() < 1));
    }


    public void Refaire(){
        if(peutRefaire()){
            Coup cp = coupAnnule.get(coupAnnule.size()-1);
            joueAnnuler(coupAnnule.get(coupAnnule.size()-1));
            coupJoue.add(cp);
            coupAnnule.remove(coupAnnule.size()-1);
        }

    }
    public boolean peutRefaire(){
        if (coupAnnule.size() < 1){
            return false;
        }else{
            return true;
        }


    }
    public void joue(Coup cp){
        int l = cp.l;
        int c = cp.c;
        while( l < this.nbligne){
            c= cp.c;
            while(c < this.nbcolonne){
                terrain[l][c] = 1;
                c++;
            }
            l++;
        }
        coupAnnule = new ArrayList<Coup>();


    }


    public void joueAnnuler(Coup cp){
        int l = cp.l;
        int c = cp.c;
        while( l < this.nbligne){
            c= cp.c;
            while(c < this.nbcolonne){
                terrain[l][c] = 1;
                c++;
            }
            l++;
        }
        }


    // nom du fichier*
    public void sauvegarde(String name){


}

	public String toString() {
		String result = "Plateau:\n[";
		String sep = "";
		for (int i=0; i<terrain.length; i++) {
			result += sep + Arrays.toString(terrain[i]);
			sep = "\n ";
		}
		result += 	"]\nEtat:" +
				"\n- peut annuler : " + peutAnnuler() +
				"\n- peut refaire : " + peutRefaire();
		return result;
	}





}