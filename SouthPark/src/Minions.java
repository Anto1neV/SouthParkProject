import java.util.ArrayList;
import java.util.List;

public class Minions extends Character {
    private int mp;
    private Cell[] adjacentCell;
    private Master master;

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

}
