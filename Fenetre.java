
import javax.swing.*;

// L'interface runnable déclare une méthode run
public class Fenetre implements Runnable {
	public void run() {
		// // Creation d'une fenetre
		JFrame frame = new JFrame("Gaufre");

		// // Ajout de notre composant de dessin dans la fenetre
		AireDeDessin aire = new AireDeDessin();
		frame.add(aire);

		// // // Ecoute des évènements liés à la souris dans l'AireDeDessin
		// // aire.addMouseListener(new EcouteurDeSouris());

		// // // Un clic sur le bouton de fermeture clos l'application
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// // On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);





	}


}
