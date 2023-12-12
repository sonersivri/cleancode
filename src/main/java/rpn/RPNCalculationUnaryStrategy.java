package rpn;

public interface RPNCalculationUnaryStrategy extends RPNCalculationStrategy {
    double calculate(double value);

    @Override
    default CalculationType getType() {
        return CalculationType.UNARY;
    }
}
