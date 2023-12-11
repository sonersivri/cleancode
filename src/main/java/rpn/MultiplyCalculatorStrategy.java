package rpn;

public class MultiplyCalculatorStrategy implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return first * second;
    }
}
