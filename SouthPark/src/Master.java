import java.util.ArrayList;
import java.util.List;

public class Master extends Character{

    private String name;
    private List<Minions> listOfMinions = new ArrayList<Minions>();

    public Master(
            String name,
            List<String> insultList,
            int size,
            Gang gang,
            Position position,
            Position[] minionsPositons,
            Grid grid){
                
        super(insultList, size, gang, position,grid);
        this.setMinions(this.listOfMinions,minionsPositons,gang,grid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return this.position;
    }

    public List<Minions> getListOfMinions() {
        return listOfMinions;
    }

    private void setMinions(List<Minions> listOfMinions, Position[] minionsPositons,Gang gang,Grid grid){
        listOfMinions.add(new BadassBoys(gang,minionsPositons[0],grid));
        listOfMinions.add(new Bullygirls(gang, minionsPositons[1], grid));
        listOfMinions.add(new DrunkBoys(gang, minionsPositons[2], grid));
    }

    public void setListOfMinions(List<Minions> listOfMinions) {
        this.listOfMinions = listOfMinions;
    }
}
