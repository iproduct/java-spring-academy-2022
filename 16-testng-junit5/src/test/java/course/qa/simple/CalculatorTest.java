package course.qa.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
@Tag("fast")
public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    public static void init() {
        log.info("Before all tests in a class");
    }

    @AfterAll
    public static void destroy() {
        log.info("After all tests in a class");
    }

    @BeforeEach
    public void setUp() throws Exception {
        log.info("Before test method");
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() throws Exception {
        log.info("After test method");
        calculator = null;
    }

    @Test
    public void givenXandY_whenAdd_thenResultXplusY() {
        log.info("Testing add()");
        //test (action)
        int actual = calculator.add(5, 8);

        //verify (assert, post-condition)
        assertEquals(13, actual, "Regular addition should work");
    }

    @Test
    public void givenXandY_whenMultiply_thenResultXproductY() {
        log.info("Testing multiply()");
        //test (action)
        int actual = calculator.multiply(5, 8);

        //verify (assert, post-condition)
        assertEquals(40, actual, "Regular multiplication should work");
    }

    @Test
    public void givenXandY_whenDivide_thenResultXdivY() {
        log.info("Testing divide()");
        //test (action)
        int actual = calculator.divide(42, 5);

        //verify (assert, post-condition)
        assertEquals(8, actual, "Regular division should work");

    }

    @Test
    public void givenXandZeroDivider_whenDivide_thenArtithmeticExceptionThrown() {
        log.info("Testing divide by zero()");
        //test (action)
        var exception  = assertThrows(ArithmeticException.class, () -> calculator.divide(42, 0));
        assertEquals("/ by zero", exception.getMessage(), "Message should be '/ by zero'");
    }
}
