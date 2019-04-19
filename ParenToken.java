class ParenToken extends Token {
    enum ParenType {
        OPEN, CLOSE
    }
    
    private ParenType type;
    
    ParenToken(char c, ParenType type) {
        super(String.valueOf(c));
        this.type = type;
    }
    
    ParenType getParenType() {
        return type;
    }
}
