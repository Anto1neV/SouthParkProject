import java.util.List;

public class Minions extends Character {
    private int mp;
    private Cell[] adjacentCell;
    private Master master;

    public Minions(List<String> insultList,int size,Gang gang,Position position,int mp){
        super(insultList, size, gang, position);

    }
    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

}
