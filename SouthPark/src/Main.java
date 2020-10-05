public class Main {
    public static void main(String[] args) throws Exception {
        Grille G1 = new Grille(10, 20);
        
        G1.placement(3, 3, 'J');
        G1.displayGrille();

        System.out.println();

        Cartman C1 = new Cartman();
        C1.lecture();
    }
}
