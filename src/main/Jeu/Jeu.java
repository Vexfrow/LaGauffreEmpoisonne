package Jeu;


import java.util.ArrayList;

public class Jeu {
    public int[][] terrain;
    public int nbligne;
    public int nbcolonne;
    private ArrayList<Coup> coupJoue;
    private ArrayList<Coup> coupAnnule;

    // Construire le jeu via une sauvegarde
    public Jeu(String fichier){

    }

    public Jeu(int nbligne, int nbcolonne){
        terrain = new int[nbligne][nbcolonne];
        this.nbligne = nbligne;
        this.nbcolonne = nbcolonne;

    }

    public void annule(){

    }


    public boolean peutannuler(){
        return true;

    }
    public void refaire(){

    }
    public boolean peutrefaire(){
        return true;

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