import java.util.List;

abstract class Character {
    
    protected List<String> insultList;
    protected int size;
    protected Gang gang;
    protected Position position;

    protected Character(List<String> insultList,int size,Gang gang,Position position){
        this.insultList = insultList;
        this.size=size;
        this.gang=gang;
        this.position=position;
    }
}
