import java.util.ArrayList;
import java.util.Stack;


class Parser {
    public Parser(String expression) {
        this.expression = expression;
    }


    public double calculate() {
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


    ArrayList<Token> backPolishNotation(ArrayList<Token> arrayList) {
        ArrayList<Token> outputQueue = new ArrayList<Token>();
        Stack<Token> bufferStack = new Stack<Token>();

        for (Token token : arrayList) {
            if (token.isNumber()) {
                outputQueue.add(token);
            }
            else if (token.isFunction()) {
                // ...
            }
            else if (token.isSeparator()) {
                // ...
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
                while (!bufferStack.peek().isLeftBracket()) {
                    outputQueue.add(bufferStack.pop());
                }
                // if bifferStack.peek() is not ) - raise error
                bufferStack.pop();

                // Functions
            }
        }
        if (!bufferStack.empty() && bufferStack.peek().isOperator()) {
            if (bufferStack.peek().isLeftBracket()
                || bufferStack.peek().isRightBracket()) {
                // Error
            }
        }
        while (!bufferStack.empty()) {
            outputQueue.add(bufferStack.pop());
        }

        return outputQueue;
    }


    public double calculateForOperator(Token operator, Stack<Double> arguments) {
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
            resultNumber = operand1 / operand2;
            break;

        default:
            System.out.println("Not operator!");
        }

        return resultNumber;
    }


    String expression;
}
