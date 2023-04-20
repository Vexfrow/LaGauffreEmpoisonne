
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// L'interface runnable déclare une méthode run
public class demoFenetre implements Runnable {

	private Color orange;
	private int ligne;
	private int colonne;
	JButton matrix[][];
	JFrame frame;
	JPanel p;
	public void run() {
		// Creation d'une fenetre

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
		

		frame.add(menuLateralDroite, BorderLayout.LINE_END);
		frame.add(p);

	}

	public void majNiveau(char[][] niveau){
		for(int i = 0; i < ligne; i++){
			for(int j=0; j < colonne; j++){
				if(i == 0 && j ==0){
					;
				}else{
					if(niveau[i][j] == 1){
						System.out.println("Sortie :"+ i + " " + j);
						matrix[i][j].setBackground(new Color(255, 255, 255));
						matrix[i][j].setEnabled(false);
					}else{
						matrix[i][j].setBackground(orange);
						matrix[i][j].setEnabled(true);
					}
					
				}

			}
		}
	}

	public demoFenetre(int x, int y){
		this.ligne = x;
		this.colonne = y;
		this.orange = new Color(250, 180, 50);
		frame = new JFrame("La gaufre empoisonée");
		p = new JPanel();

		this.matrix = new JButton[this.ligne][this.colonne];
		p.setLayout(new GridLayout(ligne, colonne, 0, 0));
		for(int i = 0; i < ligne; i++){
			for(int j=0; j < colonne; j++){
				JButton b = new JButton();
				if(i == 0 && j ==0){
					b.setBackground(new Color(10, 240, 10));
				}else{
					b.setBackground(orange);
				}
				matrix[i][j] = b;
				p.add(b);

			}
		}
	}

	

}
