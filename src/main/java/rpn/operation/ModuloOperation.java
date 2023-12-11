package rpn.operation;

import rpn.RPNCalculationBinaryStrategy;

public class ModuloOperation implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return second % first;
    }
}
