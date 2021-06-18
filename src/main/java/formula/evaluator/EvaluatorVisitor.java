package formula.evaluator;

import formula.AST.*;

import java.util.Arrays;
import java.util.Stack;

public class EvaluatorVisitor implements Visitor {

    private static EvaluatorVisitor evaluatorVisitor;

    Stack<Object> valueStack;

    public static EvaluatorVisitor getEvaluatorVisitor() {
        if (evaluatorVisitor == null) {
            evaluatorVisitor = new EvaluatorVisitor();
        }
        return evaluatorVisitor;
    }

    public void evaluate(Formula formula, String sheetName) {
        visitFormula(formula);
    }

    @Override
    public void visitFormula(Formula f) {
        f.exp.accept(this);
    }

    @Override
    public void visitBinaryExpression(BinaryExpression exp) {
        exp.left.accept(this);
        exp.right.accept(this);
    }

    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.exp.accept(this);
    }

    @Override
    public void visitFunctionCall(FunctionCall call) {

    }

    @Override
    public void visitCellReference(CellReference ref) {

    }

    @Override
    public void visitParenExpression(ParenExpression exp) {
        exp.exp.accept(this);
    }

    @Override
    public void visitIntegerNumber(IntegerNumber num) {

    }

    @Override
    public void visitDoubleNumber(DoubleNumber num) {

    }

    @Override
    public void visitStringLiteral(StringLiteral lit) {

    }
}
