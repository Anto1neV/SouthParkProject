public class Grid {
    
    private int nbRow;
    private int nbColumn;
    private Cell [][] grid;

    public Grid(int nbRow, int nbColumn) {
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


}
