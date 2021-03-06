package course.java.util;


import course.java.exception.ConstraintViolation;
import course.java.exception.ConstraintViolationException;
import course.java.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookValidator {
    public void validate(Book book) throws ConstraintViolationException {
        List<ConstraintViolation> violations = new ArrayList<>();
        var titleLength = book.getTitle().trim().length();
        if(titleLength < 2 || titleLength > 50){
            violations.add(
                    new ConstraintViolation(book.getClass().getName(), "title", book.getTitle(),
                            "Book title length should be between 2 and 50 characters"));
        }
        if(book.getYear() > LocalDate.now().getYear()){
            violations.add(
                    new ConstraintViolation(book.getClass().getName(), "publishingDate", book.getYear(),
                            "Publishing date: '" + book.getYear() + "' should be in the past"));
        }
        if(book.getPrice() < 0){
            violations.add(
                    new ConstraintViolation(book.getClass().getName(), "price", book.getPrice(),
                            "Book price can not be negative"));
        }
        if(violations.size() > 0) {
            throw new ConstraintViolationException("Invalid book field", violations);
        }
    }
}
