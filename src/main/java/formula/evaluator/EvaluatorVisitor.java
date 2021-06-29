package formula.evaluator;

import formula.AST.*;
import spreadsheet.ICell;
import spreadsheet.ISheet;

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
        cell.setValue(valueStack.pop());
    }

    @Override
    public void visitFormula(Formula f) throws TypeErrorException {
        f.exp.accept(this);
    }

    @Override
    public void visitBinaryExpression(BinaryExpression exp) throws TypeErrorException {
        exp.left.accept(this);
        exp.right.accept(this);
        Object right = valueStack.pop(), left = valueStack.pop();
        switch (exp.operator) {
            case PLUS -> {
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
                if (left instanceof Integer && right instanceof Integer) {
                    valueStack.push((Integer)left / (Integer)right);
                } else if  (left instanceof Integer && right instanceof Double) {
                    valueStack.push((Integer)left / (Double)right);
                } else if (left instanceof Double && right instanceof Integer) {
                    valueStack.push((Double)left / (Integer)right);
                } else if (left instanceof Double && right instanceof Double) {
                    valueStack.push((Double)left / (Double)right);
                } else {
                    throw new TypeErrorException("Incompatible types");
                }
            }
            case MUL -> {
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
                }

            }
            case RANGE -> { }
            default -> { }
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

    }

    @Override
    public void visitCellReference(CellReference ref) {
        ISheet targetSheet = ref.sheet == null ? sheet : sheet.getSpreadsheet().getSheet(ref.sheet);
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
