package rpn.operation;

import rpn.RPNCalculationUnaryStrategy;

public class FactorialOperation  implements RPNCalculationUnaryStrategy {
    @Override
    public double calculate(double value) {
        if (value < 0) {
            throw new RuntimeException("");
        }
        int result = 1;

        for (int i = 1; i <= value; i++) {
            result *= i;
        }

        return result;
    }
}
