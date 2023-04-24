package Vue;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdaptateurSouris extends MouseAdapter {
    GaufreGraphique gaufre;
    CollecteurEvenements collecteur;

    public AdaptateurSouris(GaufreGraphique gGaufre, CollecteurEvenements cEvenement) {
        gaufre = gGaufre;
        collecteur = cEvenement;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int l = e.getY() / gaufre.hauteurCase();
        int c = e.getX() / gaufre.largeurCase();
        collecteur.clicSouris(l,c);
    }
}
