package Jeu;


public class main_test {

	public static void main(String[] args) {
		
		Jeu j = new Jeu(10,10);
		
        
        Coup cp = new Coup(9,9);
        System.out.println(j);
        System.out.println("");
        System.out.println("");


        System.out.println(" joue en 9,9");
        j.joue(cp);
        System.out.println(j);
        System.out.println("");
        System.out.println("");


        System.out.println(" joue en 8,8");
        cp = new Coup(8,8);
        j.joue(cp);
        System.out.println(j);
        System.out.println("");
        System.out.println("");

        System.out.println(" joue en 7,7");
        cp = new Coup(7,7);
        j.joue(cp);
        System.out.println(j);
        System.out.println("");
        System.out.println("");

        
        System.out.println(" joue en 5,5");
        cp = new Coup(5,5);
        j.joue(cp);
        System.out.println(j);
        System.out.println("");
        System.out.println("");
        
        System.out.println("j'annule");
        j.annule();
        System.out.println(j);
        System.out.println("");
        System.out.println("");

        
		
		j.sauvegarde("rsc/sauvegarde/test.txt");
		
		
		
		Jeu j2 = new Jeu("rsc/sauvegarde/test.txt");
		
		
		// 
		System.out.println("refait");
		
		j2.annule();
		
		System.out.println(j2.toString());
		
		
	}

}