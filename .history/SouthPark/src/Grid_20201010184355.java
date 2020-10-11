import java.util.Random;

public class Grid {

    private Cell [][] grid;
    private Random rand=new Random();

    public Grid(int nbRow, int nbColumn) {
        grid = new Cell[nbRow][nbColumn];
        this.generateGrid(nbRow, nbColumn);
    }

    private void generateGrid ( int nbRow, int nbColumn){
        boolean isBorder;
        for (int x=0; x<nbRow; x++){
            for (int y=0; y<nbColumn; y++){
                Position position = new Position(x,y);
                isBorder =(x==0||x==nbRow-1||y==0||y==nbColumn-1);
                grid[x][y]= new Cell(position,isBorder,(isBorder||rand.nextInt(100)>10)?false:true);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
