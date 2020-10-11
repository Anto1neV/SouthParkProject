import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        int i=0;
        Grid grid = new Grid(10,10);
        Minions minions1 = new Minions(Gang.WestGang,new Position(1, 1), grid.getGrid());
        grid.printGrid();

        while (i<10){
            minions1.move();
            grid.printGrid();
            i++;
            TimeUnit.SECONDS.sleep(1);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }
      }
}
