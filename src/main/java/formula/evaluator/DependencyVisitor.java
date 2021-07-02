package formula.evaluator;

import formula.AST.*;
import spreadsheet.ICell;
import spreadsheet.ISheet;

import java.util.Arrays;

public class DependencyVisitor implements Visitor {

    private static DependencyGraph dependencyGraph;

    private static DependencyVisitor dependencyVisitor;

    private ICell cell;

    private boolean dependenciesFound;

    public static DependencyVisitor getDependencyVisitor() {
        if (dependencyVisitor == null) {
            dependencyVisitor = new DependencyVisitor();
        }
        return dependencyVisitor;
    }

    public boolean addDependencies(DependencyGraph dependencyGraph, Formula formula, ICell cell) {
        this.dependencyGraph = dependencyGraph;
        this.cell = cell;
        dependenciesFound = false;
        visitFormula(formula);
        return dependenciesFound;
    }

    @Override
    public void visitFormula(Formula f) {
        f.exp.accept(this);
    }

    @Override
    public void visitBinaryExpression(BinaryExpression exp) {
        if (exp.operator == BinaryOperator.RANGE) {
            if (exp.left instanceof CellReference && exp.right instanceof CellReference) {
                CellReference leftCellReference = (CellReference)exp.left, rightCellReference = (CellReference)exp.right;
                ISheet leftSheet = leftCellReference.sheet == null
                        ? cell.getSheet()
                        : cell.getSheet().getSpreadsheet().getSheet(leftCellReference.sheet),
                        rightSheet = rightCellReference.sheet == null
                                ? cell.getSheet()
                                : cell.getSheet().getSpreadsheet().getSheet(rightCellReference.sheet);
                if (leftSheet != rightSheet) {
                    return;
                }
                int leftColumn = Math.min(leftCellReference.column, rightCellReference.column),
                        rightColumn = Math.max(leftCellReference.column, rightCellReference.column),
                        topRow = Math.min(leftCellReference.row, rightCellReference.row),
                        bottomRow = Math.max(leftCellReference.row, rightCellReference.row);
                for (int i = topRow; i <= bottomRow; ++i) {
                    for (int j = leftColumn; j <= rightColumn; ++j) {
                        dependencyGraph.addDependency(cell.getAddress(), leftSheet.getCellAt(i, j).getAddress());
                    }
                }
            }
        } else {
            exp.left.accept(this);
            exp.right.accept(this);
        }
    }

    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.exp.accept(this);
    }

    @Override
    public void visitFunctionCall(FunctionCall call) {
        call.argumentList.forEach((arg) -> arg.accept(this));
    }

    @Override
    public void visitCellReference(CellReference ref) {
        ISheet targetSheet = ref.sheet == null ? cell.getSheet() : cell.getSheet().getSpreadsheet().getSheet(ref.sheet);
        dependencyGraph.addDependency(cell.getAddress(), targetSheet.getCellAt(ref.row, ref.column).getAddress());
        dependenciesFound = true;
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
