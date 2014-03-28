class Token {
    public Token(String tokenAsString) {
        this.tokenAsString = tokenAsString;
    }


    public String toString() {
        return this.tokenAsString;
    }


    public boolean isOperator() {
        return contains(operators, tokenAsString);
    }


    public boolean isNumber() {
        char fst = tokenAsString.charAt(0);
        return Character.isDigit(fst);
    }


    public boolean isFunction() {
        char fst = tokenAsString.charAt(0);
        return Character.isAlphabetic(fst);
    }


    public boolean isSeparator() {
        return (tokenAsString.equals(","));
    }


    public boolean isLeftBracket() {
        return (tokenAsString.equals("("));
    }


    public boolean isRightBracket() {
        return (tokenAsString.equals(")"));
    }


    public boolean isLeftAssoc() {
        return true; // !!!
    }


    public boolean isRightAssoc() {
        return false; // !!!
    }


    public boolean isBinary() {
        return true; // !!!
    }


    public boolean isUnary() {
        return false; // !!!
    }


    public int getPriority() {
        return 1; // !!!
    }


    protected boolean contains(String[] strings, String str) {
        for (String el : strings) {
            if (el.equals(str)) {
                return true;
            }
        }

        return false;
    }


    String tokenAsString;

    String[] operators = { "+", "-", "*", "/" };

}
