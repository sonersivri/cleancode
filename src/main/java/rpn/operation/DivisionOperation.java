package rpn.operation;

import rpn.RPNCalculationBinaryStrategy;

public class DivisionOperation implements RPNCalculationBinaryStrategy {

  public static final String DIVISION_BY_ZERO_IS_NOT_ALLOWED = "Division by zero is not allowed";
  @Override
  public double calculate(final double divisor, final double dividend) {
    if (divisor == 0) {
      throw new ArithmeticException(DIVISION_BY_ZERO_IS_NOT_ALLOWED);
    }
    return dividend / divisor;
  }
}
