
package Interface;

import javax.swing.*;

import Controlleur.Controleur;
import Jeu.Coup;
import Jeu.Jeu;
import Vue.AdaptateurSouris;
import Vue.CollecteurEvenements;
import Vue.GaufreGraphique;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class FenetreJeu implements Runnable {

	//-----UTILS-----
	CollecteurEvenements controleur;
	Jeu jeu;

	//---------------


	//----SWING COMPONENT-----
	JFrame mainFrame;
	JMenuBar barMenu;
	JMenu listeNouvellePartie;
	JMenuItem buttonIAAleatoire;
	JMenuItem buttonHumain;
	JMenuItem buttonIAGagnantPerdant;
	JMenuItem buttonIAEtOu;

	Button buttonChargerP;
	Button buttonSauvegarderP;
	Button buttonAnnulerC;
	Button buttonRestaurerC;

	JPanel panelHistorique;
	JPanel panelGaufre;

	JSplitPane splitPane;
	GaufreGraphique gaufreGraphique;
	JTextArea infoJoueur;

	DefaultListModel<String> model;


	public FenetreJeu(Jeu j, CollecteurEvenements ce){
		barMenu = null;
		panelHistorique = null;
		panelGaufre = null;
		splitPane = null;

		controleur = ce;
		controleur.ajouteInterface(this);
		jeu = j;
		model = null;
	}


	public static void demarrer(Jeu j, Controleur c){
		try{
			SwingUtilities.invokeAndWait(new FenetreJeu(j, c));
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	public void run() {
		// Creation d'une fenêtre
		mainFrame = new JFrame("La gaufre empoisonnée");
		mainFrame.setLayout(new BorderLayout());

		// On fixe la taille et on démarre
		mainFrame.setSize(530, 300);
		mainFrame.setMinimumSize(new Dimension(530, 300));


		//On initialise les différentes parties de l'interface
		initBarMenu();
		initHistorique();
		initGaufre();

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelGaufre, panelHistorique);
		splitPane.setResizeWeight(0.80);
		mainFrame.add(splitPane, BorderLayout.CENTER);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

	}


	//Fonction pour initialiser la bar situé en haut de la fenêtre de jeu
	public void initBarMenu(){
		barMenu = new JMenuBar();

		//Menu "Nouvelle Partie"
		listeNouvellePartie = new JMenu("Nouvelle Partie");
		//Boutons du menu "Nouvelle Partie"
		buttonIAAleatoire = new JMenuItem("IA Aléatoire");
		buttonHumain = new JMenuItem("Humain");
		buttonIAGagnantPerdant = new JMenuItem("IA Coup Gagnant/Perdant");
		buttonIAEtOu = new JMenuItem("IA ET/OU");


		buttonHumain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newGameMenu(Controleur.PVP);
			}
		});

		buttonIAAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newGameMenu(Controleur.PVE);

			}
		});

		listeNouvellePartie.add(buttonHumain);
		listeNouvellePartie.add(buttonIAAleatoire);
		listeNouvellePartie.add(buttonIAGagnantPerdant);
		listeNouvellePartie.add(buttonIAEtOu);


		//Les autres boutons du menu
		buttonChargerP = new Button("Charger partie");
		buttonSauvegarderP = new Button("Sauvegarder partie");
		buttonAnnulerC = new Button("Annuler Coup");
		buttonRestaurerC = new Button("Restaurer Coup");

		buttonChargerP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chargerFichier();
			}
		});

		buttonSauvegarderP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		buttonAnnulerC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controleur.traiterCommande("annule", null);
			}
		});

		buttonRestaurerC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controleur.traiterCommande("rejoue", null);
			}
		});

		barMenu.add(listeNouvellePartie);
		barMenu.add(buttonChargerP);
		barMenu.add(buttonSauvegarderP);
		barMenu.add(buttonAnnulerC);
		barMenu.add(buttonRestaurerC);

		//Rajout du menu au jframe
		mainFrame.setJMenuBar(barMenu);
	}


	//Fonction pour initialiser le menu situé sur la droite de la fenêtre de jeu
	public void initHistorique() {

		SpringLayout layoutMenu = new SpringLayout();

		//Panel contenant l'historique et l'information sur le tour actuel
		panelHistorique = new JPanel(layoutMenu);
		Dimension dim = new Dimension(mainFrame.getWidth()/2, mainFrame.getHeight());
		panelHistorique.setMinimumSize(dim);
		panelHistorique.setPreferredSize(dim);


		//Information sur le tour actuel
		infoJoueur = new JTextArea("C'est au tour du joueur n°"+jeu.joueurCourant);
		infoJoueur.setRows(1);
		infoJoueur.setEditable(false);

		//Separator
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(panelHistorique.getWidth(), 5));
		separator.setBackground(Color.getHSBColor(0, 0, 0));

		//Historique de la partie
		model = new DefaultListModel<>();

		JList<String> listeCoups = new JList<>(model);
		JTextArea titreHistorique = new JTextArea("Historique des coups");
		titreHistorique.setRows(1);
		titreHistorique.setEditable(false);

		listeCoups.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listeCoups.setLayoutOrientation(JList.VERTICAL);
		listeCoups.setVisibleRowCount(-1);


		layoutMenu.putConstraint(SpringLayout.NORTH, infoJoueur, 10, SpringLayout.NORTH, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.WEST, infoJoueur, 10, SpringLayout.WEST, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.EAST, infoJoueur, 10, SpringLayout.EAST, panelHistorique);

		layoutMenu.putConstraint(SpringLayout.NORTH, separator, 10, SpringLayout.SOUTH, infoJoueur);

		layoutMenu.putConstraint(SpringLayout.NORTH, titreHistorique, 10, SpringLayout.SOUTH, separator);
		layoutMenu.putConstraint(SpringLayout.WEST, titreHistorique, 10, SpringLayout.WEST, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.EAST, titreHistorique, 10, SpringLayout.EAST, panelHistorique);

		layoutMenu.putConstraint(SpringLayout.NORTH, listeCoups, 10, SpringLayout.SOUTH, titreHistorique);
		layoutMenu.putConstraint(SpringLayout.WEST, listeCoups, 10, SpringLayout.WEST, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.EAST, listeCoups, 10, SpringLayout.EAST, panelHistorique);

		panelHistorique.add(infoJoueur,SpringLayout.NORTH);
		panelHistorique.add(separator);
		panelHistorique.add(titreHistorique,SpringLayout.NORTH);
		panelHistorique.add(listeCoups,SpringLayout.NORTH);


		panelHistorique.setBorder(BorderFactory.createLineBorder(Color.red));


	}


	//Fonction pour initialiser la gaufre
	public void initGaufre() {
		panelGaufre = new JPanel(new CardLayout());
		gaufreGraphique = new GaufreGraphique(jeu);
		panelGaufre.add(gaufreGraphique);
		panelGaufre.setVisible(true);
		gaufreGraphique.addMouseListener(new AdaptateurSouris(gaufreGraphique, controleur));
	}


	//Fonction pour mettre à jour la fenêtre
	public void maj(){
		panelGaufre.repaint();
		if(jeu.jeuEnCours)
			infoJoueur.setText("C'est au tour du joueur n°"+jeu.joueurCourant);
		else{
			JOptionPane.showMessageDialog(mainFrame, "Victoire du joueur n°"+jeu.joueurCourant);
		}

		buttonAnnulerC.setEnabled(jeu.peutAnnuler());
		buttonRestaurerC.setEnabled(jeu.peutRefaire());
		majHistorique(jeu.getHistorique());


	}

	public void chargerFichier(){

		JFrame jf = new JFrame();

		File repertoireSauvegarde = new File(System.getProperty("user.dir")+"/rsc/sauvegarde/");
		File[] listeFile = repertoireSauvegarde.listFiles();

		JList<File> listeFichiers = new JList<>(listeFile);


		Box boxSud = new Box(BoxLayout.X_AXIS);

		Button boutonOpen = new Button("Open");
		Button boutonClose = new Button("Close");

		boutonOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File fichierChoisi = listeFichiers.getSelectedValue();
				if(fichierChoisi != null){
					System.out.println("Fichier choisi = " + fichierChoisi.getAbsolutePath());
					controleur.traiterCommande("charger", fichierChoisi.getAbsolutePath());
					jf.dispatchEvent(new WindowEvent(jf, WindowEvent.WINDOW_CLOSING));
					//Appel à la fonction "modifier jeu" avec le fichier choisi
				}
			}
		});

		boutonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispatchEvent(new WindowEvent(jf, WindowEvent.WINDOW_CLOSING));
			}
		});

		boxSud.add(boutonOpen);
		boxSud.add(boutonClose);


		jf.add(boxSud, BorderLayout.SOUTH);
		jf.add(listeFichiers, BorderLayout.CENTER);


		// On fixe la taille et on démarre
		jf.setSize(500, 300);
		jf.setVisible(true);


	}


	public void save(){
		JFrame jf = new JFrame();
		JTextField text = new JTextField(1);
		Box b = new Box(BoxLayout.X_AXIS);

		//TODO : refaire cette partie
		text.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e){}

			public void keyReleased(KeyEvent e){}

			@Override
			public void keyPressed(KeyEvent e){
				char ch = e.getKeyChar();
				if(ch == '\n'){
					controleur.traiterCommande("sauvegarde",text.getText());
					jf.dispose();
				}
			}
		});
		b.add(text);
		jf.add(b);
		jf.setSize(100,100);
		jf.setVisible(true);
	}


	public void majHistorique(ArrayList<Coup> c){
		model.removeAllElements();
		for(int i = 0; i < c.size() ; i++){
			if(i%2 == 0)
				model.addElement("Joueur n°1 à joué : " + c.get(i).toString());
			else
				model.addElement("Joueur n°2 à joué : " + c.get(i).toString());
		}

	}


	public void newGame(){

		mainFrame.remove(panelGaufre);
		mainFrame.remove(panelHistorique);
		initGaufre();
		initHistorique();

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelGaufre, panelHistorique);
		splitPane.setResizeWeight(0.80);
		mainFrame.add(splitPane, BorderLayout.CENTER);

		panelGaufre.revalidate();
		panelHistorique.revalidate();
		barMenu.revalidate();


	}

	public void newGameMenu(int type){
		JFrame jf = new JFrame("New Game");
		jf.setMinimumSize(new Dimension(210, 100));
		JPanel pane = new JPanel(new GridLayout(1, 3));
		pane.setPreferredSize(new Dimension(200, 30));

		SpinnerNumberModel sp1 = new SpinnerNumberModel(jeu.nbligne, 1, 13, 1);
		SpinnerNumberModel sp2 = new SpinnerNumberModel(jeu.nbcolonne, 1, 13, 1);
		JSpinner l = new JSpinner(sp1);
		JSpinner c = new JSpinner(sp2);

		JButton b = new JButton("New Game!");

		l.setPreferredSize(new Dimension(50, 30));
		c.setPreferredSize(new Dimension(50, 30));
		b.setPreferredSize(new Dimension(200, 30));

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				jf.dispose();
				controleur.newJeu(type,(Integer)sp1.getNumber(),(Integer)sp2.getNumber());
				jeu = controleur.getJeu();
				newGame();
			}
		});


		pane.add(l);
		pane.add(c);
		pane.add(b);
		jf.add(pane);
		jf.setVisible(true);

	}

}
