

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EcouteurDeSouris implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Le bouton de la souris a été pressé en (" + e.getX() + ", " + e.getY() + ")");
	}

	// Toutes les méthodes qui suivent font partie de l'interface. Si nous ne
	// nous en servons pas, il suffit de les déclarer vides.
	// Une autre manière de faire, plus simple, est d'hériter de MouseAdapter,
	// qui est une classe qui hérite de MouseListener avec une implémentation
	// vide de toutes ses méthodes.
	@Override
	public void mouseClicked(MouseEvent e) { }
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
}
