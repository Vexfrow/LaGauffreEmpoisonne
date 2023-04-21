package Jeu;

public class newMain {


    public static void main(String[] args){

        Coup cp = new Coup(2,2);
        Jeu j = new Jeu(6,6);
        System.out.println(j);
        System.out.println("");
        System.out.println("");


        System.out.println(" joue en 2,2");
        j.joue(cp);
        System.out.println(j);
        System.out.println("");
        System.out.println("");


        System.out.println(" joue en 1,0");
        cp = new Coup(1,0);
        j.joue(cp);
        System.out.println(j);
        System.out.println("");
        System.out.println("");


        System.out.println("j'annule");
        j.annule();
        System.out.println(j);
        System.out.println("");
        System.out.println("");


        System.out.println("j'annule");
        j.annule();
        System.out.println(j);
        System.out.println("");
        System.out.println("");

        System.out.println("je refais!!!");
        j.refaire();
        System.out.println(j);
        System.out.println("");
        System.out.println("");

        System.out.println("je refais!!!");
        j.refaire();
        System.out.println(j);
        System.out.println("");
        System.out.println("");






    }
}