package formula.evaluator;

import formula.AST.*;

import java.util.Arrays;

public class DependencyVisitor implements Visitor {

    DependencyGraph dependencyGraph;

    private static DependencyVisitor dependencyVisitor;

    private String ref;

    public static DependencyVisitor getDependencyVisitor() {
        if (dependencyVisitor == null) {
            dependencyVisitor = new DependencyVisitor();
        }
        return dependencyVisitor;
    }

    public void addDependencies(DependencyGraph dependencyGraph, Formula formula, String ref) {
        this.dependencyGraph = dependencyGraph;
        this.ref = ref;
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
        dependencyGraph.addDependency(this.ref,
                (ref.sheet == null
                        ? Arrays.stream(this.ref.split("\\.")).findFirst().orElseThrow()
                        : ref.sheet) + "." + ref.reference);
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
