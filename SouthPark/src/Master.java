import java.util.List;

public class Master extends Character{

    private String name;
    private BadassBoys badassBoys;
    private Bullygirls bullygirls;
    private DrunkBoys drunkBoys;

    public Master(
            String name,
            List<String> insultList,
            int size,
            Gang gang,
            Position position,
            Position[] minionsPositons,
            Grid grid){
        super(insultList, size, gang, position,grid);
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

    public void setMinions(BadassBoys badassBoys,Bullygirls bullygirls,DrunkBoys drunkBoys){
        this.badassBoys = badassBoys;
        this.bullygirls = bullygirls;
        this.drunkBoys = drunkBoys;
        badassBoys.setMaster(this);
        bullygirls.setMaster(this);
        drunkBoys.setMaster(this);
    }
}
