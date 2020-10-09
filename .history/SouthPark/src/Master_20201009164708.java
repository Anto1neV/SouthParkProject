import java.util.List;

public class Master extends Character{

    private String name;
    private List<Minions> listOfMinions = new List<Minions>;

    public Master(String name){
        this.listOfMinions=
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Minions[] getListOfMinions() {
        return listOfMinions;
    }

    private setMinions(List<Minions> listOfMinions){
        for (int i = 0; i < 3; i++) {
            listOfMinions.add(new Minions());
        }
    }
}
