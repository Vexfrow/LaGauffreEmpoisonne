
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class demoFenetre implements Runnable {


	//Revoir les attributs
	private Color orange;
	private int ligne;
	private int colonne;
	JButton matrix[][];
	JPanel p;



	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("La gaufre empoisonée");


		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on démarre
		frame.setSize(500, 300);
		frame.setVisible(true);

		//On initialise les différentes parties de l'interface
		initBarMenu(frame);
		initHistorique(frame);
		initGaufre(frame);

	}



	//Mets à jour le terrain selon un tableau de char représentant le niveau
	public void majNiveau(char[][] niveau){
		for(int i = 0; i < ligne; i++){
			for(int j=0; j < colonne; j++){
				if(i == 0 && j ==0){
					;
				}else{
					if(niveau[i][j] == 1){
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

	}


	public static void main(String[] args){
		SwingUtilities.invokeLater(new demoFenetre(5, 5));

	}



	public void initBarMenu(JFrame jframe){
		JMenuBar jmb = new JMenuBar();

		//Menu "Nouvelle Partie"
		JMenu listeNouvellePartie = new JMenu("Nouvelle Partie");
		//Boutons du menu "Nouvelle Partie"
		JMenuItem buttonIAAleatoire = new JMenuItem("IA Aleatoire");
		JMenuItem buttonHumain = new JMenuItem("Humain");
		JMenuItem buttonIAGagnantPerdant = new JMenuItem("IA Coup Gagnant/Perdant");
		JMenuItem buttonIAEtOu = new JMenuItem("IA ET/OU");

		listeNouvellePartie.add(buttonHumain);
		listeNouvellePartie.add(buttonIAAleatoire);
		listeNouvellePartie.add(buttonIAGagnantPerdant);
		listeNouvellePartie.add(buttonIAEtOu);


		//Les autres boutons du menu
		Button buttonChargerP = new Button("Charger partie");
		Button buttonSauvegarderP = new Button("Sauvegarder partie");
		Button buttonAnnulerC = new Button("Annuler Coup");
		Button buttonRestaurerC = new Button("Restaurer Coup");

		buttonChargerP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clique sur le bouton \"Charger Partie\" ");
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

		jmb.add(listeNouvellePartie);
		jmb.add(buttonChargerP);
		jmb.add(buttonSauvegarderP);
		jmb.add(buttonAnnulerC);
		jmb.add(buttonRestaurerC);

		//Rajout du menu au jframe
		jframe.setJMenuBar(jmb);
	}



	public void initHistorique(JFrame jframe) {
		//Box contenant l'historique et l'information sur le tour actuel
		Box menuLateralDroite = new Box(BoxLayout.Y_AXIS);
		menuLateralDroite.setBackground(Color.getHSBColor(204, 100, 81));

		//Information sur le tour actuel
		Label texteJoueur = new Label("C'est au tour du joueur X");
		menuLateralDroite.add(texteJoueur, BorderLayout.NORTH);

		//Historique de la partie
		DefaultListModel<String> model = new DefaultListModel<>();
		model.addElement("Test1");
		model.addElement("Test2");
		model.addElement("Test3");

		JList<String> listeCoups = new JList<>(model);
		listeCoups.setName("Historique des coups");
		listeCoups.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listeCoups.setLayoutOrientation(JList.VERTICAL);
		listeCoups.setVisibleRowCount(-1);
		listeCoups.setBackground(Color.getHSBColor(204, 100, 81));

		menuLateralDroite.add(listeCoups,BorderLayout.CENTER);


		jframe.add(menuLateralDroite, BorderLayout.EAST);
	}



	public void initGaufre(JFrame jframe) {
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

		jframe.add(p);


	}


}
