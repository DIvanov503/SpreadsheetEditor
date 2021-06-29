package formula.evaluator;

import formula.AST.*;
import spreadsheet.ICell;
import spreadsheet.ISheet;

import java.util.Arrays;

public class DependencyVisitor implements Visitor {

    DependencyGraph dependencyGraph;

    private static DependencyVisitor dependencyVisitor;

    private ICell cell;

    public static DependencyVisitor getDependencyVisitor() {
        if (dependencyVisitor == null) {
            dependencyVisitor = new DependencyVisitor();
        }
        return dependencyVisitor;
    }

    public void addDependencies(DependencyGraph dependencyGraph, Formula formula, ICell cell) {
        this.dependencyGraph = dependencyGraph;
        this.cell = cell;
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
        ISheet targetSheet = ref.sheet == null ? cell.getSheet() : cell.getSheet().getSpreadsheet().getSheet(ref.sheet);
        dependencyGraph.addDependency(cell, targetSheet.getCellAt(ref.row, ref.column));
    }

    @Override
    public void visitParenExpression(ParenExpression exp) throws TypeErrorException {
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
