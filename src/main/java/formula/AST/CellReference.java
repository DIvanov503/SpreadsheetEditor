package formula.AST;

public class CellReference implements Expression {
    public String sheet;
    public String reference;

    @Override
    public void accept(Visitor v) {
        v.visitCellReference(this);
    }
}
