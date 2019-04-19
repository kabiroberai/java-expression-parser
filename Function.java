import java.util.function.ToDoubleFunction;
class Function {
    private String name;
    private ToDoubleFunction<double[]> evaluator;
    
    Function(String name, ToDoubleFunction<double[]> evaluator) {
        this.name = name;
        this.evaluator = evaluator;
    }
    
    String getName() {
        return name;
    }
    
    double evaluate(double[] args) {
        return evaluator.applyAsDouble(args);
    }
}
