
package Interface;


import javax.swing.*;

import Controlleur.Controleur;
import Jeu.Coup;
import Jeu.Jeu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class demoFenetre implements Runnable {


	//Revoir les attributs
	private Color orange;
	private int ligne;
	private int colonne;
	Controleur c;
	JButton matrix[][];
	JPanel panelGaufre;
	JMenuBar barMenu;
	JPanel panelHistorique;


	public demoFenetre(int x, int y){
		this.ligne = x;
		this.colonne = y;
		this.orange = new Color(250, 180, 50);
		this.matrix = new JButton[this.ligne][this.colonne];
		try{
			SwingUtilities.invokeAndWait(this);
		}catch(Exception e){
			System.out.println("Marche pas");
		}
		
	}

	public demoFenetre(Jeu j, Controleur c){
		this(j.nbligne, j.nbcolonne);
		this.c = c;
	}


	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("La gaufre empoisonnée");

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on démarre
		frame.setSize(530, 300);
		frame.setMinimumSize(new Dimension(530, 300));


		//On initialise les différentes parties de l'interface
		initBarMenu(frame);
		initHistorique(frame);
		initGaufre(frame);

		frame.pack();
		frame.setVisible(true);

		frame.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e){
				if(frame.getWidth()< frame.getJMenuBar().getMinimumSize().getWidth()){
					frame.setSize(new Dimension((int)frame.getMinimumSize().getWidth(), (int)frame.getSize().getHeight()));
				}

				if(frame.getHeight()< frame.getJMenuBar().getMinimumSize().getHeight()){
					frame.setSize(new Dimension( (int)frame.getSize().getWidth(),(int)frame.getMinimumSize().getHeight()));
				}
			}
		});


	}



	//Mets à jour le terrain selon un tableau de char représentant le niveau
	public void majNiveau(int[][] niveau){
		for(int i = 0; i < ligne; i++){
			for(int j=0; j < colonne; j++){
				if(i == 0 && j ==0){
					System.out.println("i :" +i + ": j :"+ j);
				}else{
					System.out.println("i :" +i + ": j :"+ j);
					//System.out.println(matrix[i][j]);
					if(niveau[i][j] == 1){
						matrix[i][j].setBackground(new Color(255, 255, 255));
						matrix[i][j].setEnabled(false);
					}else{
						matrix[i][j].setBackground(orange);
						matrix[i][j].setEnabled(true);
					}
					System.out.println(matrix[i][j].isEnabled());
				}

			}
		}
	}


	public static void main(String[] args){
		new demoFenetre(5, 5);

	}


	public void initBarMenu(JFrame jframe){
		barMenu = new JMenuBar();

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
				c.annule();
				System.out.println("Clique sur le bouton \"Annuler Coup\" ");
			}
		});

		buttonRestaurerC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.rejoue();
				System.out.println("Clique sur le bouton \"Restaurer Coup\" ");
			}
		});

		barMenu.add(listeNouvellePartie);
		barMenu.add(buttonChargerP);
		barMenu.add(buttonSauvegarderP);
		barMenu.add(buttonAnnulerC);
		barMenu.add(buttonRestaurerC);

		//Rajout du menu au jframe
		jframe.setJMenuBar(barMenu);
	}



	public void initHistorique(JFrame mainPanel) {

		SpringLayout layoutMenu = new SpringLayout();

		//Panel contenant l'historique et l'information sur le tour actuel
		panelHistorique = new JPanel(layoutMenu);
		Dimension dim = new Dimension(mainPanel.getWidth()/3, mainPanel.getHeight());
		panelHistorique.setPreferredSize(dim);



		//Information sur le tour actuel
		JTextArea texteJoueur = new JTextArea("C'est au tour du joueur X");
		texteJoueur.setRows(1);
		texteJoueur.setEditable(false);

		//Separator
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(panelHistorique.getWidth(), 5));
		separator.setBackground(Color.getHSBColor(0, 0, 0));

		//Historique de la partie
		DefaultListModel<String> model = new DefaultListModel<>();
		model.addElement("Test1");
		model.addElement("Test2");
		model.addElement("Test3");

		JList<String> listeCoups = new JList<>(model);
		JTextArea titreHistorique = new JTextArea("Historique des coups");
		titreHistorique.setRows(1);
		titreHistorique.setEditable(false);

		listeCoups.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listeCoups.setLayoutOrientation(JList.VERTICAL);
		listeCoups.setVisibleRowCount(-1);


		layoutMenu.putConstraint(SpringLayout.NORTH, texteJoueur, 10, SpringLayout.NORTH, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.WEST, texteJoueur, 10, SpringLayout.WEST, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.EAST, texteJoueur, 10, SpringLayout.EAST, panelHistorique);

		layoutMenu.putConstraint(SpringLayout.NORTH, separator, 10, SpringLayout.SOUTH, texteJoueur);

		layoutMenu.putConstraint(SpringLayout.NORTH, titreHistorique, 10, SpringLayout.SOUTH, separator);
		layoutMenu.putConstraint(SpringLayout.WEST, titreHistorique, 10, SpringLayout.WEST, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.EAST, titreHistorique, 10, SpringLayout.EAST, panelHistorique);

		layoutMenu.putConstraint(SpringLayout.NORTH, listeCoups, 10, SpringLayout.SOUTH, titreHistorique);
		layoutMenu.putConstraint(SpringLayout.WEST, listeCoups, 10, SpringLayout.WEST, panelHistorique);
		layoutMenu.putConstraint(SpringLayout.EAST, listeCoups, 10, SpringLayout.EAST, panelHistorique);

		panelHistorique.add(texteJoueur,SpringLayout.NORTH);
		panelHistorique.add(separator);
		panelHistorique.add(titreHistorique,SpringLayout.NORTH);
		panelHistorique.add(listeCoups,SpringLayout.NORTH);


		panelHistorique.setBorder(BorderFactory.createLineBorder(Color.red));

		mainPanel.add(panelHistorique, SpringLayout.EAST);

	}


	public void initGaufre(JFrame jframe) {
		panelGaufre = new JPanel();
		panelGaufre.setLayout(new GridLayout(ligne, colonne, 0, 0));
		for(int i = 0; i < ligne; i++){
			for(int j=0; j < colonne; j++){
				JButton b = new JButton();				
				if(i == 0 && j ==0){
					b.setBackground(new Color(10, 240, 10));
				}else{
					b.setBackground(orange);
				}
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent a){
						c.joue(coupTable(b));
						System.out.println("Clicked");
					}
				});
				matrix[i][j] = b;
				//System.out.println(matrix[i][j]);

				panelGaufre.add(b);

			}
		}
		jframe.add(panelGaufre);
	}


	private Coup coupTable(JButton b){
		int i =0;
		int j =0;
		boolean bl = false;
		while(i < this.ligne && !bl){
			j = 0;
			while(j < this.colonne && !bl){
				bl = b.equals(matrix[i][j]);
				j++;
			}
			i++;
		}
		return new Coup(i-1, j-1);
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
		text.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}
			@Override
			public void keyPressed(KeyEvent e){
				char ch = e.getKeyChar();
				if(ch == '\n'){
					c.sauvegarder(text.getText());
					jf.dispose();
				}
			}
		});
		b.add(text);
		jf.add(b);
		jf.setSize(100,100);
		jf.setVisible(true);
	}

}
