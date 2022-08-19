package course.java.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        try(var from = new FileInputStream("test.db").getChannel();
        var to = new FileOutputStream("test.copy").getChannel()) {
            var start = System.currentTimeMillis();
            to.transferFrom(from, 0, from.size());
            var end = System.currentTimeMillis();
            System.out.printf("File of %d bytes was copied for %d ms", from.size(), end-start);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
