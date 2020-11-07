import java.util.Random;

public class Grid {

    private Cell [][] grid;
    private Random rand=new Random();
    private int nbRow;
    private int nbColumn;
    private static Grid uniqueGrid;

    private Grid(int nbRow, int nbColumn) {
        grid = new Cell[nbRow][nbColumn];
        this.generateGrid(nbRow, nbColumn);
        this.nbColumn=nbColumn;
        this.nbRow=nbRow;
    }

    public static Grid getGridInstance(int nbRow, int nbColumn){
        if (uniqueGrid==null){
            uniqueGrid = new Grid(nbRow,nbColumn);
        }
        return uniqueGrid;
    }

    private void generateGrid (int nbColumn, int nbRow){
        boolean isBorder;
        for (int y=0; y<nbRow; y++){
            for (int x=0; x<nbColumn; x++){
                Position position = new Position(x,y);
                isBorder =(x==0||x==nbColumn-1||y==0||y==nbRow-1);
                grid[x][y]= new Cell(position,isBorder,(isBorder||rand.nextInt(100)>5)?false:true);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
