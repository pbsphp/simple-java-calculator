import java.util.ArrayList;


class Parser {
    public Parser(String expression) {
        this.expression = expression;
    }


    public double calculate() {
        ArrayList<Token> tokens = getTokensAsList();
        return (double)tokens.size();
    }


    ArrayList<Token> getTokensAsList() {
        boolean inIdentifier = false;

        String currentToken = new String();
        ArrayList<Token> tokens = new ArrayList<Token>();

        for (char c : this.expression.toCharArray()) {
            if (Character.isWhitespace(c)) {
                inIdentifier = false;
            }
            else if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                inIdentifier = true;
                currentToken += c;
            }
            else {
                inIdentifier = false;
                if (!currentToken.isEmpty()) {
                    tokens.add(new Token(currentToken));
                    currentToken = new String();
                }

                currentToken += c;
                tokens.add(new Token(currentToken));
                currentToken = new String();
            }
        }

        if (!currentToken.isEmpty()) {
            tokens.add(new Token(currentToken));
        }

        return tokens;
    }


    String expression;
}
