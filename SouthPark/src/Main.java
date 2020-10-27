import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        int i=0;
        Grid grid = new Grid(10,10);
        Minions minions1 = new BadassBoys(Gang.WestGang,new Position(1, 4), grid);
        Minions minions2 = new Bullygirls(Gang.WestGang,new Position(2, 4), grid);
        Minions minions3 = new DrunkBoys(Gang.WestGang,new Position(3, 4), grid);
        List<String> insultCartman = new ArrayList<>();
        insultCartman.add("Va chier !!!");
        insultCartman.add("Ils ont tué Kenny !");
        Position[] cartmanMinionsPositions = new Position[]{minions1.position,minions2.position,minions3.position};
        Master cartman = new Master("Cartmarn",insultCartman,3,Gang.WestGang,new Position(1,1),cartmanMinionsPositions,grid);
        minions1.setMaster(cartman);


        while (i<1){
            grid.printGrid();
            minions1.move();
            i++;
            TimeUnit.SECONDS.sleep(1);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }
      }
}
