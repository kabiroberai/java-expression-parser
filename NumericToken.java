class NumericToken extends Token {
    private double value;
    
    NumericToken(double value) {
        super(String.valueOf(value));
        this.value = value;
    }
    
    double getValue() {
        return value;
    }
}
