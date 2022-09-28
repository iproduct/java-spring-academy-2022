package course.qa.simple;


import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

@Slf4j
public class TestNGStack {
    Stack<Object> stack;
    String anElement = "an element";

    @BeforeMethod(groups = {"stack.empty"}, onlyForGroups = {"stack.empty"})
    void createNewStack() {
        log.info("Creating empty stack");
        stack = new Stack<>();
    }

    @Test(groups = {"stack.empty"})
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test(description = "throws EmptyStackException when popped", expectedExceptions = EmptyStackException.class, groups = {"stack.empty"})
    void throwsExceptionWhenPoped() {
        stack.pop();
    }

    // Stack after pushing an element
    @BeforeMethod(groups = {"stack.after.pushing"}, onlyForGroups = {"stack.after.pushing"})
    void createStackAndPush() {
        log.info("Creating stack with one element");
        stack = new Stack<>();
        stack.push(anElement);
    }

    @Test(description = "is no longer empty", groups= {"stack.after.pushing"})
    void isEmptyAfterPush() {
        assertFalse(stack.isEmpty());
    }

    @Test(description = "returns element when poped", groups= {"stack.after.pushing"})
    void returnsElementWhenPopedAfterPush() {
        assertEquals(anElement, stack.pop());
        assertTrue(stack.isEmpty(), "Stack should be empty after pop()");
    }

//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    @DisplayName("after pushing 3 elements")
//    class AfterPushing3Elems {
//        List<String> elements = List.of("one", "two", "three");
//
//        @BeforeAll
//        void createNewStack() {
//            stack = new Stack<>();
//            elements.forEach(stack::push);
//        }
//
//        @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
//        @DisplayName("returns element when poped ")
//        void returnsElementWhenPoped() {
//            assertThat(stack.pop()).isInstanceOf(String.class).isIn(elements);
//        }
//    }
}
