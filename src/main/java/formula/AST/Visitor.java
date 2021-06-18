package formula.AST;

public interface Visitor {
    void visitFormula(Formula f);
    void visitBinaryExpression(BinaryExpression exp);
    void visitUnaryExpression(UnaryExpression exp);
    void visitFunctionCall(FunctionCall call);
    void visitCellReference(CellReference ref);
    void visitParenExpression(ParenExpression exp);
    void visitIntegerNumber(IntegerNumber num);
    void visitDoubleNumber(DoubleNumber num);
    void visitStringLiteral(StringLiteral lit);
}
