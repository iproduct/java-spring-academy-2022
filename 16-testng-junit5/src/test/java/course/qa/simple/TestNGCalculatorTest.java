package course.qa.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.*;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.testng.Assert.assertEquals;

@Slf4j
public class TestNGCalculatorTest {
    private Calculator calculator;

    @BeforeClass(alwaysRun = true)
    public void init() {
        log.info("Before all tests in a class");
    }

    @AfterClass(alwaysRun = true)
    public void destroy() {
        log.info("After all tests in a class");
    }


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        log.info("Before test method");
        calculator = new Calculator();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        log.info("After test method");
        calculator = null;
    }

    @Test(groups = {"simple", "success", "fast"})
    public void givenXandY_whenAdd_thenResultXplusY() {
        log.info("Testing add()");
        //test (action)
        int actual = calculator.add(5, 8);

        //verify (assert, post-condition)
        assertEquals(actual, 13, "Regular addition should work");
    }

    @Test(groups = {"simple", "success", "fast"})
    public void givenXandY_whenMultiply_thenResultXproductY() {
        log.info("Testing multiply()");
        //test (action)
        int actual = calculator.multiply(5, 8);

        //verify (assert, post-condition)
        assertEquals(actual, 40, "Regular multiplication should work");
    }

    @Test(groups = {"simple", "success", "fast"})
    public void givenXandY_whenDivide_thenResultXdivY() {
        log.info("Testing divide()");
        //test (action)
        int actual = calculator.divide(42, 5);

        //verify (assert, post-condition)
        assertEquals(actual, 8, "Regular division should work");

    }

    @Test(expectedExceptions = ArithmeticException.class, expectedExceptionsMessageRegExp = "/ by zero", groups = {"simple", "failure", "fast"})
    public void givenXandZeroDivider_whenDivide_thenArtithmeticExceptionThrown() {
        log.info("Testing divide by zero()");
        calculator.divide(42, 0);
    }

    @Test(groups = {"simple", "success", "slow"})
    void testGenerateBigPrime() {
        assertEquals(calculator.generateNextPrime(BigInteger.valueOf(1000000000)), BigInteger.valueOf(1000000007));
    }
}
