package rpn.operation;

import rpn.RPNCalculationBinaryStrategy;

public class MultiplyOperation implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return second * first;
    }
}
