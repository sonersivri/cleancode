package rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import rpn.operation.AddOperation;

public class AddOperationTest {

    static Stream<Arguments> checkMultiArgumentsMethodSource() {

        return Stream.of(Arguments.of(0,0,0),
                Arguments.of(0,1,1),
                Arguments.of(1,0,1),
                Arguments.of(3,2,5),
                Arguments.of(4,3,7),
                Arguments.of(-4,3,-1)
        );
    }
    @ParameterizedTest
    @MethodSource("checkMultiArgumentsMethodSource")
    void test(double first, double second, double expected) {
        Assertions.assertEquals(expected, new AddOperation().calculate(first, second));
    }
}
