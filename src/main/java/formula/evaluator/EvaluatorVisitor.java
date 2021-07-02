package formula.evaluator;

import formula.AST.*;
import spreadsheet.ICell;
import spreadsheet.ISheet;

import java.util.Arrays;
import java.util.SimpleTimeZone;
import java.util.Stack;

public class EvaluatorVisitor implements Visitor {

    private static EvaluatorVisitor evaluatorVisitor;

    Stack<Object> valueStack = new Stack<>();

    ISheet sheet;

    public static EvaluatorVisitor getEvaluatorVisitor() {
        if (evaluatorVisitor == null) {
            evaluatorVisitor = new EvaluatorVisitor();
        }
        return evaluatorVisitor;
    }

    public void evaluate(ICell cell) throws TypeErrorException {
        valueStack.clear();
        this.sheet = cell.getSheet();
        visitFormula(cell.getFormula());
        System.out.println(valueStack.peek());
        if (valueStack.peek() instanceof Object[][]) {
            throw new TypeErrorException("Cell value cannot be a range of cell values");
        }
        cell.updateValue(valueStack.peek() == null ? 0 : valueStack.pop());
    }

    @Override
    public void visitFormula(Formula f) throws TypeErrorException {
        f.exp.accept(this);
    }

    @Override
    public void visitBinaryExpression(BinaryExpression exp) throws TypeErrorException {
        if (exp.operator == BinaryOperator.RANGE) {
            if (exp.left instanceof CellReference && exp.right instanceof CellReference) {
                System.out.println("Cell range");
                CellReference leftCellReference = (CellReference)exp.left, rightCellReference = (CellReference)exp.right;
                ISheet leftSheet = leftCellReference.sheet == null
                        ? sheet
                        : sheet.getSpreadsheet().getSheet(leftCellReference.sheet),
                        rightSheet = rightCellReference.sheet == null
                                ? sheet
                                : sheet.getSpreadsheet().getSheet(rightCellReference.sheet);
                if (leftSheet != rightSheet) {
                    throw new TypeErrorException("Cell range must be in the same sheet");
                }
                int leftColumn = Math.min(leftCellReference.column, rightCellReference.column),
                        rightColumn = Math.max(leftCellReference.column, rightCellReference.column),
                        topRow = Math.min(leftCellReference.row, rightCellReference.row),
                        bottomRow = Math.max(leftCellReference.row, rightCellReference.row);
                Object[][] values = new Object[bottomRow - topRow + 1][rightColumn - leftColumn + 1];
                for (int i = topRow; i <= bottomRow; ++i) {
                    for (int j = leftColumn; j <= rightColumn; ++j) {
                        values[i - topRow][j - leftColumn] = leftSheet.getValueAt(i, j);
                    }
                }
                valueStack.push(values);
                return;
            } else {
                throw new TypeErrorException("Cell range must be done on cells");
            }
        }
        exp.left.accept(this);
        exp.right.accept(this);
        System.out.println(exp.left + " " + exp.right);
        Object right = valueStack.pop(), left = valueStack.pop();
        switch (exp.operator) {
            case PLUS -> {
                if (left == null) {
                    left = 0;
                }
                if (right == null) {
                    right = 0;
                }
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left + (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left + (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left + (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left + (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case MINUS -> {
                if (left == null) {
                    left = 0;
                }
                if (right == null) {
                    right = 0;
                }
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left - (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left - (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left - (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left - (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case DIV -> {
                if (left == null) {
                    left = 0;
                }
                if (right == null) {
                    right = 0;
                }
                double rightDouble;
                if (right instanceof Integer) {
                    rightDouble = (Integer)right;
                } else if (right instanceof Double) {
                    rightDouble = (Double)right;
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
                if  (left instanceof Integer) {
                    valueStack.push((Integer)left / rightDouble);
                } else if (left instanceof Double) {
                    valueStack.push((Double) left / rightDouble);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case MUL -> {
                if (left == null) {
                    left = 0;
                }
                if (right == null) {
                    right = 0;
                }
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left * (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left * (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left * (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left * (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case EQ -> {
                valueStack.push(left.equals(right));
            }
            case NEQ -> {
                valueStack.push(!left.equals(right));
            }
            case GE -> {
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left >= (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left >= (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left >= (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left >= (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case LE -> {
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left <= (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left <= (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left <= (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left <= (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case GT -> {
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left > (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left > (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left > (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left > (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case LT -> {
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left < (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left < (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left < (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left < (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case OR -> {
                if (left instanceof Boolean && right instanceof Boolean) {
                    valueStack.push((Boolean)left || (Boolean)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case AND -> {
                if (left instanceof Boolean && right instanceof Boolean) {
                    valueStack.push((Boolean)left && (Boolean)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case POW -> {
                if (left instanceof Number && right instanceof Number) {
                    valueStack.push(Math.pow((Double)left, (Double)right));
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case RANGE -> {
                if (exp.left instanceof CellReference && exp.right instanceof CellReference) {
                    System.out.println("Cell range");
                    CellReference leftCellReference = (CellReference)exp.left, rightCellReference = (CellReference)exp.right;
                    ISheet leftSheet = leftCellReference.sheet == null
                            ? sheet
                            : sheet.getSpreadsheet().getSheet(leftCellReference.sheet),
                            rightSheet = rightCellReference.sheet == null
                                    ? sheet
                                    : sheet.getSpreadsheet().getSheet(rightCellReference.sheet);
                    if (leftSheet != rightSheet) {
                        throw new TypeErrorException("Cell range must be in the same sheet");
                    }
                    int leftColumn = Math.min(leftCellReference.column, rightCellReference.column),
                            rightColumn = Math.max(leftCellReference.column, rightCellReference.column),
                            topRow = Math.min(leftCellReference.row, rightCellReference.row),
                            bottomRow = Math.max(leftCellReference.row, rightCellReference.row);
                    Object[][] values = new Object[bottomRow - topRow + 1][rightColumn - leftColumn + 1];
                    for (int i = topRow; i <= bottomRow; ++i) {
                        for (int j = leftColumn; j <= rightColumn; ++j) {
                            values[i - topRow][j - leftColumn] = leftSheet.getValueAt(i, j);
                        }
                    }
                    valueStack.push(values);
                    return;
                } else {
                    throw new TypeErrorException("Cell range must be done on cells");
                }
            }
            default -> {
                throw new TypeErrorException("Unsupported operator " + exp.operator.name());
            }
        }
    }

    @Override
    public void visitUnaryExpression(UnaryExpression exp) throws TypeErrorException {
        exp.exp.accept(this);
        Object operand = valueStack.pop();
        switch (exp.operator) {
            case Plus -> {
                if (operand instanceof Number) {
                    valueStack.push(operand);
                } else {
                    throw new TypeErrorException("Incompatible type");
                }
            }
            case Minus -> {
                if (operand instanceof Integer) {
                    valueStack.push(-(Integer)operand);
                } else if (operand instanceof Double) {
                    valueStack.push(-(Double)operand);
                } else {
                    throw new TypeErrorException("Incompatible type");
                }
            }
        }
    }

    @Override
    public void visitFunctionCall(FunctionCall call) {
        call.argumentList.forEach((arg) -> arg.accept(this));
        Object[] args = new Object[call.argumentList.size()];
        for (int i = call.argumentList.size() - 1; i >= 0; --i) {
            args[i] = valueStack.pop();
        }
        if (call.functionName.equals("SUM")) {
            double resultDouble = 0.0;
            int resultInt = 0;
            boolean isInt = true;
            for (int i = 0; i < call.argumentList.size(); ++i) {
                System.out.println(isInt);
                if (args[i] == null) {
                    args[i] = 0;
                }
                if (args[i] instanceof Integer) {
                    if (isInt) {
                        resultInt += (Integer)args[i];
                    } else {
                        resultDouble += (Integer)args[i];
                    }
                } else if (args[i] instanceof Double) {
                    if (isInt) {
                        resultDouble = resultInt;
                    }
                    resultDouble += (Double)args[i];
                    System.out.println(args[i]);
                    isInt = false;
                } else if (args[i] instanceof Object[][]) {
                    Object[][] values = (Object[][])args[i];
                    for (int j = 0; j < values.length; ++j) {
                        for (int k = 0; k < values[j].length; ++k) {
                            if (values[j][k] == null) {
                                values[j][k] = 0;
                            }
                            if (values[j][k] instanceof Integer) {
                                if (isInt) {
                                    resultInt += (Integer)values[j][k];
                                } else {
                                    resultDouble += (Integer)values[j][k];
                                }
                            } else if (values[j][k] instanceof Double) {
                                if (isInt) {
                                    resultDouble = resultInt;
                                }
                                resultDouble += (Double)values[j][k];
                                isInt = false;
                            }
                        }
                    }
                } else {
                    throw new TypeErrorException("Unsupported SUM argument type");
                }
            }
            valueStack.push(isInt ? resultInt : resultDouble);
        } else {
            throw new TypeErrorException("Unsupported function " + call.functionName);
        }
    }

    @Override
    public void visitCellReference(CellReference ref) {
        ISheet targetSheet = ref.sheet == null ? sheet : sheet.getSpreadsheet().getSheet(ref.sheet);
        System.out.println("cell" + targetSheet.getValueAt(ref.row, ref.column));
        valueStack.push(targetSheet.getValueAt(ref.row, ref.column));
    }

    @Override
    public void visitParenExpression(ParenExpression exp) throws TypeErrorException {
        exp.exp.accept(this);
    }

    @Override
    public void visitIntegerNumber(IntegerNumber num) {
        valueStack.push(num.value);
    }

    @Override
    public void visitDoubleNumber(DoubleNumber num) {
        valueStack.push(num.value);
    }

    @Override
    public void visitStringLiteral(StringLiteral lit) {
        valueStack.push(lit.value);
    }
}
