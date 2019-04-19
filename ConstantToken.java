class ConstantToken extends Token {
    private Constant co;
    ConstantToken(Constant co) {
        super(String.valueOf(co.getSymbol()));
        this.co = co;
    }
    
    Constant getConstant() {
        return co;
    }
}
