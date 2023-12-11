package rpn;

import java.util.EmptyStackException;
import java.util.Stack;

public class RPNCalculator {
    public static double calculate(String... inputs) {
        if (inputs == null || inputs.length == 0) {
            throw new EmptyStackException();
        }
        if (inputs.length == 1) {
            return Double.parseDouble(inputs[0]);
        }

        Stack<Double> stack = new Stack<>();
        for (String input : inputs) {
            try {
                stack.push(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                double first = stack.pop();
                double second = stack.pop();
            }
        }

        return 0D;
    }
}
