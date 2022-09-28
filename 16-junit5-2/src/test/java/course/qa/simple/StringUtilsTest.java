package course.qa.simple;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Tag("fast")
@Tag("service")
class StringUtilsTest {

    @ParameterizedTest(name = "#{index}: Given {0} isPalindrome -> true")
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    void given_radar_whenIsPalindrome_thenTrue(String candidate) {
        assertTrue(StringUtils.isPalindrome(candidate));
    }

    @ParameterizedTest(name = "#{index}: Given {0} isPalindrome -> {1}")
    @MethodSource("stringAndBooleanResultProvider")
    void givenMethodSource_IsPalindrome(String candidate, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.isPalindrome(candidate));
    }

    @ParameterizedTest(name = "#{index}: Given {0} isPalindrome -> {1}")
    @CsvSource({
            "racecar, true",
            "radar, true",
            "able was I ere I saw elba, true",
            "byl hlyb, false"
    })
    void givenCsvSource_IsPalindrome(String candidate, String expectedResult) {
        assertEquals(Boolean.valueOf(expectedResult), StringUtils.isPalindrome(candidate));
    }

    @ParameterizedTest(name = "#{index}: Given {0} isPalindrome -> {1}")
    @CsvFileSource(resources = "/palindroms.csv", numLinesToSkip = 1)
    void givenCsvFileSource_IsPalindrome(String candidate, String expectedResult) {
        assertEquals(Boolean.valueOf(expectedResult), StringUtils.isPalindrome(candidate));
    }

    static Stream<Arguments> stringAndBooleanResultProvider() {
        return Stream.of(
                arguments("racecar", true),
                arguments("radar", true),
                arguments("able was I ere I saw elba", true),
                arguments("byl hlyb", false)
        );
    }
}
