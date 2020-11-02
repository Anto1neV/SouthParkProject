import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        int i=0;
        Grid grid = new Grid(10,10);
        BadassBoys bb1 = new BadassBoys(Gang.WestGang,new Position(4, 4), grid);
        Bullygirls bg1 = new Bullygirls(Gang.WestGang,new Position(2, 4), grid);
        DrunkBoys db1 = new DrunkBoys(Gang.WestGang,new Position(3, 4), grid);

        BadassBoys bb2 = new BadassBoys(Gang.EastGang,new Position(7, 7), grid);
        Bullygirls bg2 = new Bullygirls(Gang.EastGang,new Position(8, 7), grid);
        DrunkBoys db2 = new DrunkBoys(Gang.EastGang,new Position(9, 7), grid);

        //Cartman
        List<String> insultCartman = new ArrayList<>();
        insultCartman.add("Va chier !!!");
        insultCartman.add("Ils ont tué Kenny !");
        Master cartman = new Master("Cartman",insultCartman,3,Gang.WestGang,new Position(1,1),grid);
        cartman.setMinions(bb1,bg1,db1);
        bb1.insultList.add("putain d'enfoiré de fils de pute");
        bg1.insultList.add("VASY MAMAN BAISE MOI");
        db1.insultList.add("Pourquoi s'insulter alors qu'on peux fumer un pétard ?");

        //Kenny
        List<String> insultKenny = new ArrayList<>();
        Master kenny = new Master("Kenny",insultKenny,3,Gang.EastGang,new Position(9,9),grid);


        while (i<60){
            grid.printGrid();
            bb1.move();
            bg1.move();
            db1.move();
            i++;
            TimeUnit.SECONDS.sleep(1);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }
      }
}