
import java.util.ArrayList;
import java.util.Random;

public class BadassBoys extends Minions  {
    private Random rand = new Random();

    public BadassBoys(Gang gang, Position position, Grid grid) {
        super(gang, position, grid);
    }


    //diagonale
    @Override
    public ArrayList <Cell>  getAdjacentCells(Position currentPosition){
        ArrayList <Cell> adjacentCells = new ArrayList<>();
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                switch (rand.nextInt(2)){
                    case 0:
                        if ((x==-1 && y==-1) || (x==-1 && y==1) || (x==1 && y==-1) || (x==1 && y==1)){
                            adjacentCells.add(grid.getGrid()[currentPosition.getX()+x][currentPosition.getY()+y]);
                            System.out.println();
                        } 
                        break;
                    case 1:
                    if ((x==-1 && y==0) || (x==1 && y==0) || (x==0 && y==-1) || (x==0 && y==1)){
                        adjacentCells.add(grid.getGrid()[currentPosition.getX()+x][currentPosition.getY()+y]);
                        System.out.println();
                    }
                    break;
                }
            }
        }
        return adjacentCells;
    }
    
}
