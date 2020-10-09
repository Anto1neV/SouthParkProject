public class Minions extends Character {
    private int mp;
    private Cell[] adjacentCell;
    private Master master;

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

}
