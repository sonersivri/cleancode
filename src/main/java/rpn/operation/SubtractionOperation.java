package rpn.operation;

import rpn.RPNCalculationBinaryStrategy;

public class SubtractionOperation implements RPNCalculationBinaryStrategy {

  @Override
  public double calculate(final double first, final double second) {
    return second - first;
  }
}
