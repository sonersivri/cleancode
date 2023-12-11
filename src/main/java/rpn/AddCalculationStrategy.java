package rpn;

public class AddCalculationStrategy implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return first + second;
    }
}
