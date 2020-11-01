import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Minions extends Character {
    private int mp;
    protected Master master;
    private Random rand = new Random();

    public Minions(Gang gang, Position position, Grid grid) {
        super(new ArrayList<String>(), 1, gang, position, grid);
        this.mp = 10;
        this.grid.getGrid()[position.getX()][position.getY()].setMinion(this);
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void move() {
        if(this.mp>=0){
            List<Position> shortestPath = shortestPath();
            if (checkEnoughMP(shortestPath)) {
                List<Cell> AdjacentCells = getAdjacentCells(this.position);
                Cell nextCell;
                if (AdjacentCells.size() != 0) {
                    nextCell = AdjacentCells.get(rand.nextInt(AdjacentCells.size()));
                } else
                    return;
    
                switch (nextCell.getContent()) {
                    case Void:
                        this.grid.getGrid()[this.position.getX()][this.position.getY()].removeCharacter();
                        this.setPosition(nextCell.getPosition());
                        this.mp--;
                        nextCell.setMinion(this);
                        break;
                    case Border:
                        break;
                    case Obstacle:
                        break;
                    case CHARACTER:
                        interactWithCharacterRPC(nextCell.getPosition());
                        break;
                    default:
                        break;
                }
            } else {
                backToSafeZone(shortestPath);
            }
        }
        else{
            System.out.println(this.master+"'s minion is out of the game because he don't have enought MP");
            Cell dead = this.grid.getGrid()[this.position.getX()][this.position.getY()];
            dead.removeCharacter();
            dead.setContent(Content.Obstacle);
        }
    }

    private void interactWithCharacterRPC(Position characterPosition) {
        Minions characterMet = grid.getGrid()[characterPosition.getX()][characterPosition.getY()].getMinion();
        if(characterMet != this){
            if (characterMet.getMaster() == this.master) {
                System.out.println("this is a friend");
                shareKnowledge("teamMate", characterMet);
            } else if (characterMet.getMaster()!=this.master && characterMet.gang == this.gang) {
                System.out.println("yo yo, wasup gang mate !!");
                shareKnowledge("gangMate", characterMet);
            } else {
                System.out.println("Suprise motherfucker !!!");
                fightRPC(characterMet);
            }
        }
    }

    public abstract List<Cell> getAdjacentCells(Position currentPosition);

    private void shareKnowledge(String withWho, Character characterMet) {
        List<String> notPresent = new ArrayList<String>(characterMet.insultList);
        notPresent.removeAll(this.insultList); // All unknown insults of the charac
        if (!notPresent.isEmpty()) {
            switch (withWho) {
                case "teamMate":
                    this.insultList.addAll(notPresent);
                    System.out.println("Take all my list !");
                    break;
                case "gangMate":
                    int halfOfnotPresent = notPresent.size() / 2;
                    for (int i = 0; i < halfOfnotPresent; i++) {
                        this.insultList.add(characterMet.insultList.get(i));
                    }
                    System.out.println("OK ! I give you the half...");
                    break;
            }
        }
    }

    private Boolean checkEnoughMP(List<Position> path) {
        if (path.size() < this.mp) {
            return true;
        } else {
            return false;
        }
    }

    private List<Position> shortestPath() {
        List<Position> possibleEnd = new ArrayList<Position>();
        int masterSize = this.master.size;

        // Stock the SafeZone's Cells
        for (int y = this.master.getPosition().getY(); y <= masterSize; y++) {
            for (int x = this.master.getPosition().getX(); x <= masterSize; x++) {
                if (y == masterSize || x == masterSize) {
                    Position currentPosition = this.grid.getGrid()[x][y].getPosition();
                    possibleEnd.add(currentPosition);
                }
            }
        }
        return leeAlgorithm(this.position, possibleEnd);
    }

    private List<Position> leeAlgorithm(Position start, List<Position> safeZone) {
        List<Integer> leeInt = new ArrayList<Integer>();
        List<Position> leePositions = new ArrayList<Position>();
        List<Position> shortPathList = new ArrayList<Position>();
        Boolean endFound = false;
        Position endPosition = null;
        // Fill in the lee's tables
        int i = 0;
        leePositions.add(start);
        leeInt.add(i);
        while (!endFound) {
            //For every cells with the index i
            List<Integer> allIndex = findAllIndexes(leeInt, i);
            for(int index : allIndex){
                for (Cell cell : getAdjacentCells(leePositions.get(index))) {
                    if (cell.getContent() == Content.Void && !leePositions.contains(cell.getPosition())) {
                        leePositions.add(cell.getPosition());
                        leeInt.add(i + 1);
                    }
                    //SafeZone found
                    if (safeZone.contains(cell.getPosition())) {
                        endFound = true;
                        endPosition = cell.getPosition();
                        break;
                    }
                }
                if(endFound){break;}
            }
            i++;
        }
        
        // Store the good path
        shortPathList.add(endPosition);
        for (int j=i-1; j >= 0; j--) {
            for (Cell cell : getAdjacentCells(shortPathList.get(shortPathList.size() - 1))) {
                if(leePositions.indexOf(cell.getPosition()) != -1 ){
                    if (leeInt.get(leePositions.indexOf(cell.getPosition())) == j) {
                        shortPathList.add(cell.getPosition());
                        break;
                    }
                }   
            }
        }
        shortPathList = shortPathList.stream().distinct().collect(Collectors.toList());
        return shortPathList;
    }

    //For leeAlgorithm
    private List<Integer> findAllIndexes(List<Integer> listOfIntegers,int i){
        List<Integer> listOfIndex = new ArrayList<>();
        for(int k=0;k<listOfIntegers.size();k++){
            if(listOfIntegers.get(k)==i){
                listOfIndex.add(k);
            }
        }
        return listOfIndex;
    }

    private void fightRPC(Minions minion) {
        int scMinionMet = 0;
        int scMinion = 0;
        int var1 = -1;
        int var2 = -1;

        while (scMinionMet == 0 || scMinion == 0) {
            var1 = this.chooseTypeRPC();
            var2 = minion.chooseTypeRPC();

            if ((var1 == 0 && var2 == 2) || (var1 == 1 && var2 == 0) || (var1 == 2 && var2 == 1)) {
                scMinion = 1;
                System.out.println("Minion wins");
            }

            if ((var2 == 0 && var1 == 2) || (var2 == 1 && var1 == 0) || (var2 == 2 && var1 == 1)) {
                scMinionMet = 1;
                System.out.println("Minion's met wins");
            }
        }

        if (scMinion == 1) {

        }
    }

    private int chooseTypeRPC() {
        int x;
        Random rand = new Random();
        switch (x = rand.nextInt(3)) {
            case (0):
                System.out.println("Rock is chosen");
                break;
            case (1):
                System.out.println("Paper is chosen");
                break;
            case (2):
                System.out.println("Cisors are chosen");
                break;
        }
        return x;
    }

    private void backToSafeZone(List<Position> shortestPath) {
        if(shortestPath.size()>1){
            System.out.println("Back to Safe Zone");
            int x = shortestPath.get(shortestPath.size()-2).getX();
            int y = shortestPath.get(shortestPath.size()-2).getY();
            Cell nextCell = grid.getGrid()[x][y];
            this.grid.getGrid()[this.position.getX()][this.position.getY()].removeCharacter();
            this.setPosition(nextCell.getPosition());
            this.mp--;
            nextCell.setMinion(this);
        }
        else if(this.position==shortestPath.get(0)){
            this.mp=10;
            shareKnowledge("teamMate", this.master);
            System.out.println("Refill...");
        }
    }
}