package rpn.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ModuloOperationTest {

    static Stream<Arguments> checkMultiArgumentsMethodSource() {

        return Stream.of(
                Arguments.of(1,0, 0),
                Arguments.of(2,3,3%2),
                Arguments.of(2,4,4%2),
                Arguments.of(-4,-2,-2%4),
                Arguments.of(-2,-3,-3%-2),
                Arguments.of(4,-2,-2%4)
        );
    }
    @ParameterizedTest
    @MethodSource("checkMultiArgumentsMethodSource")
    void test(double divisor, double dividend, double expected) {
        Assertions.assertEquals(expected, new ModuloOperation().calculate(divisor, dividend));
    }
}
