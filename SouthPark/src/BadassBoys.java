
import java.util.ArrayList;
import java.util.List;

public class BadassBoys extends Minions  {

    public BadassBoys(Gang gang, Position position, Grid grid) {
        super(gang, position, grid);
    }

    @Override
    public List<Cell>  getAdjacentCells(Position currentPosition){
        List<Cell> adjacentCells = new ArrayList<Cell>();
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                adjacentCells.add(grid.getGrid()[currentPosition.getX() + x][currentPosition.getY() + y]);
            }
        }
        return adjacentCells;
    }
}
