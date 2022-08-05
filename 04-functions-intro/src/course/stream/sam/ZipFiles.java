package course.stream.sam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {
        private List<String> fileList;
        private static final String NOTES_ZIP = "D:\\Notes.zip";
        private static final String FOLDER_TO_ZIP = "D:\\Notes";

        public static void main(String[] args) throws IOException {
            zip(FOLDER_TO_ZIP,NOTES_ZIP);
        }

        public static void zip(final String sourcNoteseDirPath, final String zipFilePath) throws IOException {
            Path zipFile = Files.createFile(Paths.get(zipFilePath));

            Path sourceDirPath = Paths.get(sourcNoteseDirPath);
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
                 Stream<Path> paths = Files.walk(sourceDirPath)) {
                paths
                        .filter(path -> !Files.isDirectory(path))
                        .forEach(path -> {
                            ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString());
                            try {
                                zipOutputStream.putNextEntry(zipEntry);
                                Files.copy(path, zipOutputStream);
                                zipOutputStream.closeEntry();
                            } catch (IOException e) {
                                System.err.println(e);
                            }
                        });
            }

            System.out.println("Zip is created at : "+zipFile);
        }
    }

