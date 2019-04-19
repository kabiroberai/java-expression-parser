class NumericExpression implements Expression {
    private double value;
    NumericExpression(double value) {
        this.value = value;
    }
    
    double getValue() {
        return value;
    }
    
    public double evaluate() {
        return value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
}
