package shift.berketov.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader {
    private final List<String> userPaths;
    private final List<String> allLinesFromFiles = new ArrayList<>();

    public FileReader(List<String> userPaths) {
        this.userPaths = userPaths;
    }

    @Override
    public void read() {
        for (String currentPath : userPaths) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(currentPath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        allLinesFromFiles.add(line);
                    }
                }
                if (allLinesFromFiles.isEmpty()) {
                    System.out.println("Файл" + currentPath + " не имеет данных для фильтрации.");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Файл " + currentPath + " не найден.");
            }
        }
    }

    public List<String> getAllReadData() {
        return allLinesFromFiles;
    }
}


