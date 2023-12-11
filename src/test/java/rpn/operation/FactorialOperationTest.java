package rpn.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FactorialOperationTest {

    static Stream<Arguments> checkMultiArgumentsMethodSource() {

        return Stream.of(
                Arguments.of(1,0, Math.pow(0,1)),
                Arguments.of(2,3,Math.pow(3,2)),
                Arguments.of(2,4,Math.pow(4,2)),
                Arguments.of(-4,-2,Math.pow(-2,-4)),
                Arguments.of(-2,-3,Math.pow(-3,-2)),
                Arguments.of(4,-2,Math.pow(-2,4))
        );
    }
    @ParameterizedTest
    @MethodSource("checkMultiArgumentsMethodSource")
    void test(double exponent, double base, double expected) {
        Assertions.assertEquals(expected, new PowerOperation().calculate(exponent, base));
    }
}
