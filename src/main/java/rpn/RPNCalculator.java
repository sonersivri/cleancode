package rpn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.EmptyStackException;

public class RPNCalculator {

  private RPNCalculator() {
    // not really required
  }

  public static double calculate(String... inputs) throws InvalidInputException {
    try {
      validateInput(inputs);
      return evaluateRPN(inputs);
    } catch (Exception e) {
      throw new InvalidInputException("Invalid Input: " + Arrays.toString(inputs));
    }
  }


  public static double evaluateRPN(String[] tokens) throws EmptyStackException {

    if (tokens.length == 1 && isValidNumber(tokens[0])) {
      return Double.parseDouble(tokens[0]);
    }

    Deque<Double> stack = new ArrayDeque<>();

    for (String token : tokens) {
      if (isValidNumber(token)) {
        stack.push(Double.parseDouble(token));
      } else {
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        double result = performCalculation(operand1, operand2, token);
        stack.push(result);
      }
    }
    return stack.pop();
  }

  private static boolean isValidNumber(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private static double performCalculation(double operand1, double operand2, String operator) {
    switch (operator) {
      case "+":
        return operand1 + operand2;
      case "-":
        return operand1 - operand2;
      case "*":
        return operand1 * operand2;
      case "/":
        return operand1 / operand2;
      default:
        throw new IllegalArgumentException("Unsupported operator: " + operator);
    }
  }

  static void validateInput(String... inputs) {
    if (inputs == null || inputs.length == 0) {
      throw new EmptyStackException();
    }
  }
}
