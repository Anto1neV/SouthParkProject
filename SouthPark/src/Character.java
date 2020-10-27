import java.util.List;

abstract class Character {
    
    protected List<String> insultList;
    protected int size;
    protected Gang gang;
    protected Position position;
    protected Grid grid;

    protected Character(List<String> insultList,int size,Gang gang,Position position,Grid grid){
        this.insultList = insultList;
        this.size=size;
        this.gang=gang;
        this.position=position;
        this.grid=grid;
    }

    public List<String> getInsultList() {
        return insultList;
    }

    public void setInsultList(List<String> insultList) {
        this.insultList = insultList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Gang getGang() {
        return gang;
    }

    public void setGang(Gang gang) {
        this.gang = gang;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
