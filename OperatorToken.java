class OperatorToken extends Token {    
    private Operator operator;
    
    OperatorToken(Operator operator) {
        super(String.valueOf(operator.getSymbol()));
        this.operator = operator;
    }
    
    Operator getOperator() {
        return operator;
    }
}
