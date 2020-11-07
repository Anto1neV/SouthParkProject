import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends StackPane{
    private static final int TILE_SIZE = 40;
    private Content content = Content.Void;
    private Position position;
    private Minions minion;
    
    //Graphic elements
    private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
    private Text text = new Text();
    private ImageView imageView = new ImageView();
    
    public Cell(Position position,Boolean isBorder,Boolean isObstacle){
        this.position=position;
        if(isBorder){
            this.content = Content.Border;
        }
        if(isObstacle){
            this.content=Content.Obstacle;
        }

        border.setStroke(Color.LIGHTGRAY);
        text.setFont(Font.font(18));
        text.setStroke(Color.LIGHTGRAY);
        this.setGraphic();
        getChildren().addAll(border, text, imageView);

        setTranslateX(position.getX() * TILE_SIZE);
        setTranslateY(position.getY() * TILE_SIZE);
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
        setGraphic();
    }

    public Position getPosition() {
        return position;
    }

    public Minions getMinion() {
        return minion;
    }

    public void setMinion(Minions minion) {
        this.minion = minion;
        setContent(Content.CHARACTER);
        setGraphic();
    }

    public void removeCharacter(){
        this.minion=null;
        setContent(Content.Void);
        setGraphic();
    }

    private void setGraphic(){
        switch(this.content){
            case CHARACTER:
                setMinionGraphic();
            break;
            case Obstacle:
                this.text.setText("O");
            break;
            case Border:
                this.text.setText("B");
            break;
            case Void:
                this.text.setText("");
            break;
            case CartmanSafeZone:
                this.text.setText("");
                this.border.setStroke(Color.RED);
                this.border.setFill(Color.RED);
            break;
            case KennySafeZone:
                this.text.setText("");
                this.border.setStroke(Color.ORANGE);
                this.border.setFill(Color.ORANGE);
            break;
            case KyleSafeZone:
                this.text.setText("");
                this.border.setStroke(Color.rgb(46, 253, 92));
                this.border.setFill(Color.rgb(46, 253, 92));
                break;
            case StanSafeZone:
                this.text.setText("");
                this.border.setStroke(Color.BLUE);
                this.border.setFill(Color.BLUE);
                break;
        }
    }
    public void setMinionGraphic(){
        if(this.minion.getMaster()!=null){
            switch (this.minion.getMaster().getName()){
                case "Cartman":
                    this.text.setText("C");
                    this.text.setFill(Color.RED);
                    break;
                case "Kenny":
                    this.text.setText("K");
                    this.text.setFill(Color.ORANGE);
                case "Kyle":
                    this.text.setText("K");
                    this.text.setFill(Color.rgb(46, 253, 92));
                    break;
                case "Stan":
                    this.text.setText("S");
                    this.text.setFill(Color.BLUE);
                break;
            }
        }
    }
}
