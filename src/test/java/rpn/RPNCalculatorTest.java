package rpn;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class RPNCalculatorTest {

  static Stream<Arguments> checkMultiArgumentsMethodSource() {

    return Stream.of(Arguments.of("1, 2, 3, +, -", -4.0),
        Arguments.of("6, 2, *, 3, /", 4),
        Arguments.of("2, 3, ^, 4, 5, +, +", 17),
        Arguments.of("3, !, 4, 5, *, +", 26),
        Arguments.of("12, 3, /, !", 24),
        Arguments.of("5, 1, 2, +, 4, *, +, 3, -", 14));
  }

  @Test
  void should_throw_exception_when_empty_input() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RPNCalculator().calculate("", "");
    });
  }

  @Test
  void should_throw_exception_when_null_input() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RPNCalculator().calculate((String) null);
    });
  }

  @Test
  void should_return_input_when_input_is_just_one_number() throws InvalidInputException {
    Assertions.assertEquals(4.5D, new RPNCalculator().calculate("4.5"));
  }

  @Test
  void should_throw_exception_when_invalid_input_given() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RPNCalculator().calculate("2", "+", "2");
    });
  }

  @Test
  void should_calculate_rpn_given_valid_inputs() throws InvalidInputException {
    Assertions.assertEquals(-4.0, new RPNCalculator().calculate("1", "2", "3", "+", "-"));
  }

  @Test
  void should_calculate_rpn_given_valid_inputs34() throws InvalidInputException {
    Assertions.assertEquals(-4.0, new RPNCalculator().calculate("1", "2", "3", "+", "-"));
  }

  @Test
  void should_throw_exception_when_dividing_by_zero() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
              new RPNCalculator().calculate("0", "0", "0", "/", "-");
        });

    Assertions.assertEquals(RPNCalculator.DIVISION_BY_ZERO_IS_NOT_ALLOWED,
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_modulo_by_zero() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
              new RPNCalculator().calculate("0", "0", "0", "%", "-");
        });

    Assertions.assertEquals(RPNCalculator.MODULO_BY_ZERO_IS_NOT_ALLOWED,
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_unsupported_operator_in_input() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
              new RPNCalculator().calculate("0", "0", "0", "_", "-");
        });

    Assertions.assertTrue(
        invalidInputException.getMessage().startsWith(RPNCalculator.UNSUPPORTED_OPERATOR));
  }

  @Test
  void should_calculate_addition() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculator().calculate("2","2", "+"));
  }

  @Test
  void should_calculate_addition_with_signed_values() throws InvalidInputException {
    Assertions.assertEquals(0.0D, new RPNCalculator().calculate("-2","2", "+"));
  }

  @Test
  void should_calculate_addition_with_signed_values2() throws InvalidInputException {
    Assertions.assertEquals(0.0D, new RPNCalculator().calculate("2","-2", "+"));
  }

  @Test
  void should_calculate_addition_with_signed_values3() throws InvalidInputException {
    Assertions.assertEquals(1.0D, new RPNCalculator().calculate("-2","3", "+"));
  }

  @Test
  void should_calculate_addition_with_signed_values4() throws InvalidInputException {
    Assertions.assertEquals(-2.0D, new RPNCalculator().calculate("-5","3", "+"));
  }

  @Test
  void should_calculate_subtraction() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculator().calculate("6","2", "-"));
  }

  @Test
  void should_calculate_subtraction_with_signed_values() throws InvalidInputException {
    Assertions.assertEquals(-4.0D, new RPNCalculator().calculate("-2","2", "-"));
  }

  @Test
  void should_calculate_subtraction_with_signed_values2() throws InvalidInputException {
    Assertions.assertEquals(-7.0D, new RPNCalculator().calculate("-5","2", "-"));
  }

  @Test
  void should_calculate_division() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculator().calculate("8","2", "/"));
  }

  @Test
  void should_calculate_multiplication() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculator().calculate("2","2", "*"));
  }

  @Test
  void should_calculate_rpn_given_valid_inputs2() {
    InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
              new RPNCalculator().calculate("1", "2", "3", "+", "-", "%");
        });

    Assertions.assertEquals(RPNCalculator.NUMBER_OF_OPERANDS_CANNOT_BE_EQUAL_TO_NUMBERS,
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_count_of_operator_is_greater_than_numbers() {
    InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> {
              new RPNCalculator().calculate("1", "2", "-", "+", "-");
        });

    Assertions.assertEquals(RPNCalculator.COUNT_OF_OPERANDS_CANNOT_BE_GREATER_THAN_COUNT_OF_NUMBERS,
        invalidInputException.getMessage());
  }

  @ParameterizedTest
  @MethodSource("checkMultiArgumentsMethodSource")
  void should_correctly_evaluate_rpn_values(String input, double expected)
      throws InvalidInputException {
    Assertions.assertEquals(expected, new RPNCalculator().calculate(input.split(", ")));
  }

  @ParameterizedTest
  @ValueSource(ints = {0,1,})
  void test_factorial() {
    new RPNCalculator().factorial(0);
  }
}
