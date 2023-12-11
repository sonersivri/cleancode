package rpn.operation;

import rpn.RPNCalculationBinaryStrategy;

public class AddOperation implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return first + second;
    }
}
