package rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import rpn.operation.MultiplyOperation;

public class MultiplyOperationTest {

    static Stream<Arguments> checkMultiArgumentsMethodSource() {

        return Stream.of(Arguments.of(0,0,0),
                Arguments.of(0,1,0),
                Arguments.of(1,0,0),
                Arguments.of(3,2,6),
                Arguments.of(4,3,12),
                Arguments.of(-4,3,-12)
        );
    }
    @ParameterizedTest
    @MethodSource("checkMultiArgumentsMethodSource")
    void test(double first, double second, double expected) {
        Assertions.assertEquals(expected, new MultiplyOperation().calculate(first, second));
    }
}
