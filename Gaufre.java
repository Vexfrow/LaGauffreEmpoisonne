import javax.swing.SwingUtilities;

import Jeu.Jeu;

public class Gaufre {
    public static void main(String args[]){
        Jeu j = new Jeu(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        demoFenetre window = new demoFenetre(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        SwingUtilities.invokeLater(window);
    }
}
