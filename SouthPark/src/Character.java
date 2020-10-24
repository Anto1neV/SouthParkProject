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
}
