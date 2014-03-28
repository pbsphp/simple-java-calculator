import java.util.ArrayList;
import java.util.Stack;


class Parser {
    public Parser(String expression) {
        this.expression = expression;
    }


    public double calculate() {
        ArrayList<Token> tokensList = getTokensAsList();
        Stack<Token> tokensStack = backPolishNotation(tokensList);


        // TODO: maybe Stack<double> ?
        Stack<Token> operandsStack = new Stack<Token>();

        while (!tokensStack.empty()) {
            Token operand = tokensStack.pop();

            if (operand.isNumber()) {
                operandsStack.push(operand);
            }
            else if (operand.isOperator()) {
                Token result = calculateForOperator(operand, operandsStack);
                operandsStack.push(result);
            }
        }


        String resultString = operandsStack.pop().toString();

        return Double.parseDouble(resultString);
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


    Stack<Token> backPolishNotation(ArrayList<Token> arrayList) {
        Stack<Token> outputStack = new Stack<Token>();
        Stack<Token> bufferStack = new Stack<Token>();

        for (Token token : arrayList) {
            if (token.isNumber()) {
                outputStack.push(token);
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
                        outputStack.push(bufferStack.pop());
                    }
                    else {
                        break;
                    }
                }
                bufferStack.push(token);
            }
            else if (token.isLeftBracket()) {
                // ...
            }
            else if (token.isRightBracket()) {
                // ...
            }
        }
        if (!bufferStack.empty() && bufferStack.peek().isOperator()) {
            if (bufferStack.peek().isLeftBracket()
                || bufferStack.peek().isRightBracket()) {
                // Error
            }
        }
        while (!bufferStack.empty()) {
            outputStack.push(bufferStack.pop());
        }


        // Reverse stack

        while (!outputStack.empty()) {
            bufferStack.push(outputStack.pop());
        }

        // return outputStack;
        return bufferStack;
    }


    public Token calculateForOperator(Token operator, Stack<Token> arguments) {
        int resultNumber = 0;
        int operand1 = 0;
        int operand2 = 0;

        switch (operator.toString()) {
        case "+":
            operand2 = Integer.parseInt(arguments.pop().toString());
            operand1 = Integer.parseInt(arguments.pop().toString());
            resultNumber = operand1 + operand2;
            break;

        case "-":
            operand2 = Integer.parseInt(arguments.pop().toString());
            operand1 = Integer.parseInt(arguments.pop().toString());
            resultNumber = operand1 - operand2;
            break;

        case "*":
            operand2 = Integer.parseInt(arguments.pop().toString());
            operand1 = Integer.parseInt(arguments.pop().toString());
            resultNumber = operand1 * operand2;
            break;

        case "/":
            operand2 = Integer.parseInt(arguments.pop().toString());
            operand1 = Integer.parseInt(arguments.pop().toString());
            resultNumber = operand1 / operand2;
            break;

        default:
            System.out.println("Not operator!");
        }

        String resultString = Integer.toString(resultNumber);
        return new Token(resultString);
    }



    String expression;
}
