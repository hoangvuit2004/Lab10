import java.util.Arrays;
import java.util.Stack;

public class MyLIFO_App {
    public static <E> void reserve(E[] array) {
        if(array == null || array.length == 0){
            return;
        }
        array = Arrays.copyOf(array, array.length*2);
    }
    public static boolean isCorrect(String input) {
       if(input == null || input.length() == 0 ){
           return false;
       }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length() ; i++) {
            if(input.charAt(i) == '(' ||input.charAt(i) == '{' || input.charAt(i) == '['){
                stack.push(input.charAt(i));
            }else if(input.charAt(i) == ')' || input.charAt(i) == ']' || input.charAt(i) == '}' ){
                if(stack.isEmpty()){
                    return  false;
                }
                char top = stack.pop();
                if((input.charAt(i) == ')' && top != '(') || (input.charAt(i) == '}' && top != '{') || (input.charAt(i) == ']' && top != '[') ){
                    return  false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static int evaluateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }

        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        String temp = "";

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                temp += c;
                continue;
            }

            if (!temp.isEmpty()) {
                int a = Integer.parseInt(temp);
                operandStack.push(a);
                temp = "";
            }

            if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.pop(); // Pop '('
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && hasPrecedence(c, operatorStack.peek())) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(c);
            }
        }
        if (!temp.isEmpty()) {
            int lastOperand = Integer.parseInt(temp);
            operandStack.push(lastOperand);
        }


        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 == '+' || op2 == '-') && (op1 == '*' || op1 == '/');
    }

    private static void performOperation(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();

        switch (operator) {
            case '+':
                operandStack.push(operand1 + operand2);
                break;
            case '-':
                operandStack.push(operand1 - operand2);
                break;
            case '*':
                operandStack.push(operand1 * operand2);
                break;
            case '/':
                operandStack.push(operand1 / operand2);
                break;
        }
    }






    public static void main(String[] args) {
        String input = "511+(54*(3+2))";
                System.out.println(evaluateExpression(input));

    }
}
