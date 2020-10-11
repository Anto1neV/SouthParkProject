import java.util.ArrayList;
import java.util.Random;

public class Minions extends Character {
    private int mp;
    private Cell[] adjacentCell = new Cell[9];
    private Master master;
    private Random rand = new Random();
    private Cell[][] grid;

    public Minions(Gang gang,Position position,Cell[][] grid){
        super(new ArrayList<String>(), 1, gang, position);
        this.mp=20;
        this.grid=grid;
    }
    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void move(){
        setAdjacentecells();
        Cell nextCell = adjacentCell[rand.nextInt(9)];
        switch (nextCell.content){
            case Void:
                this.position.setX(nextCell.position.getX());
                this.position.setY(nextCell.position.getY());
                this.mp--;
                break;
            case Border:
                break;
            case Obstacle:
                break;
            case Character:
                interactWithCharacter();
        }

        
    }

    private void interactWithCharacter() {
    }

    private void setAdjacentecells(){
        int i=0;
        for(int y=-1;y<=1;y++){
            for(int x=-1;y<=1;y++){
                adjacentCell[i]=grid[position.getX()+x][position.getY()+y];
            }
        }
    }

    private void shareKnowledge(){

    }

}
