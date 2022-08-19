package course.java.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedCharacterInput {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(
                new FileReader("src/main/java/course/java/io/CharacterInput.java"))) {
            String s;
            int i = 0;
            while ((s = br.readLine()) != null) {
                System.out.printf("%3d: %s%n", ++i, s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
