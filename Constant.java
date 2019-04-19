class Constant {
    private char symbol;
    private double value;
    
    Constant(char symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }
    
    char getSymbol() {
        return symbol;
    }
    
    double getValue() {
        return value;
    }
}
