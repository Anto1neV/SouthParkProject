

public class Bullygirls extends Minions  {

    public Bullygirls(Gang gang, Position position, Grid grid) {
        super(gang, position, grid);
    }




    @Override
    public Cell[] getAdjacentCells(Position currentPosition){
        Cell[] adjacentCells = new Cell[9];
        int i=0;
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                adjacentCells[i]=grid.getGrid()[currentPosition.getX()+x][currentPosition.getY()+y];
                i++;
            }
        }
        return adjacentCells;
    }
    
}
