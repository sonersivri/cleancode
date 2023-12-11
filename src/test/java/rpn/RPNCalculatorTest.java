package rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RPNCalculatorTest {

  @Test
  void should_throw_exception_when_empty_input() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      RPNCalculator.calculate("", "");
    });
  }

  @Test
  void should_throw_exception_when_null_input() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      RPNCalculator.calculate((String) null);
    });
  }

  @Test
  void should_return_input_when_input_is_just_one_number() throws InvalidInputException {
    Assertions.assertEquals(4.5D, RPNCalculator.calculate("4.5"));
  }

  @Test
  void should_throw_exception_when_invalid_input_given() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      RPNCalculator.calculate("2", "+", "2");
    });
  }

  @Test
  void should_calculate_rpn_given_valid_inputs() throws InvalidInputException {
    Assertions.assertEquals(-4.0, RPNCalculator.calculate("1", "2", "3", "+", "-"));
  }


  @Test
  void should_throw_exception_when_dividing_by_zero() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
          RPNCalculator.calculate("0", "0", "0", "/", "-");
        });

    Assertions.assertEquals(RPNCalculator.DIVISION_BY_ZERO_IS_NOT_ALLOWED,
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_modulo_by_zero() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
          RPNCalculator.calculate("0", "0", "0", "%", "-");
        });

    Assertions.assertEquals(RPNCalculator.MODULO_BY_ZERO_IS_NOT_ALLOWED,
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_unsupported_operator_in_input() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
          RPNCalculator.calculate("0", "0", "0", "_", "-");
        });

    Assertions.assertTrue(
        invalidInputException.getMessage().startsWith(RPNCalculator.UNSUPPORTED_OPERATOR));
  }

  @Test
  void should_calculate_addition() throws InvalidInputException {
    Assertions.assertEquals(4.0D, RPNCalculator.calculate("2","2", "+"));
  }

  @Test
  void should_calculate_subtraction() throws InvalidInputException {
    Assertions.assertEquals(4.0D, RPNCalculator.calculate("6","2", "-"));
  }

  @Test
  void should_calculate_division() throws InvalidInputException {
    Assertions.assertEquals(4.0D, RPNCalculator.calculate("8","2", "/"));
  }

  @Test
  void should_calculate_multiplication() throws InvalidInputException {
    Assertions.assertEquals(4.0D, RPNCalculator.calculate("2","2", "*"));
  }

  @Test
  void should_calculate_rpn_given_valid_inputs2() {
    InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
          RPNCalculator.calculate("1", "2", "3", "+", "-", "%");
        });

    Assertions.assertEquals(RPNCalculator.NUMBER_OF_OPERANDS_CANNOT_BE_EQUAL_TO_NUMBERS,
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_count_of_operator_is_greater_than_numbers() {
    InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
          RPNCalculator.calculate("1", "2", "-", "+", "-");
        });

    Assertions.assertEquals(RPNCalculator.COUNT_OF_OPERANDS_CANNOT_BE_GREATER_THAN_COUNT_OF_NUMBERS,
        invalidInputException.getMessage());
  }
}
