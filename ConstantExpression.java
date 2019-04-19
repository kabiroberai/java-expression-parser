class ConstantExpression implements Expression {
    private Constant co;
    ConstantExpression(Constant co) {
        this.co = co;
    }
    
    public double evaluate() {
        return co.getValue();
    }
    
    public String toString() {
        return String.valueOf(co.getSymbol());
    }
}
