import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
    private Scene scene;
    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;
    public static final int X_TILES = W / TILE_SIZE;
    public static final int Y_TILES = H / TILE_SIZE;
    private Grid grid = Grid.getGridInstance(X_TILES,Y_TILES);

    private Parent createContent() {
        Pane root = new Pane();
        Button runSimulationButton = new Button("Start");
        ImageView winnerView = new ImageView();
        Simulation sim = new Simulation(runSimulationButton,this.grid,winnerView);
        root.setPrefSize(W, H);

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Position pos = new Position(x, y);
                root.getChildren().add(grid.getGrid()[pos.getX()][pos.getY()]);
            }
        }
        
        sim.getThread().setDaemon(true);
        
        runSimulationButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0){
                sim.getThread().start();
                runSimulationButton.setText("Running");
            }
        });
        root.getChildren().addAll(runSimulationButton, winnerView);
        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        launch(args);
      }
}