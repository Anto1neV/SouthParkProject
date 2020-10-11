
public class Main {
    public static void main(String[] args) throws Exception {
        int i=0;
        Grid grid = new Grid(10,10);
        Minions minions1 = new Minions(Gang.WestGang,new Position(1, 1), grid.getGrid());
        grid.printGrid();

        while (i<1){
            minions1.move();
            i++;
        }
      }
}
