import java.util.Random;

public class Grid {

    private Cell [][] grid;
    private Random rand=new Random();
    private int nbRow;
    private int nbColumn;

    public Grid(int nbRow, int nbColumn) {
        grid = new Cell[nbRow][nbColumn];
        this.generateGrid(nbRow, nbColumn);
        this.nbColumn=nbColumn;
        this.nbRow=nbRow;
    }

    private void generateGrid (int nbColumn, int nbRow){
        boolean isBorder;
        for (int y=0; y<nbRow; y++){
            for (int x=0; x<nbColumn; x++){
                Position position = new Position(x,y);
                isBorder =(x==0||x==nbColumn-1||y==0||y==nbRow-1);
                grid[x][y]= new Cell(position,isBorder,(isBorder||rand.nextInt(100)>10)?false:true);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void printGrid(){
        int wordSize=0;
        for (int y=0; y<nbRow;y++){
            for (int x=0; x<nbColumn; x++){
                wordSize=12-this.grid[x][y].getContent().toString().length();
               System.out.print(this.grid[x][y].getContent());
               for(int i=0;i<wordSize;i++){
                System.out.print(" ");
               }
               System.out.print("|");
            }
            System.out.println();
        }
    }
}
