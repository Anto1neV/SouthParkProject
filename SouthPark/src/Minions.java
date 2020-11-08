import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Minions extends Character {
    private int mp;
    protected Master master;
    private final int MAXMP=70;

    public Minions(Gang gang, Position position, Grid grid) {
        super(new ArrayList<String>(), 1, gang, position, grid);
        this.mp = MAXMP;
        this.grid.getGrid()[position.getX()][position.getY()].setMinion(this);
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
        this.insultList.addAll(this.master.getInsultList());
    }

    public void move() {
        if (this.mp > 0) {
            List<Position> shortestPath = leeAlgorithm(this.position,this.master.getSafeZone());
            if (checkEnoughMP(shortestPath)) {
                List<Cell> AdjacentCells = getAdjacentCells(this.position);
                Cell nextCell;
                if (AdjacentCells.size() != 0) {
                    Random random = new Random();
                    nextCell = AdjacentCells.get(random.nextInt(AdjacentCells.size()));
                } else{
                    return;
                }

                switch (nextCell.getContent()) {
                    case Border:
                        break;
                    case Obstacle:
                        break;
                    case CHARACTER:
                        interactWithCharacterRPC(nextCell.getPosition());
                        break;
                    default:
                        this.grid.getGrid()[this.position.getX()][this.position.getY()].removeCharacter();
                            this.setPosition(nextCell.getPosition());
                            this.mp--;
                            nextCell.setMinion(this);
                        break;
                }
            } else {
                backToSafeZone(shortestPath);
            }
            refillMP();
        } else {
            //System.out.println(this.master + "'s minion is out of the game because he don't have enought MP");
            Cell dead = this.grid.getGrid()[this.position.getX()][this.position.getY()];
            dead.removeCharacter();
            dead.setContent(Content.Obstacle);
        }
    }

    private void interactWithCharacterRPC(Position characterPosition) {
        Minions minionMet = grid.getGrid()[characterPosition.getX()][characterPosition.getY()].getMinion();
        if (minionMet != this) {
            if (minionMet.getMaster() == this.master) {
                System.out.println("this is a friend");
                shareKnowledge("teamMate", minionMet);
            } else if (minionMet.getMaster() != this.master && minionMet.gang == this.gang) {
                System.out.println("yo yo, wasup gang mate !!");
                shareKnowledge("gangMate", minionMet);
            } else {
                System.out.println("Suprise motherfucker !!!");
                fightRPC(minionMet);
            }
        }
    }

    public abstract List<Cell> getAdjacentCells(Position currentPosition);

    private void shareKnowledge(String withWho, Character characterMet) {
        if(characterMet==this){
            List<String> notPresent = new ArrayList<String>(characterMet.insultList);
            notPresent.removeAll(this.master.getInsultList());
            if(!notPresent.isEmpty()){
                List<String> masterList = new ArrayList<>();
                masterList.addAll(this.master.insultList);
                masterList.addAll(notPresent);
                this.master.setInsultList(masterList);
            }
        }
        else{
            List<String> notPresent = new ArrayList<String>(characterMet.insultList);
            notPresent.removeAll(this.insultList); // All unknown insults of the charac
            if (!notPresent.isEmpty()) {
                switch (withWho) {
                    case "teamMate":
                    case "master":
                        this.insultList.addAll(notPresent);
                        System.out.println("Take all my list !");
                        break;
                    case "gangMate":
                        int halfOfnotPresent = notPresent.size() / 2;
                        for (int i = 0; i < halfOfnotPresent; i++) {
                            this.insultList.add(notPresent.get(i));
                        }
                        System.out.println("OK ! I give you the half...");
                        break;
                    case "ennemy":
                        int divRandom = (int) (Math.random()*notPresent.size()+1); 
                        int SomeOfnotPresent = notPresent.size() / divRandom; 
                        for (int i = 0; i < SomeOfnotPresent; i++) {
                            this.insultList.add(notPresent.get(i));
                        }
                        System.out.println("OK ! I give you " + SomeOfnotPresent + "bad words ...");
                        break;
                }
            }
        }
    }

    private Boolean checkEnoughMP(List<Position> path) {
        if (path.size() < this.mp - 1) {
            return true;
        } else {
            return false;
        }
    }

    private List<Position> leeAlgorithm(Position start, List<Position> safeZone) {
        List<Integer> leeInt = new ArrayList<Integer>();
        List<Position> leePositions = new ArrayList<Position>();
        List<Position> shortPathList = new ArrayList<Position>();
        Boolean endFound = false;
        Position endPosition = null;
        // Fill in the lee's tables
        Integer i = 0;
        leePositions.add(start);
        leeInt.add(i);
        for(int k=0;k<300;k++) {
            // For every cells with the index i
            List<Integer> allIndex = findAllIndexes(leeInt, i);
            for (Integer index : allIndex) {
                for (Cell cell : getAdjacentCells(leePositions.get(index))) {
                    if (cell.getContent() == Content.Void && !leePositions.contains(cell.getPosition())) {
                        leePositions.add(cell.getPosition());
                        leeInt.add(i + 1);
                    }
                    // SafeZone found
                    if (safeZone.contains(cell.getPosition())) {
                        endFound = true;
                        endPosition = cell.getPosition();
                        break;
                    }
                }
                if (endFound) {
                    break;
                }
            }
            i++;
        }
        if(!endFound){
            System.out.println("end not found !!!");
        }
        // Store the good path
        shortPathList.add(endPosition);
        for (int j = i - 1; j >= 0; j--) {
            for (Cell cell : getAdjacentCells(shortPathList.get(shortPathList.size() - 1))) {
                if (leePositions.indexOf(cell.getPosition()) != -1) {
                    if (leeInt.get(leePositions.indexOf(cell.getPosition())) == j && cell.getPosition() != start) {
                        shortPathList.add(cell.getPosition());
                        break;
                    }
                }
            }
        }
        shortPathList = shortPathList.stream().distinct().collect(Collectors.toList());
        return shortPathList;
    }

    // For leeAlgorithm
    private List<Integer> findAllIndexes(List<Integer> listOfIntegers, int i) {
        List<Integer> listOfIndex = new ArrayList<>();
        for (int k = 0; k < listOfIntegers.size(); k++) {
            if (listOfIntegers.get(k) == i) {
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
                shareKnowledge("ennemy", minion);
            }

            if ((var2 == 0 && var1 == 2) || (var2 == 1 && var1 == 0) || (var2 == 2 && var1 == 1)) {
                scMinionMet = 1;
                System.out.println("Ennemy wins");
            }
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

    private void refillMP() {
        if (this.master.getSafeZone().contains(this.position)) {
            this.mp = MAXMP;
            shareKnowledge("master", this.master);
            shareKnowledge("teamMate", this);
        }
    }

    private void backToSafeZone(List<Position> shortestPath) {
        if (shortestPath.size() > 0) {
            System.out.println("Back to Safe Zone");
            int x = shortestPath.get(shortestPath.size() - 1).getX();
            int y = shortestPath.get(shortestPath.size() - 1).getY();
            Cell nextCell = grid.getGrid()[x][y];
            this.grid.getGrid()[this.position.getX()][this.position.getY()].removeCharacter();
            this.setPosition(nextCell.getPosition());
            this.mp--;
            nextCell.setMinion(this);
        }
    }
}