import java.util.Scanner;

class Driver {
    public static void main() {
        ExpressionParser parser = new ExpressionParser(new Operator[] {
            new Operator('^', 4, Operator.Associativity.RIGHT, Math::pow),
            new Operator('*', 3, Operator.Associativity.LEFT, (a, b) -> a * b),
            new Operator('/', 3, Operator.Associativity.LEFT, (a, b) -> a / b),
            new Operator('+', 2, Operator.Associativity.LEFT, (a, b) -> a + b),
            new Operator('-', 2, Operator.Associativity.LEFT, (a, b) -> a - b),
        }, new Function[] {
            new Function("sin", (args) -> Math.sin(args[0])),
            new Function("max", (args) -> {
                double largest = args[0];
                for (double arg : args) {
                    if (arg > largest) largest = arg;
                }
                return largest;
            })
        }, new Constant[] {
            new Constant('π', Math.PI)
        });
        
        System.out.print("Expression: ");
        String str = new Scanner(System.in).nextLine();
        Expression expr = parser.parse(str);
        System.out.println("Parsed: " + expr);
        System.out.println("Value: " + expr.evaluate());
    }
}
