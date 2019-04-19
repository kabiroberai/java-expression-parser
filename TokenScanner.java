class TokenScanner {
    private String s;
    private int len;
    private int currIdx = 0;
    
    private Operator[] operators;
    private Function[] functions;
    private Constant[] constants;
    
    private boolean needsNext = true;
    private Token next = null;
    
    TokenScanner(String s, Operator[] operators, Function[] functions, Constant[] constants) {
        s = s.trim();
        this.s = s;
        this.len = s.length();
        
        this.operators = operators;
        this.functions = functions;
        this.constants = constants;
    }
    
    private boolean isAtEnd() {
        return currIdx >= len;
    }
    
    private char currChar() {
        return s.charAt(currIdx);
    }
    
    private boolean isNumeric() {
        char c = currChar();
        return '0' <= c && c <= '9';
    }
    
    private Token fetchNext() {
        // sanitize
        if (isAtEnd()) return null;
        while (currChar() == ' ') currIdx++;
        
        // numbers
        if (isNumeric() || currChar() == '.') {
            long beforeDecimal = 0;
            long afterDecimal = 0;
            boolean reachedDecimal = false;
            
            while (!isAtEnd() && (isNumeric() || currChar() == '.')) {
                char curr = currChar();
                currIdx++;
                
                if (curr == '.') {
                    reachedDecimal = true;
                    continue;
                }
                
                if (reachedDecimal) {
                    afterDecimal = afterDecimal * 10 + (curr - '0');
                } else {
                    beforeDecimal = beforeDecimal * 10 + (curr - '0');
                }
            }

            double num;
            if (afterDecimal == 0) {
                num = beforeDecimal;
            } else {
                num = beforeDecimal + (double)afterDecimal * Math.pow(10, -((int)Math.log10(afterDecimal) + 1));
            }
            return new NumericToken(num);
        }
        
        // functions
        int nextParenIdx = s.indexOf("(", currIdx);
        if (nextParenIdx != currIdx && nextParenIdx != -1) {
            String tokName = s.substring(currIdx, nextParenIdx);
            for (Function fn : functions) {
                if (fn.getName().equals(tokName)) {
                    currIdx = nextParenIdx;
                    return new FunctionToken(fn);
                }
            }
        }
        
        char c = currChar();
        currIdx++;
        
        // parens and comma
        switch (c) {
        case ',':
            return new CommaToken();
        case '(':
            return new ParenToken(c, ParenToken.ParenType.OPEN);
        case ')':
            return new ParenToken(c, ParenToken.ParenType.CLOSE);
        }
        
        // constants
        for (Constant co : constants) {
            if (c == co.getSymbol()) {
                return new ConstantToken(co);
            }
        }
        
        // operators
        for (Operator op : operators) {
            if (c == op.getSymbol()) {
                return new OperatorToken(op);
            }
        }
        
        return null;
    }
    
    private void fetchNextIfNeeded() {
        if (!needsNext) return;
        next = fetchNext();
        needsNext = false;
    }
    
    boolean hasNext() {
        fetchNextIfNeeded();
        return next != null;
    }
    
    Token next() {
        fetchNextIfNeeded();
        needsNext = true;
        return next;
    }
}
