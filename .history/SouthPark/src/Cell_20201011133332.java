public class Cell {
    private Content content = Content.Void;
    private Position position;
    private Minions character;
    
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

    public Minions getCharacter() {
        return character;
    }

    public void setCharacter(Minions character) {
        this.character = character;
        setContent(Content.Character);
    }

    public void removeCharacter(){
        this.character=null;
        setContent(Content.Void);
    }

}
