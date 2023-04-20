import javax.swing.SwingUtilities;

public class Gaufre{
    public static void main(String args[]){
        demoFenetre window = new demoFenetre(5, 5);

        SwingUtilities.invokeLater(window);
        char niv[][] = new char [5][5];
        for(int i =0; i < 5; i ++){
            for(int j =0; j < 5; j++){
                niv[i][j] = 0;
            }
        }
        niv[2][3] =1;
        niv[2][4] = 1;
        System.out.println("Maj 2, 3");
        window.majNiveau(niv);
    }
}