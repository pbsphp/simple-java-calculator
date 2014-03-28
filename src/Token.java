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
        return tokenAsString.equals(",");
    }


    public boolean isLeftBracket() {
        return tokenAsString.equals("(");
    }


    public boolean isRightBracket() {
        return tokenAsString.equals(")");
    }


    public boolean isLeftAssoc() {
        return !this.isRightAssoc();
    }


    public boolean isRightAssoc() {
        return contains(rightAssocOperators, tokenAsString);
    }


    public boolean isBinary() {
        return !this.isUnary();
    }


    public boolean isUnary() {
        return this.isOperator() && tokenAsString.startsWith("u");
    }


    public int getPriority() {
        for (int i = 0; i < priorityTable.length; ++i) {
            if (contains(priorityTable[i], tokenAsString)) {
                return i;
            }
        }

        return 0;
    }


    protected boolean contains(String[] strings, String str) {
        for (String el : strings) {
            if (el.equals(str)) {
                return true;
            }
        }

        return false;
    }


    public double toNumber() {
        return Double.parseDouble(tokenAsString);
    }


    String tokenAsString;

    String[] operators = { "+", "-", "*", "/" };
    String[] rightAssocOperators = { };

    String[][] priorityTable = {{ "+", "-" }, { "*", "/" }};
}
