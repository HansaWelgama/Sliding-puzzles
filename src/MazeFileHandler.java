import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeFileHandler {
    private final String mazeDirectory;

    public MazeFileHandler(String mazeDirectory) {
        this.mazeDirectory = mazeDirectory;
    }

    public List<Path> listMazeFiles() {
        List<Path> mazeFiles = new ArrayList<>();
        Path directory = Paths.get(mazeDirectory);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.txt")) {
            for (Path entry : stream) {
                mazeFiles.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mazeFiles;
    }

    public Path getRandomMazeFile() {
        List<Path> mazeFiles = listMazeFiles();
        if (!mazeFiles.isEmpty()) {
            Random random = new Random();
            return mazeFiles.get(random.nextInt(mazeFiles.size()));
        }
        return null;
    }
}