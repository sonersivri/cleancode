package rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

public class RPNCalculatorTest {
    @Test
    void should_throw_exception_when_empty_input() {

        Assertions.assertThrows(EmptyStackException.class, () -> {
            RPNCalculator.calculate(new String[]{});
        });

    }

    @Test
    void should_throw_exception_when_null_input() {

        Assertions.assertThrows(EmptyStackException.class, () -> {
            RPNCalculator.calculate(null);
        });

    }
}
