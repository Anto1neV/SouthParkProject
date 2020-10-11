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
        
        for (int x=0; x<=nbRow; x++){
            for (int y=0; y<=nbColumn; y++){
                Position position = new Position(x,y);
                grille[x][y]= new Cell(position,(x==0||x==nbRow||y==0||y==nbColumn));
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
