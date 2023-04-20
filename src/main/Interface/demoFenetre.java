
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class demoFenetre implements Runnable {
	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("La gaufre empoisonée");

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);

		//---------------Code pour le menu situé en haut -------------------
		JMenuBar jmb = new JMenuBar();

		JMenu listeNouvellePartie = new JMenu("Nouvelle Partie");

		JMenuItem buttonIAAleatoire = new JMenuItem("IA Aleatoire");
		JMenuItem buttonHumain = new JMenuItem("Humain");
		JMenuItem buttonIAGagnantPerdant = new JMenuItem("IA Coup Gagnant/Perdant");
		JMenuItem buttonIAEtOu = new JMenuItem("IA ET/OU");

		Button buttonChargerP = new Button("Charger partie");
		Button buttonSauvegarderP = new Button("Sauvegarder partie");
		Button buttonAnnulerC = new Button("Annuler Coup");
		Button buttonRestaurerC = new Button("Restaurer Coup");

		buttonChargerP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clique sur le bouton \"Charquer Partie\" ");
			}
		});

		buttonSauvegarderP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clique sur le bouton \"Sauvegarder Partie\" ");
			}
		});

		buttonAnnulerC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clique sur le bouton \"Annuler Coup\" ");
			}
		});

		buttonRestaurerC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clique sur le bouton \"Restaurer Coup\" ");
			}
		});

		listeNouvellePartie.add(buttonHumain);
		listeNouvellePartie.add(buttonIAAleatoire);
		listeNouvellePartie.add(buttonIAGagnantPerdant);
		listeNouvellePartie.add(buttonIAEtOu);

		jmb.add(listeNouvellePartie);
		jmb.add(buttonChargerP);
		jmb.add(buttonSauvegarderP);
		jmb.add(buttonAnnulerC);
		jmb.add(buttonRestaurerC);

		frame.setJMenuBar(jmb);


		//---------------Fin du code pour le menu -------------------



		Box menuLateralDroite = new Box(BoxLayout.Y_AXIS);

		Label texteJoueur = new Label("C'est au tour du joueur X");
		menuLateralDroite.add(texteJoueur, BorderLayout.PAGE_START);

		JTextArea listeCoup = new JTextArea("Historique des coups :\n");
		listeCoup.setEditable(false);
		JScrollPane historiqueCoup = new JScrollPane(listeCoup);
		menuLateralDroite.add(historiqueCoup);

		listeCoup.setBackground(Color.getHSBColor(204, 100, 81));
		menuLateralDroite.setBackground(Color.getHSBColor(204, 100, 81));
		historiqueCoup.setBackground(Color.getHSBColor(204, 100, 81));

		


		frame.add(menuLateralDroite, BorderLayout.LINE_END);





	}

	public static void main(String[] args) {
		// Swing s'exécute dans un thread séparé. En aucun cas il ne faut accéder directement
		// aux composants graphiques depuis le thread principal. Swing fournit la méthode
		// invokeLater pour demander au thread de Swing d'exécuter la méthode run d'un Runnable.
		SwingUtilities.invokeLater(new demoFenetre());
	}
}
