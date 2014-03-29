import java.util.ArrayList;
import java.util.Stack;
import java.lang.Exception;


/**
 * Class for calculating simple math expressions.
 * Supported operators are +, -, *, /, also brackets are available.
 *
 * @author pbsphp
 */

public class Parser {

    /**
     * Constructor of Parser. Gets expression as string and remembers it.
     * Not calculates it in constructor.
     *
     * @param expression
     */
    public Parser(String expression) {
        this.expression = expression;
    }


    /**
     * Calculates expression and returns result as number.
     *
     * @return calculated expression as number
     *
     * @throws UnsupportedOperationException if expresison
     *         contains unsupported things, e.g. functions.
     * @throws ArithmeticException if expression is invalid
     *         (zero division or unclosed brackets).
     */
    public double calculate() throws Exception {
        ArrayList<Token> tokensList = getTokensAsList();
        ArrayList<Token> tokensStack = backPolishNotation(tokensList);

        Stack<Double> operandsStack = new Stack<Double>();

        for (Token operand : tokensStack) {
            if (operand.isNumber()) {
                operandsStack.push(operand.toNumber());
            }
            else if (operand.isOperator()) {
                double result = calculateForOperator(operand, operandsStack);
                operandsStack.push(result);
            }
        }

        return operandsStack.pop();
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


    ArrayList<Token> backPolishNotation(ArrayList<Token> arrayList) throws Exception {
        ArrayList<Token> outputQueue = new ArrayList<Token>();
        Stack<Token> bufferStack = new Stack<Token>();

        for (Token token : arrayList) {
            if (token.isNumber()) {
                outputQueue.add(token);
            }
            else if (token.isFunction()) {
                throw new UnsupportedOperationException("Functions are not supported");
            }
            else if (token.isSeparator()) {
                throw new UnsupportedOperationException("Functions are not supported");
            }
            else if (token.isOperator()) {
                Token op1 = token;
                while (!bufferStack.empty() && bufferStack.peek().isOperator()) {
                    Token op2 = bufferStack.peek();
                    if (op1.isLeftAssoc() && op1.getPriority() <= op2.getPriority()
                        || op1.isRightAssoc() && op1.getPriority() < op2.getPriority()) {
                        outputQueue.add(bufferStack.pop());
                    }
                    else {
                        break;
                    }
                }
                bufferStack.push(token);
            }
            else if (token.isLeftBracket()) {
                bufferStack.push(token);
            }
            else if (token.isRightBracket()) {
                while (!bufferStack.empty() && !bufferStack.peek().isLeftBracket()) {
                    outputQueue.add(bufferStack.pop());
                }

                if (bufferStack.empty() || !bufferStack.peek().isLeftBracket()) {
                    throw new ArithmeticException("Invalid brackets");
                }

                bufferStack.pop();
            }
        }

        while (!bufferStack.empty()) {
            Token token = bufferStack.pop();
            if (token.isRightBracket() || token.isLeftBracket()) {
                throw new ArithmeticException("Invalid brackets");
            }

            outputQueue.add(token);
        }

        return outputQueue;
    }


    private double calculateForOperator(Token operator, Stack<Double> arguments) throws Exception {
        double resultNumber = 0;
        double operand1 = 0;
        double operand2 = 0;

        switch (operator.toString()) {
        case "+":
            operand2 = arguments.pop();
            operand1 = arguments.pop();
            resultNumber = operand1 + operand2;
            break;

        case "-":
            operand2 = arguments.pop();
            operand1 = arguments.pop();
            resultNumber = operand1 - operand2;
            break;

        case "*":
            operand2 = arguments.pop();
            operand1 = arguments.pop();
            resultNumber = operand1 * operand2;
            break;

        case "/":
            operand2 = arguments.pop();
            operand1 = arguments.pop();

            if (operand2 == 0.0) {
                throw new ArithmeticException("Zero division");
            }

            resultNumber = operand1 / operand2;
            break;

        default:
            throw new UnsupportedOperationException("Operator is not supported");
        }

        return resultNumber;
    }


    String expression;
}
