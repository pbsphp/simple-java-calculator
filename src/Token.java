
/**
 * This class describes tokens and contains methods for working with tokens.
 * For example isOperator - is token operator or not, isFunction - is token
 * function name.
 *
 * @author pbsphp
 */

public class Token {

    /**
     * Constructor of Token.
     *
     * @param token as string, e.g. "+", "10", "(".
     */
    public Token(String tokenAsString) {
        this.tokenAsString = tokenAsString;
    }


    /**
    * Converts token to string. (for new Token(T) it returns T)
    *
    * @return token as string
    */
    public String toString() {
        return this.tokenAsString;
    }


    /**
     * Tests if token is operator
     *
     * @return is token operator
     */
    public boolean isOperator() {
        return contains(operators, tokenAsString);
    }


    /**
     * Tests if token is number
     *
     * @return is token number
     */
    public boolean isNumber() {
        char fst = tokenAsString.charAt(0);
        return Character.isDigit(fst);
    }


    /**
     * Tests if token is function name
     *
     * @return is token function name
     */
    public boolean isFunction() {
        char fst = tokenAsString.charAt(0);
        return Character.isAlphabetic(fst);
    }


    /**
     * Tests if token is separator in function arguments default is ','
     *
     * @return is token separator
     */
    public boolean isSeparator() {
        return tokenAsString.equals(",");
    }


    /**
     * Tests if token is left brace
     *
     * @return is token '('
     */
    public boolean isLeftBracket() {
        return tokenAsString.equals("(");
    }


    /**
     * Tests if token is right brace
     *
     * @return is token ')'
     */
    public boolean isRightBracket() {
        return tokenAsString.equals(")");
    }


    /**
     * Tests if token is left associative operator
     *
     * @return is token left associative
     */
    public boolean isLeftAssoc() {
        return !this.isRightAssoc();
    }


    /**
     * Tests if token is right associative operator
     *
     * @return is token right associative
     */
    public boolean isRightAssoc() {
        return contains(rightAssocOperators, tokenAsString);
    }


    /**
     * Tests if token is binary operator like '+' in 'A + B'
     *
     * @return is token binary operator
     */
    public boolean isBinary() {
        return !this.isUnary();
    }


    /**
     * Tests if token is unary operator like '-' in 'A * -B'
     *
     * @return is token unary operator
     */
    public boolean isUnary() {
        return this.isOperator() && tokenAsString.startsWith("u");
    }


    /**
     * Returns priority of operator.
     * For example priority of + is lower than priority of *
     *
     * @return priority
     */
    public int getPriority() {
        for (int i = 0; i < priorityTable.length; ++i) {
            if (contains(priorityTable[i], tokenAsString)) {
                return i;
            }
        }

        return 0;
    }


    /**
     * Converts token to number (now is Double)
     * Token must be number!
     *
     * @bug bad conversion of not-numeric tokens
     *
     * @return token as number
     */
    public double toNumber() {
        return Double.parseDouble(tokenAsString);
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
    String[] rightAssocOperators = { };

    String[][] priorityTable = {{ "+", "-" }, { "*", "/" }};
}
