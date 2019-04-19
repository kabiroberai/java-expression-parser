import java.util.ArrayList;

class ExpressionParser {
    private Operator[] operators;
    private Function[] functions;
    private Constant[] constants;
    ExpressionParser(Operator[] operators, Function[] functions, Constant[] constants) {
        this.operators = operators;
        this.functions = functions;
        this.constants = constants;
    }
    
    // creates an operation on top of the stack using tok with the last two operands
    private void pushOperation(OperatorToken tok, Stack<Expression> stack) {
        Expression right = stack.pop();
        Expression left = stack.pop();
        stack.push(new OperationExpression(left, tok.getOperator(), right));
    }
    
    Expression parse(String expression) {
        TokenScanner sc = new TokenScanner(expression, operators, functions, constants);
        Stack<Token> toks = new Stack<Token>();
        Stack<Expression> operands = new Stack<Expression>();

        while (sc.hasNext()) {
            Token tok = sc.next();
            
            if (tok instanceof NumericToken) {
                NumericToken num = (NumericToken)tok;
                operands.push(new NumericExpression(num.getValue()));
            }  else if (tok instanceof ConstantToken) {
                ConstantToken co = (ConstantToken)tok;
                operands.push(new ConstantExpression(co.getConstant()));
            } else if (tok instanceof OperatorToken) {
                OperatorToken op = (OperatorToken)tok;
                int precedence = op.getOperator().getPrecedence();
                
                popOps: while (toks.peek() instanceof OperatorToken) {
                    OperatorToken top = (OperatorToken)toks.peek();
                    Operator topOp = top.getOperator();
                    int topPrec = topOp.getPrecedence();
                    
                    switch (topOp.getAssociativity()) {
                    case LEFT:
                        if (topPrec < precedence) break popOps;
                        break;
                    case RIGHT:
                        if (topPrec <= precedence) break popOps;
                        break;
                    }
                    
                    toks.pop();
                    pushOperation(top, operands);
                }
                
                toks.push(op);
            } else if (tok instanceof FunctionToken) {
                FunctionToken fn = (FunctionToken)tok;
                toks.push(fn);
                operands.push(new FunctionExpression(fn.getFunction()));
            } else if (tok instanceof CommaToken) {
                // parse the argument and add it to the function (which should be the topmost operand after the operators)
                while (toks.peek() instanceof OperatorToken) pushOperation((OperatorToken)toks.pop(), operands);
                Expression arg = operands.pop();
                FunctionExpression fn = (FunctionExpression)operands.peek();
                fn.addArgument(arg);
            } else if (tok instanceof ParenToken) {
                ParenToken paren = (ParenToken)tok;
                ParenToken.ParenType parenType = paren.getParenType();
                switch (parenType) {
                case OPEN:
                    toks.push(paren);
                    break;
                case CLOSE:
                    while (toks.peek() instanceof OperatorToken) pushOperation((OperatorToken)toks.pop(), operands);
                    toks.pop();
                    if (toks.peek() instanceof FunctionToken) {
                        toks.pop();
                        Expression arg = operands.pop();
                        FunctionExpression fn = (FunctionExpression)operands.peek();
                        fn.addArgument(arg);
                    }
                    break;
                }
            }
        }

        while (!toks.isEmpty()) pushOperation((OperatorToken)toks.pop(), operands);

        return operands.pop();
    }
}
