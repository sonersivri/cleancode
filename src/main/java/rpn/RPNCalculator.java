package rpn;

import java.util.EmptyStackException;

public class RPNCalculator {
    public static double calculate(String... inputs) {
        if (inputs == null || inputs.length == 0) {
            throw new EmptyStackException();
        }
        if (inputs.length == 1) {
            return Double.parseDouble(inputs[0]);
        }
        return 0D;
    }
}
