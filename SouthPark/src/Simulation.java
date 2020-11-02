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
                        kenny.getMinions().get(i).move();
                        cartman.getMinions().get(i).move();
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
                    BadassBoys bb1 = new BadassBoys(Gang.WestGang, new Position(4, 4), grid);
                    Bullygirls bg1 = new Bullygirls(Gang.WestGang, new Position(2, 4), grid);
                    DrunkBoys db1 = new DrunkBoys(Gang.WestGang, new Position(3, 4), grid);

                    BadassBoys bb2 = new BadassBoys(Gang.EastGang, new Position(5, 9), grid);
                    Bullygirls bg2 = new Bullygirls(Gang.EastGang, new Position(5, 8), grid);
                    DrunkBoys db2 = new DrunkBoys(Gang.EastGang, new Position(5, 7), grid);

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
                    cartman.setMinions(bb1, bg1, db1);

                    kenny = new Master("Kyle", insultKyle, 3, Gang.EastGang, new Position(9, 9), grid);
                    kenny.setMinions(bb2, bg2, db2);
                }
            };

            if (!isCharacterset) {
                Platform.runLater(setCharacter);
                isCharacterset = true;
            }
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                }
                Platform.runLater(minionMover);
                if (i == 99) {
                    Platform.runLater(buttonUpdater);
                    thread.interrupt();
                }
            }
        }

    });

    public Thread getThread() {
        return this.thread;
    }

}
// https://riptutorial.com/javafx/example/7291/updating-the-ui-using-platform-runlater