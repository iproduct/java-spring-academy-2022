package course.java.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CharacterInput {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("src/main/java/course/java/io/CharacterInput.java")) {
            int c;
            while ((c = fr.read()) != -1) {
                System.out.write(c);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
