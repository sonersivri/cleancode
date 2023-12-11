package rpn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rpn.operation.AddOperation;
import rpn.operation.DivisionOperation;
import rpn.operation.ModuloOperation;
import rpn.operation.MultiplyOperation;
import rpn.operation.PowerOperation;
import rpn.operation.SubtractionOperation;

public class RPNCalculator {

  public static final String NUMBER_OF_OPERANDS_CANNOT_BE_EQUAL_TO_NUMBERS = "Number of operands cannot be equal to numbers";
  public static final String DIVISION_BY_ZERO_IS_NOT_ALLOWED = "Division by zero is not allowed";
  public static final String MODULO_BY_ZERO_IS_NOT_ALLOWED = "Modulo by zero is not allowed";
  public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";
  public static final String COUNT_OF_OPERANDS_CANNOT_BE_GREATER_THAN_COUNT_OF_NUMBERS = "Count of operands cannot be greater than count of Numbers";

  private Map<String, RPNCalculationBinaryStrategy> strategyBinaryMap = new HashMap<>();
  private Map<String, RPNCalculationUnaryStrategy> strategyUnaryMap = new HashMap<>();

  public RPNCalculator() {
    strategyBinaryMap.put("+", new AddOperation());
    strategyBinaryMap.put("*", new MultiplyOperation());
    strategyBinaryMap.put("-", new SubtractionOperation());
    strategyBinaryMap.put("/", new DivisionOperation());
    strategyBinaryMap.put("%", new ModuloOperation());
    strategyBinaryMap.put("^", new PowerOperation());
  }

  public double calculate(String... inputs) throws InvalidInputException {
    try {
      validateInput(inputs);

      if (inputs.length == 1 && isValidNumber(inputs[0])) {
        return Double.parseDouble(inputs[0]);
      }

      Deque<Double> queue = new ArrayDeque<>();

      for (String token : inputs) {
        if (isValidNumber(token)) {
          queue.push(Double.parseDouble(token));
        } else if (strategyBinaryMap.containsKey(token)) {
          queue.push(strategyBinaryMap.get(token).calculate(queue.pop(), queue.pop()));
        } else if (strategyUnaryMap.containsKey(token)) {
          queue.push(strategyUnaryMap.get(token).calculate((queue.pop())));
        } else {
          throw new IllegalArgumentException(UNSUPPORTED_OPERATOR + token);
        }

      }
      return queue.pop();
    } catch (InvalidInputException e) {
      throw e;
    } catch (Exception e) {
      throw new InvalidInputException(e.getMessage());
    }

  }

  private boolean isValidNumber(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  void validateInput(String... inputs) throws InvalidInputException {
    List<String> numbers = new ArrayList<>();
    List<String> operands = new ArrayList<>();

    for (String token : inputs) {
      if (isValidNumber(token)) {
        numbers.add(token);
      } else {
        operands.add(token);
      }
    }

    if (numbers.size() == operands.size()) {
      throw new InvalidInputException(NUMBER_OF_OPERANDS_CANNOT_BE_EQUAL_TO_NUMBERS);
    }

    if (operands.size() > numbers.size()) {
      throw new InvalidInputException(COUNT_OF_OPERANDS_CANNOT_BE_GREATER_THAN_COUNT_OF_NUMBERS);
    }
  }

  public void factorial(int i) {
  }
}
