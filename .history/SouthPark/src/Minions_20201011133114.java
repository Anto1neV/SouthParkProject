import java.util.ArrayList;
import java.util.Random;

public class Minions extends Character {
    private int mp;
    private Cell[] adjacentCells = new Cell[9];
    private Master master;
    private Random rand = new Random();
    private Cell[][] grid;

    public Minions(Gang gang,Position position,Cell[][] grid){
        super(new ArrayList<String>(), 1, gang, position);
        this.mp=20;
        this.grid=grid;
        this.grid[position.getX()][position.getY()].setCharacter(this);
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void move(){
        setAdjacentecells();
        Cell nextCell = adjacentCells[rand.nextInt(9)];
        switch (nextCell.getContent()){
            case Void:
                this.position.setX(nextCell.getPosition().getX());
                this.position.setY(nextCell.getPosition().getY());
                this.mp--;
                nextCell.setCharacter(this);
                System.out.println("the cell is empty");
                break;
            case Border:
            System.out.println("the cell is a border");
                break;
            case Obstacle:
                System.out.println("the cell is an obstacle");
                break;
            case Character:
                System.out.println("there is a mate here");;
                interactWithCharacter(nextCell.getPosition());
        }
        
    }

    private void interactWithCharacter(Position characterPosition) {
        Minions characterMet = grid[characterPosition.getX()][characterPosition.getY()].getCharacter();
        if(characterMet.master==this.master){
            System.out.println("this is a friend");
            shareKnowledge();
        }
        else if(characterMet.gang==this.gang){
            System.out.println("yo yo, wasup !!");
        }
        else{
            System.out.println("Suprise motherfucker !!!");
        }
    }

    private void setAdjacentecells(){
        int i=0;
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                adjacentCells[i]=grid[this.position.getX()+x][this.position.getY()+y];
                i++;
            }
        }
    }

    private void shareKnowledge(){

    }

}
