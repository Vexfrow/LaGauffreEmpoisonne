package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaptateurCommande implements ActionListener {

    CollecteurEvenements collecteurEvenements;
    String commande;
    String param;

    AdaptateurCommande(CollecteurEvenements cEvenements, String commande, String param) {
        collecteurEvenements = cEvenements;
        this.commande = commande;
        this.param = param;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        collecteurEvenements.traiterCommande(commande, param);
    }

}
