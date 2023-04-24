
package Joueur;
import Jeu.Coup;
import java.util.Random;
import Jeu.Jeu;



public class IArandom extends Joueur {
    Random r;

    public IArandom(Jeu j){
        super(j);

}

    @Override
    boolean elaboreCoup(){
        if(!(j.terrain[0][1] ==0 || j.terrain[1][0]== 0)){
            return false;
        }

        int l, c;
        l = r.nextInt(j.nbligne);
        c= r.nextInt(j.nbcolonne-);
        while(!(j.terrain[l][c] == 0 || (l ==0 && c ==0) ) ){
            l = r.nextInt(j.nbligne);
            c= r.nextInt(j.nbcolonne);

        }
        Coup cp = new Coup(l,c);
        j.joue(cp);
        return true;
    }

    






}