import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Minions extends Character {
    private int mp;
    private Master master;
    private Random rand = new Random();


    public Minions(Gang gang,Position position,Grid grid){
        super(new ArrayList<String>(), 1, gang, position,grid);
        this.mp=20;
        this.grid.getGrid()[position.getX()][position.getY()].setCharacter(this);
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void move(){
        Cell nextCell = getAdjacentCells(this.position)[rand.nextInt(9)];
        switch (nextCell.getContent()){
            case Void:
                this.grid.getGrid()[this.position.getX()][this.position.getY()].removeCharacter();;
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
            case CHARACTER:
                System.out.println("there is a mate here");;
                interactWithCharacter(nextCell.getPosition());
                break;
        }
        
    }

    private void interactWithCharacter(Position characterPosition) {
        Minions characterMet = grid.getGrid()[characterPosition.getX()][characterPosition.getY()].getCharacter();
        if(characterMet.master==this.master){
            System.out.println("this is a friend");
            shareKnowledge("teamMate",characterMet);
        }
        else if(characterMet.gang==this.gang){
            System.out.println("yo yo, wasup !!");
            shareKnowledge("gangMate",characterMet);
        }
        else{
            System.out.println("Suprise motherfucker !!!");
        }
    }

    private Cell[] getAdjacentCells(Position currentPosition){
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

    private void shareKnowledge(String withWho, Minions characterMet){
        List<String> notPresent = new ArrayList<String>(characterMet.insultList);
        notPresent.removeAll(this.insultList); //Toutes les insultes du character rencontré non connues
        if(!notPresent.isEmpty()){
            switch(withWho){
                case "teamMate":
                    this.insultList.addAll(notPresent);
                    System.out.println("Take all my list !");
                    break;
                case "gangMate":
                    int halfOfnotPresent = notPresent.size()/2;
                    for(int i=0;i<halfOfnotPresent;i++){
                        this.insultList.add(characterMet.insultList.get(i));
                    }
                    System.out.println("OK ! I give you the half...");
                    break;
            }
        }
    }

    private List<Position> shortestPath(){
        List<Position> posibleEnd = new ArrayList<Position>() ;
        List<Position> shortestPath = new ArrayList<Position>() ;

        for(int y=-(this.master.size/2);y<=this.master.size/2;y++){
            for (int x=-(this.master.size/2);x<=this.master.size/2;x++){
                Position currentPosition =  new Position(x,y);
                posibleEnd.add(currentPosition);
            }
        }
        int lenght = 0;
        for(Position endPosition : posibleEnd){
            if(leeAlgorithm(this.position, endPosition).size()>0){
                lenght=leeAlgorithm(this.position, endPosition).size();
                shortestPath=posibleEnd;
            }
        }
    }

    private List<Position>  leeAlgorithm(Position start, Position end){
        List<Integer> leeInt = new ArrayList<Integer>();
        List<Position> leePositions = new ArrayList<Position>();
        List<Position> leePositionsVisited = new ArrayList<Position>();
        List<Position> shortPathList = new ArrayList<Position>();
        int minDist = Integer.MAX_VALUE;

       // Fill in the lee's tables
        int i=0;
        leePositions.add(start);
        leeInt.add(i);
        while (i<grid.getGrid().length){
            if(leePositions.get(i)!=leePositionsVisited.get(i)){
                for (Cell cell : getAdjacentCells(leePositions.get(i))){
                    if(cell.getContent()==Content.Void){
                            leePositions.add(cell.getPosition());
                            leeInt.add(i+1);
                            leePositionsVisited.add(leePositions.get(i));
                    }
                    else if(cell.getPosition()==end){
                        minDist=i+1;
                        break;
                    }
                }
            }
            i++;
        }
        //Store the good path
        shortPathList.add(end);
        while(i!=0){
            for (Cell cell : getAdjacentCells(shortPathList.get(i))){
                if(cell.getPosition()==leePositions.get(i)){
                    shortPathList.add(leePositions.get(i));
                    i--;
                }
            }
        }
        return shortPathList;
    }

}