/*
 * Sokoban - Encore une nouvelle version (à but pédagogique) du célèbre jeu
 * Copyright (C) 2018 Guillaume Huard
 *
 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).
 *
 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.
 *
 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.
 *
 * Contact:
 *          Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// L'interface runnable déclare une méthode run
public class demoFenetre implements Runnable {
	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("La gaufre empoisonée");

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);

		Button b = new Button();
		b.setLabel("Test");


		Box menuLateralDroite = new Box(BoxLayout.X_AXIS);
		Box menuHorinzontalHaute = new Box(BoxLayout.X_AXIS);

		menuLateralDroite.add(b);

		Button b1 = new Button();
		b1.setLabel("Nouvelle partie");
		Button b2 = new Button();
		b2.setLabel("Charger partie");
		Button b3 = new Button();
		b3.setLabel("Sauvegarder partie");
		Button b4 = new Button();
		b4.setLabel("Annuler coup");
		Button b5 = new Button();
		b5.setLabel("Restaurer coup");

		b1.setVisible(true);
		b2.setVisible(true);
		b3.setVisible(true);
		b4.setVisible(true);


		menuHorinzontalHaute.add(b1);
		menuHorinzontalHaute.add(b2);
		menuHorinzontalHaute.add(b3);
		menuHorinzontalHaute.add(b4);

		menuLateralDroite.setVisible(true);
		menuHorinzontalHaute.setVisible(true);

		frame.add(menuLateralDroite, BorderLayout.LINE_END);
		frame.add(menuHorinzontalHaute, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		// Swing s'exécute dans un thread séparé. En aucun cas il ne faut accéder directement
		// aux composants graphiques depuis le thread principal. Swing fournit la méthode
		// invokeLater pour demander au thread de Swing d'exécuter la méthode run d'un Runnable.
		SwingUtilities.invokeLater(new demoFenetre());
	}
}
