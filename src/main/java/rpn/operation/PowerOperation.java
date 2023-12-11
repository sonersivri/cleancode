package rpn.operation;

import rpn.RPNCalculationBinaryStrategy;

public class PowerOperation implements RPNCalculationBinaryStrategy {
    public double calculate(double exponent, double base) {
        return Math.pow(base, exponent);
    }
}
