package rpn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import rpn.operation.AddOperation;
import rpn.operation.DivisionOperation;
import rpn.operation.FactorialOperation;
import rpn.operation.ModuloOperation;
import rpn.operation.MultiplyOperation;
import rpn.operation.PowerOperation;
import rpn.operation.SubtractionOperation;

public class RPNCalculator {

  public static final String NUMBER_OF_OPERANDS_CANNOT_BE_EQUAL_TO_NUMBERS = "Number of operands cannot be equal to numbers";
  public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";

  private final Map<String, RPNCalculationStrategy> strategyMap = new HashMap<>();
  private RPNCalculationStrategy strategy;

  public void setStrategy(RPNCalculationStrategy strategy) {
    this.strategy = strategy;
  }


  public RPNCalculator() {
    strategyMap.put("+", new AddOperation());
    strategyMap.put("*", new MultiplyOperation());
    strategyMap.put("-", new SubtractionOperation());
    strategyMap.put("/", new DivisionOperation());
    strategyMap.put("%", new ModuloOperation());
    strategyMap.put("^", new PowerOperation());
    strategyMap.put("!", new FactorialOperation());
  }

  public double calculate(String... inputs) throws InvalidInputException {
    try {

      if (inputs.length == 1 && isValidNumber(inputs[0])) {
        return Double.parseDouble(inputs[0]);
      }

      Deque<Double> queue = new ArrayDeque<>();

      for (String token : inputs) {

        if (isValidNumber(token)) {
          queue.push(Double.parseDouble(token));

        } else if (!strategyMap.containsKey(token)){
          throw new IllegalArgumentException(UNSUPPORTED_OPERATOR + token);

        } else {
          this.setStrategy(strategyMap.get(token));
          queue.push(this.calculate(queue));
        }

      }

      if (queue.size() > 1) {
        throw new InvalidInputException("All inputs can not be processed");
      }

      return queue.pop();

    } catch (InvalidInputException | IllegalArgumentException e) {
      throw e;
    }  catch (Exception e) {
      throw new InvalidInputException(e);
    }

  }

  private double calculate(Deque<Double> queue) throws InvalidInputException {

    if (CalculationType.BINARY == strategy.getType()) {
      return ((RPNCalculationBinaryStrategy)strategy).calculate(queue.pop(), queue.pop());
    }

    return ((RPNCalculationUnaryStrategy)strategy).calculate(queue.pop());
  }

  private boolean isValidNumber(String str) {

    try {
      Double.parseDouble(str);
      return true;

    } catch (NumberFormatException e) {
      return false;
    }

  }

}
