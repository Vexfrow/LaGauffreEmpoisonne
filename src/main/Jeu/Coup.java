
public class Coup {
    int l,c;



Coup(int ligne, int colonne){
    this.l = ligne;
    this.c = colonne;
}
public Coup cloner(){
    Coup cp = new Coup(this.l,this.c);
    return cp;
}

}
