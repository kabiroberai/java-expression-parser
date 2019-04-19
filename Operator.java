import java.util.function.BinaryOperator;

class Operator {
    enum Associativity {
        LEFT, RIGHT
    }
    
    private char symbol;
    private int precedence;
    private Associativity associativity;
    private BinaryOperator<Double> evaluator;
    
    Operator(char symbol, int precedence, Associativity associativity, BinaryOperator<Double> evaluator) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.associativity = associativity;
        this.evaluator = evaluator;
    }
    
    char getSymbol() {
        return symbol;
    }
    
    int getPrecedence() {
        return precedence;
    }
    
    Associativity getAssociativity() {
        return associativity;
    }
    
    double evaluate(double lhs, double rhs) {
        return evaluator.apply(lhs, rhs);
    }
    
    public String toString() {
        return String.valueOf(symbol);
    }
}
