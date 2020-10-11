public class Cell {
    Content content = Content.Void;
    Position position;
    public Cell(Position position){
        this.position=position;
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
