package rpn.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FactorialOperationTest {

  static Stream<Arguments> checkMultiArgumentsMethodSource() {

    return Stream.of(
        Arguments.of(0,1),
        Arguments.of(1,1),
        Arguments.of(2,2),
        Arguments.of(3,6)
    );
  }
  @ParameterizedTest
  @MethodSource("checkMultiArgumentsMethodSource")
  void test(double first, double expected) {
    Assertions.assertEquals(expected, new FactorialOperation().calculate(first));
  }
}