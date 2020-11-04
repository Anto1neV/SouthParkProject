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
        this.name = name;
        for (int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                int positionX = position.getX()>Main.X_TILES/2?position.getX()-i:position.getX()+i;
                int positionY = position.getY()>Main.Y_TILES/2?position.getY()-j:position.getY()+j;
                Cell sfCell = this.grid.getGrid()[positionX][positionY];
                this.safeZone.add(sfCell.getPosition());
                switch (name){
                    case "Cartman" :
                        sfCell.setContent(Content.CartmanSafeZone);
                        break;
                    case "Kenny" :
                        sfCell.setContent(Content.KennySafeZone);
                        break;
                    case "Kyle":
                        sfCell.setContent(Content.KyleSafeZone);
                        break;
                    case "Stan":
                        sfCell.setContent(Content.StanSafeZone);
                        break;
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
