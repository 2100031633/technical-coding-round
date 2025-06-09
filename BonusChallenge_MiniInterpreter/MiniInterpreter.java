//MiniInterpreter.java
import java.util.*;

public class MiniInterpreter {
    static Map<String, Integer> variables = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter commands (type 'exit' to stop):");

        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            if (input.startsWith("let ")) {
                handleLet(input);
            } else if (input.startsWith("if ")) {
                handleIf(input);
            } else {
                System.out.println("Invalid command.");
            }
        }

        sc.close();
    }

    // Handles: let x = 10 or let y = x + 5
    private static void handleLet(String input) {
        input = input.substring(4).trim(); // remove "let "
        String[] parts = input.split("=", 2);
        if (parts.length != 2) {
            System.out.println("Invalid let statement.");
            return;
        }

        String varName = parts[0].trim();
        String expr = parts[1].trim();

        try {
            int value = evaluateExpression(expr);
            variables.put(varName, value);
            System.out.println("Assigned " + varName + " = " + value);
        } catch (Exception e) {
            System.out.println("Error evaluating expression.");
        }
    }

    // Handles: if x > 5 then print YES else print NO
    private static void handleIf(String input) {
        try {
            input = input.substring(3); // remove "if "
            String[] parts = input.split("then print|else print");

            String condition = parts[0].trim();
            String thenPart = input.split("then print")[1].split("else print")[0].trim();
            String elsePart = input.split("else print")[1].trim();

            if (evaluateCondition(condition)) {
                System.out.println(thenPart);
            } else {
                System.out.println(elsePart);
            }
        } catch (Exception e) {
            System.out.println("Invalid if statement.");
        }
    }

    // Evaluates simple conditions like x > 5, x == y, etc.
    private static boolean evaluateCondition(String condition) {
        if (condition.contains("==")) {
            String[] c = condition.split("==");
            return evaluateExpression(c[0].trim()) == evaluateExpression(c[1].trim());
        } else if (condition.contains(">")) {
            String[] c = condition.split(">");
            return evaluateExpression(c[0].trim()) > evaluateExpression(c[1].trim());
        } else if (condition.contains("<")) {
            String[] c = condition.split("<");
            return evaluateExpression(c[0].trim()) < evaluateExpression(c[1].trim());
        }
        return false;
    }

    // Supports expressions like x, 5, x+3
    private static int evaluateExpression(String expr) {
        expr = expr.replaceAll("\\s+", "");
        if (expr.contains("+")) {
            String[] parts = expr.split("\\+");
            return getValue(parts[0]) + getValue(parts[1]);
        } else if (expr.contains("-")) {
            String[] parts = expr.split("-");
            return getValue(parts[0]) - getValue(parts[1]);
        } else {
            return getValue(expr);
        }
    }

    // Converts variable to value or parses int
    private static int getValue(String token) {
        if (variables.containsKey(token)) {
            return variables.get(token);
        }
        return Integer.parseInt(token);
    }
}
