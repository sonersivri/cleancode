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

  // "4"
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
}
