import java.util.ArrayList;
import java.util.List;

public class Master extends Character{

    private String name;
    private BadassBoys badassBoys;
    private Bullygirls bullygirls;
    private DrunkBoys drunkBoys;
    private ArrayList<Position> safeZone;
    public Master(
            String name,
            List<String> insultList,
            int size,
            Gang gang,
            Position position,
            Grid grid){
        super(insultList, size, gang, position,grid);
        this.safeZone = new ArrayList<Position>();
        for (int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                Cell sfCell = this.grid.getGrid()[position.getX()+i][position.getY()+j];
                this.safeZone.add(sfCell.getPosition());
                switch (name){
                    case "Cartman" :
                        sfCell.setContent(Content.CartmanSafeZone);
                    case "Kenny" :
                        sfCell.setContent(Content.KennySafeZone);
                    case "Kyle":
                        sfCell.setContent(Content.KyleSafeZone);
                    case "Stan":
                        sfCell.setContent(Content.StanSafeZone);
                }
            }
        }
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

    public ArrayList<Minions> getMinions(){
        ArrayList<Minions> list = new ArrayList<Minions>();
        list.add(this.drunkBoys);
        list.add(this.bullygirls);
        list.add(this.badassBoys);
        return list;
    }

    public ArrayList<Position> getSafeZone() {
        return safeZone;
    }

}
