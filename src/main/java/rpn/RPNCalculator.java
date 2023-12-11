package rpn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RPNCalculator {

  public static final String NUMBER_OF_OPERANDS_CANNOT_BE_EQUAL_TO_NUMBERS = "Number of operands cannot be equal to numbers";
  public static final String DIVISION_BY_ZERO_IS_NOT_ALLOWED = "Division by zero is not allowed";
  public static final String MODULO_BY_ZERO_IS_NOT_ALLOWED = "Modulo by zero is not allowed";
  public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";
  public static final String COUNT_OF_OPERANDS_CANNOT_BE_GREATER_THAN_COUNT_OF_NUMBERS = "Count of operands cannot be greater than count of Numbers";

  private Map<String, RPNCalculationBinaryStrategy> strategyBinaryMap = new HashMap<>();
  private Map<String, RPNCalculationUnaryStrategy> strategyUnaryMap = new HashMap<>();

  public RPNCalculator() {
    strategyBinaryMap.put("+", new AddCalculationStrategy());
    strategyBinaryMap.put("*", new MultiplyCalculatorStrategy());
  }
  public double calculate(String... inputs) throws InvalidInputException {
    try {
      return evaluateRPN(inputs);
    } catch (Exception e) {
      throw new InvalidInputException(e.getMessage(), e);
    }
  }


  public double evaluateRPN(String[] inputs) throws InvalidInputException {

    validateInput(inputs);

    if (inputs.length == 1 && isValidNumber(inputs[0])) {
      return Double.parseDouble(inputs[0]);
    }

    Deque<Double> queue = new ArrayDeque<>();

    for (String token : inputs) {
      if (isValidNumber(token)) {
        queue.push(Double.parseDouble(token));
      } else  if (strategyBinaryMap.containsKey(token)) {
        queue.push(strategyBinaryMap.get(token).calculate(queue.pop(), queue.pop()));
      } else  if (strategyUnaryMap.containsKey(token)) {
        queue.push(strategyUnaryMap.get(token).calculate((queue.pop()));
      }
    }
    return queue.pop();
  }

  private boolean isValidNumber(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private double performCalculation(double operand1, double operand2, String operator) {
    strategyBinaryMap.get(operator).calculate(operand1, operand2);
    switch (operator) {
      case "+":
        return operand1 + operand2;
      case "-":
        return operand1 - operand2;
      case "*":
        return operand1 * operand2;
      case "/":
        if (operand2 == 0) {
          throw new ArithmeticException(DIVISION_BY_ZERO_IS_NOT_ALLOWED);
        }
        return operand1 / operand2;
      case "%":
        if (operand2 == 0) {
          throw new ArithmeticException(MODULO_BY_ZERO_IS_NOT_ALLOWED);
        }
        return operand1 % operand2;
      case "^":
        return Math.pow(operand1, operand2);
      case "!":
         return 0.0;
      default:
        throw new IllegalArgumentException(UNSUPPORTED_OPERATOR + operator);
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
