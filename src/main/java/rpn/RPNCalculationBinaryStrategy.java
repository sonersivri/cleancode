package rpn;

public interface RPNCalculationBinaryStrategy extends RPNCalculationStrategy {
    double calculate(double first, double second);

    @Override
    default CalculationType getType() {
        return CalculationType.BINARY;
    }
}
