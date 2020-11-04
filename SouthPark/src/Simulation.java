import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Simulation {

    private Button button;
    private Master cartman;
    private Master kenny;
    private Grid grid;
    private Boolean endOfTheGame = false;

    public Simulation(Button button, Grid grid) {
        this.button = button;
        this.grid = grid;
    }

    // longrunning operation runs on different thread
    private Thread thread = new Thread(new Runnable() {
        boolean isCharacterset = false;

        @Override
        public void run() {
            Runnable minionMover = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 3; i++) {
                        cartman.getMinions().get(i).move();
                        kenny.getMinions().get(i).move();
                        if(cartman.getInsultList().size()==6){
                            System.out.println("Cartman WIN the game");
                            System.out.println("\"Lâche toi ! Vas-y mets le feu ! Disco Sensation !!\"");
                            endOfTheGame = true;
                            break;
                        } else if (kenny.getInsultList().size()==6){
                            System.out.println("Kenny WIN the game");
                            System.out.println("Fmmmmmpmffmffmp çmmm fpmmmm");
                            endOfTheGame = true;
                            break;
                        }
                    }
                }
            };

            Runnable buttonUpdater = new Runnable() {

                @Override
                public void run() {
                    button.setText("Restart ?");
                }

            };
            Runnable setCharacter = new Runnable() {
                @Override
                public void run() {
                    List<String> insultCartman = new ArrayList<String>() {
                        {
                            add("Va chier !!!");
                            add("Ils ont tué Kenny !");
                            add("VASY MAMAN BAISE MOI");
                        }
                    };

                    List<String> insultKyle = new ArrayList<String>() {
                        {
                            add("putain d'enfoiré de fils de pute");
                            add("Vous etes un tel connard que je vous inscris pour l'oscar du plus grand connard de l'univers!");
                            add("Pourquoi s'insulter alors qu'on peux fumer un pétard ?");
                        }
                    };

                    cartman = new Master("Cartman", insultCartman, 3, Gang.WestGang, new Position(1, 1), grid);
                    BadassBoys bb1 = new BadassBoys(Gang.WestGang, new Position(4, 4), grid);
                    Bullygirls bg1 = new Bullygirls(Gang.WestGang, new Position(2, 4), grid);
                    DrunkBoys db1 = new DrunkBoys(Gang.WestGang, new Position(3, 4), grid);
                    cartman.setMinions(bb1, bg1, db1);

                    kenny = new Master("Kenny", insultKyle, 3, Gang.EastGang, new Position(18, 13), grid);
                    BadassBoys bb2 = new BadassBoys(Gang.EastGang, new Position(18, 10), grid);
                    Bullygirls bg2 = new Bullygirls(Gang.EastGang, new Position(17, 10), grid);
                    DrunkBoys db2 = new DrunkBoys(Gang.EastGang, new Position(16, 10), grid);
                    kenny.setMinions(bb2, bg2, db2);
                }
            };

            if (!isCharacterset) {
                Platform.runLater(setCharacter);
                isCharacterset = true;
            }
            for (int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
                Platform.runLater(minionMover);
                if (i == 9999) {
                    Platform.runLater(buttonUpdater);
                    thread.interrupt();
                }
                if(endOfTheGame){
                    break;
                }
            }
        }

    });

    public Thread getThread() {
        return this.thread;
    }

}
// https://riptutorial.com/javafx/example/7291/updating-the-ui-using-platform-runlater