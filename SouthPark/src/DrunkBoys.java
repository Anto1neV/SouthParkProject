
import java.util.ArrayList;

public class DrunkBoys extends Minions  {

    public DrunkBoys(Gang gang, Position position, Grid grid) {
        super(gang, position, grid);
    }



    //croix
    @Override
    public ArrayList <Cell>  getAdjacentCells(Position currentPosition){
        ArrayList <Cell> adjacentCells = new ArrayList<>();
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                if ((x==-1 && y==0) || (x==1 && y==0) || (x==0 && y==-1) || (x==0 && y==1)){
                    adjacentCells.add(grid.getGrid()[currentPosition.getX()+x][currentPosition.getY()+y]);
                }    
            }
        }
        return adjacentCells;
    }
    
}
