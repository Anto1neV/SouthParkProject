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

public class Simulation{

    private Button button;
    private Master cartMan;
    private Grid grid;

    public Simulation(Button button,Grid grid){
        this.button=button;
        this.grid=grid;
    }

    // longrunning operation runs on different thread
    private Thread thread = new Thread(new Runnable() {
        boolean isCharacterset = false;

        @Override
        public void run() {
            Runnable minionMover = new Runnable() {
                @Override
                public void run() {
                    cartMan.getMinions().get(2).move();
                }
            };
            
            Runnable buttonUpdater = new Runnable(){

                @Override
                public void run() {
                    button.setText("Restart ?");
                }
                
            };
            Runnable setCharacter = new Runnable(){
                @Override
                public void run(){
                    BadassBoys minions1 = new BadassBoys(Gang.WestGang,new Position(4, 4), grid);
                    Bullygirls minions2 = new Bullygirls(Gang.WestGang,new Position(2, 4), grid);
                    DrunkBoys minions3 = new DrunkBoys(Gang.WestGang,new Position(3, 4), grid);
                    List<String> insultCartman = new ArrayList<String>();
                    insultCartman.add("Va chier !!!");
                    insultCartman.add("Ils ont tué Kenny !");
                    cartMan = new Master("CartMan",insultCartman,3,Gang.WestGang,new Position(1,1),grid);
                    cartMan.setMinions(minions1,minions2,minions3);
                    minions1.insultList.add("putain d'enfoiré de fils de pute");
                    minions2.insultList.add("VASY MAMAN BAISE MOI");
                    minions3.insultList.add("Pourquoi s'insulter alors qu'on peux fumer un pétard ?");
                }
            };

            if(!isCharacterset){
                Platform.runLater(setCharacter);
                isCharacterset=true;
            }
            for(int i=0;i<100;i++) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                }
                Platform.runLater(minionMover);
                if(i==99){
                    Platform.runLater(buttonUpdater);
                    thread.interrupt();
                }
            }
        }

    });
    public Thread getThread(){
        return this.thread;
    }


}
//https://riptutorial.com/javafx/example/7291/updating-the-ui-using-platform-runlater