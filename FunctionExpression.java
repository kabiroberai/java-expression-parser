import java.util.ArrayList;

class FunctionExpression implements Expression {
    private Function function;
    private ArrayList<Expression> arguments;
    
    FunctionExpression(Function function) {
        this.function = function;
        this.arguments = new ArrayList<Expression>();
    }
    
    void addArgument(Expression argument) {
        arguments.add(argument);
    }
    
    public double evaluate() {
        double[] args = new double[arguments.size()];
        for (int i = 0; i < arguments.size(); i++) {
            args[i] = arguments.get(i).evaluate();
        }
        return function.evaluate(args);
    }
    
    public String toString() {
        String[] args = new String[arguments.size()];
        for (int i = 0; i < arguments.size(); i++) {
            args[i] = arguments.get(i).toString();
        }
        return function.getName() + "(" + String.join(", ", args) + ")";
    }
}
