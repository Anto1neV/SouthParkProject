public class Cell {
    private Content content = Content.Void;
    private Position position;
    private Minions minion;
    
    public Cell(Position position,Boolean isBorder,Boolean isObstacle){
        this.position=position;
        if(isBorder){
            this.content = Content.Border;
        }
        if(isObstacle){
            this.content=Content.Obstacle;
        }
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
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
    }

    public void removeCharacter(){
        this.minion=null;
        setContent(Content.Void);
    }
}
