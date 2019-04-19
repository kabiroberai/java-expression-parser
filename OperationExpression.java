class OperationExpression implements Expression {
    private Expression left;
    private Operator operator;
    private Expression right;
    
    OperationExpression(Expression left, Operator operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    
    Expression getLeft() {
        return left;
    }
  
    Operator getOperator() { 
        return operator;
    }    
    
    Expression getRight() {
        return right;
    }
    
    public double evaluate() {
        double lhs = left.evaluate();
        double rhs = right.evaluate();
        return operator.evaluate(lhs, rhs);
    }
    
    public String toString() {
        return "(" + getLeft() + " " + getOperator() + " " + getRight() + ")";
    }
}
