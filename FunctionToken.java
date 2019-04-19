class FunctionToken extends Token {
    private Function function;
    
    FunctionToken(Function function) {
        super(function.getName());
        this.function = function;
    }
    
    Function getFunction() {
        return function;
    }
}
