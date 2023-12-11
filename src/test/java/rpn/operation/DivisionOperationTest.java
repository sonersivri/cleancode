package rpn.operation;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DivisionOperationTest {
  static Stream<Arguments> checkMultiArgumentsMethodSource() {

    return Stream.of(
        Arguments.of(1,0,0),
        Arguments.of(2,3,1.5),
        Arguments.of(2,4,2),
        Arguments.of(-4,-2,0.5),
        Arguments.of(-2,-3,1.5),
        Arguments.of(4,-2,-0.5)
    );
  }
  @ParameterizedTest
  @MethodSource("checkMultiArgumentsMethodSource")
  void test(double divisor, double dividend, double expected) {
    Assertions.assertEquals(expected, new DivisionOperation().calculate(divisor, dividend));
  }

  @Test
  void should_throw_exception_when_divisor_is_zero() {
    final ArithmeticException arithmeticException = Assertions.assertThrows(
        ArithmeticException.class, () -> new DivisionOperation().calculate(0, 1));
    Assertions.assertEquals("Division by zero is not allowed", arithmeticException.getMessage());
  }
}
