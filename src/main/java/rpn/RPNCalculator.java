package rpn;

import java.util.EmptyStackException;

public class RPNCalculator {
    public static double calculate(String[] inputs) {
        if (inputs == null || inputs.length == 0) {
            throw new EmptyStackException();
        }
        return 0D;
    }
}
