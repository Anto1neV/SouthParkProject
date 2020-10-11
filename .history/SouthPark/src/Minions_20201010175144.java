import java.util.ArrayList;
import java.util.Random;

import javax.swing.border.Border;

public class Minions extends Character {
    private int mp;
    private Cell[] adjacentCell;
    private Master master;
    private Random rand = new Random();

    public Minions(Gang gang,Position position){
        super(new ArrayList<String>(), 1, gang, position);
        this.mp=20;
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

    }

    private void shareKnowledge()

}
