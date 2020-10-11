public class Cell {
    Content content = Content.Void;
    Position position;
    Boolean isBorder;
    public Cell(Position position,Boolean isBorder){
        this.position=position;
        this.isBorder = isBorder;
    }
}
