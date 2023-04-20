



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class AireDeDessin extends JComponent {
	int counter;
	Image img;
	Image ing;

	public AireDeDessin() {
		// Chargement de l'image de la même manière que le fichier de niveaux
		
		try {
			// Chargement d'une image utilisable dans Swing
			InputStream in = new FileInputStream("./Assets/image.png");
			img = ImageIO.read(in);
			ing = ImageIO.read(new FileInputStream("./Assets/CaseGaufre.png"));
		} catch (Exception e) {
			System.exit(1);
		}
		counter = 1;
	}

	@Override
	public void paintComponent(Graphics g) {
		

		// Graphics 2D est le vrai type de l'objet passé en paramètre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		Graphics2D drawable = (Graphics2D) g;

		// On reccupere quelques infos provenant de la partie JComponent
		int width = getSize().width;
		int height = getSize().height;
		System.out.println("W - H " + width + " " + height);
		// On calcule le centre de la zone et un rayon
		Point center = new Point(width/2, height/2);

		// On efface tout
		drawable.clearRect(0, 0, width, height);

		// On affiche une petite image au milieu
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				drawable.drawImage(ing, i*40 , j*40, 40, 40, null);
			}
		}
		
	}
}