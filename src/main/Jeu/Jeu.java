package Jeu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.ArrayList;

public class Jeu {
    public int[][] terrain;
    public int nbligne;
    public int nbcolonne;
    private ArrayList<Coup> coupJoue;
    private ArrayList<Coup> coupAnnule;
	private boolean lose;

    // Construire le jeu via une sauvegarde
    Jeu(String fichier){

        try {
    		
    		//init des arrays
    		coupAnnule = new ArrayList<Coup>();
        	coupJoue = new ArrayList<Coup>();
    		
        	//ouverture fichier
    		FileReader reader = new FileReader(fichier);
    		BufferedReader bufferedReader = new BufferedReader(reader);
    		
    		String line;
    		
    		//recuperer le nombre de ligne
    		line = bufferedReader.readLine();
    		nbligne = Integer.parseInt(line);
    		
    		//recuperer le nombre de collone
    		line = bufferedReader.readLine();
    		nbcolonne = Integer.parseInt(line);
    		
    		//creation terrain
    		terrain = new int[nbligne][nbcolonne];
    		
    		//recuprer tous les coups à jouer
    		while ((line = bufferedReader.readLine()) != null && (!line.equals("b"))) {

    				//split la ligne
    				String[] parts = line.split(" ");
    				
    				//creer un nouveau coup
    				Coup cp = new Coup(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    				
    				//jouer le coup
    				joue(cp);
    	        
    	    }
    		
    		//recuprere les dernieres lignes du fichier
    		while ((line = bufferedReader.readLine()) != null && (!line.equals("b"))) {
				
    			//split les lignes
				String[] parts = line.split(" ");
				//definir un nouveau  coup
				Coup cp = new Coup(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				
				//ajoute le coup à l'arraylist
				coupAnnule.add(cp);
	        
	    }
    		
    		//fermer le fichier
    		reader.close();
    		
		} catch (IOException e) {
			System.out.print("Erreur : " + e);
			
		}
    }

    public Jeu(int nbligne, int nbcolonne){
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
            coupAnnule.add(coupJoue.get(coupJoue.size()-1));
            coupJoue.remove(coupJoue.size()-1);

            while( i < coupJoue.size()){
                jeu.joue(coupJoue.get(i));
                i++;
            }
            this.terrain = jeu.terrain;

        }

        

    }


    public boolean peutAnnuler(){
        return (!(coupJoue.size() < 1));
    }


    public void refaire(){
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
        if (getCase(l,c) != 1) {
            while( l < this.nbligne){
				c= cp.c;
				while(c < this.nbcolonne){
					terrain[l][c] = 1;
					c++;
				}
				l++;
       		}
			coupJoue.add(cp);
			coupAnnule = new ArrayList<Coup>();
        }



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

        try {
    		
			FileWriter w = new FileWriter(name);
			
			w.write(nbligne + "\n");
			w.write(nbcolonne + "\n");
			
			//stocker tous les coups joués
			int tailleList = coupJoue.size();
			
			//stock tous les coups
			for(int i = 0; i< tailleList; i++) {
				w.write(coupJoue.get(i).l + " "+ coupJoue.get(i).c + "\n");
			}
			
			//marque pour indiquer que la suite sont des coups annules
			w.write("b\n");
			
			//stocker tous les coups annules
			int tailleLista = coupAnnule.size();
			for(int i = 0; i< tailleLista; i++) {
				w.write(coupAnnule.get(i).l + " "+ coupAnnule.get(i).c + "\n");
			}
			
			//fermer le fichier
			w.close();
			
		} catch (IOException e) {
			System.out.print("Erreur : " + e);
			
		}

    }

    //pour recuprer le contenu de la case
    public int getCase(int i, int j) {
    	return terrain[i][j];
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

    public void charger(String fichier){
        try {
    		
    		//init des arrays
    		coupAnnule = new ArrayList<Coup>();
        	coupJoue = new ArrayList<Coup>();
    		
        	//ouverture fichier
    		FileReader reader = new FileReader(fichier);
    		BufferedReader bufferedReader = new BufferedReader(reader);
    		
    		String line;
    		
    		//recuperer le nombre de ligne
    		line = bufferedReader.readLine();
    		nbligne = Integer.parseInt(line);
    		
    		//recuperer le nombre de collone
    		line = bufferedReader.readLine();
    		nbcolonne = Integer.parseInt(line);
    		
    		//creation terrain
    		terrain = new int[nbligne][nbcolonne];
    		
    		//recuprer tous les coups à jouer
    		while ((line = bufferedReader.readLine()) != null && (!line.equals("b"))) {

    				//split la ligne
    				String[] parts = line.split(" ");
    				
    				//creer un nouveau coup
    				Coup cp = new Coup(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    				
    				//jouer le coup
    				joue(cp);
    	        
    	    }
    		
    		//recuprere les dernieres lignes du fichier
    		while ((line = bufferedReader.readLine()) != null && (!line.equals("b"))) {
				
    			//split les lignes
				String[] parts = line.split(" ");
				//definir un nouveau  coup
				Coup cp = new Coup(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				
				//ajoute le coup à l'arraylist
				coupAnnule.add(cp);
	        
	    }
    		
    		//fermer le fichier
    		reader.close();
    		
		} catch (IOException e) {
			System.out.print("Erreur : " + e);
			
		}
    }


}