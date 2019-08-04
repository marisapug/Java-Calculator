import java.util.*;
import java.lang.*;

// TODO:
// Add Functionality for
// [] Negative integers
// [] Floating point numbers
// [] Compound expressions
// [] Order of operations

class Calculator {
    
    public static boolean isMatched(String exp) {
        Stack<Character> s = new Stack<Character>();
        for(int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                s.push(exp.charAt(i));
            } else if (exp.charAt(i) == ')') {
                try {
                    char popped = s.pop();
                    if (popped != '(') {
                        return false;
                    }
                } catch (EmptyStackException e) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static String removeWhiteSpace(String str) {
        String result = "";
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result += str.charAt(i);
            }
        }
        return result;
    }
    
    public static String getOperation(String str) {
        String exp = removeWhiteSpace(str);
        if (exp.contains("+")) {
            return "+";
        } else if (exp.contains("-")) {
            return "-";
        } else if (exp.contains("*")) {
            return "*";
        } else if (exp.contains("/")) {
            return "/";
        } else if (exp.contains("^")){
            return "^";
        } else {
            return "none";
        }
    }
    
    public static String[] getTokens(String exp, String op) {
        String[] tokens = new String[3];
        int operator = exp.indexOf(op);
        tokens[0] = op;
        tokens[1] = "";
        for(int i = 0; i<operator; i++) {
            tokens[1] += exp.charAt(i);
        }
        tokens[2] = "";
        for(int i = operator + 1; i < exp.length(); i++) {
            tokens[2] += exp.charAt(i);
        }
        return tokens;
    }
    
    public static int process(String expression) {
        String exp = removeWhiteSpace(expression);
        if (!isMatched(exp)) {
            System.out.println("Invalid expression");
            return -1;
        } else {
            String op = getOperation(exp);
            String[] tokens = getTokens(exp, op);
            int first = Integer.parseInt(tokens[1]);
            int second = Integer.parseInt(tokens[2]);
            if (tokens[0].equals("+")) {
                return first + second;
            } else if (tokens[0].equals("-")) {
                return first - second;
            } else if (tokens[0].equals("*")) {
                return first * second;
            } else if (tokens[0].equals("/")) {
                return first / second;
            } else if (tokens[0].equals("^")){
                return (int)Math.pow(first, second);
            }
            else {
                return -1;
            }
        }
    }
    
    public static void main(String[] var0) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Input your numerical expression or q to quit:");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                return;
            }
            System.out.println(process(input));
        }
    }
}
