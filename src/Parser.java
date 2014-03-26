import java.util.ArrayList;


class Parser {
    public Parser(String expression) {
        this.expression = expression;
    }


    public double calculate() {
        ArrayList<Token> tokens = getTokensAsList();
        return (double)tokens.size();
    }


    protected ArrayList<Token> getTokensAsList() {
        return new ArrayList<Token>();
    }


    String expression;
}
