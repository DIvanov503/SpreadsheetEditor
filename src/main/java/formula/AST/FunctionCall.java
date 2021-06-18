package formula.AST;

import java.util.List;

public class FunctionCall implements Expression {
    String functionName;
    List<Expression> parameters;

    @Override
    public void accept(Visitor v) {
        v.visitFunctionCall(this);
    }
}
