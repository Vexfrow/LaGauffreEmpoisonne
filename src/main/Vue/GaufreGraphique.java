package Vue;

import Jeu.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GaufreGraphique extends JComponent{
    Image gaufre, gaufrePoison, vide;
    Jeu j;
    int largeurCase;
    int hauteurCase;


    public GaufreGraphique(Jeu jeu) {
        j = jeu;

        gaufre = chargeImage("gaufre");
        gaufrePoison = chargeImage("gaufrePoison");
        vide = chargeImage("sol");

    }

    private Image chargeImage(String nom) {
        InputStream in = null;
        try {
            in = new FileInputStream("rsc/images/" + nom + ".png");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier \"" + nom + "\" introuvable");
        }

        try {
            // Chargement d'une image utilisable dans Swing
            return ImageIO.read(in);
        } catch (Exception e) {
            System.out.println("Chargement du fichier \""+nom + "\" impossible");
        }
        return null;
    }

    private void tracer(Graphics2D g, Image i, int x, int y, int l, int h) {
        g.drawImage(i, x, y, l, h, null);
    }

    public void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;

        int largeur = getSize().width;
        int hauteur = getSize().height;
        int colonnes = j.nbcolonne;
        int lignes = j.nbligne;
        largeurCase = largeur / colonnes;
        hauteurCase = hauteur / lignes;
        // On prend des cases carrées
        largeurCase = Math.min(largeurCase, hauteurCase);
        hauteurCase = largeurCase;



        for (int ligne = 0; ligne < lignes; ligne++)
            for (int colonne = 0; colonne < colonnes; colonne++) {
                int x = colonne * largeurCase;
                int y = ligne * hauteurCase;
                if(ligne == colonne && ligne == 0)
                    tracer(drawable, gaufrePoison, x, y, largeurCase, hauteurCase);
                else if (j.getCase(ligne, colonne) == 0)
                    tracer(drawable, gaufre, x, y, largeurCase, hauteurCase);
                else
                    tracer(drawable, vide, x, y, largeurCase, hauteurCase);

            }
    }


    int hauteurCase() {
        return hauteurCase;
    }

    int largeurCase() {
        return largeurCase;
    }


    public void misAJour(Jeu jeu){
        j = jeu;
        repaint();
    }


}