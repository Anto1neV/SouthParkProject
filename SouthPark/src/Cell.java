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

        getChildren().addAll(border, text);

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
                this.text.setText("C");
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
            break;
            case KennySafeZone:
                this.text.setText("");
                this.border.setStroke(Color.ORANGE);
            break;
        }
    }


}
