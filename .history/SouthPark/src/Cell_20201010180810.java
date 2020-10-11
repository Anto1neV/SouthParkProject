public class Cell {
    Content content = Content.Void;
    Position position;
    public Cell(Position position,Boolean isBorder){
        this.position=position;
        if(isBorder){
            this.content = Content.Border;
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

}
