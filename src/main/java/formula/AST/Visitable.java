package formula.AST;

public interface Visitable {
    void accept(Visitor v);
}
