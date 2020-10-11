public class Grille1 {
    
    private int nbRow;
    private int nbColumn;
    private Cell [][] grille;

    public Grille1(int nbRow, int nbColumn) {
        this.nbRow = nbRow;
        this.nbColumn = nbColumn;
        grille = new Cell[nbRow][nbColumn];
    }

    public void creationGrille ( int nbRow, int nbColumn){
        
        for (int i=0; i<nbRow; i++){
            for (int j=0; j<nbColumn; j++){
                
                grille[i][j]= new Cell();
            }
        }
    }

    public void displayGrille(){
        for (int i=0; i<nbRow; i++){
            for (int j=0; j<nbColumn; j++){
                
                System.out.print(" | " + grille[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }

}
