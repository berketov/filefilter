package shift.berketov.reader;

import shift.berketov.settings.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader implements Reader {
    private final Settings settings;
    private final List<String> allLinesFromFiles = new ArrayList<>();

    public FileReader(Settings config) {
        this.settings = config;
    }

    @Override
    public void read() {
        for (String currentPath : settings.getPaths()) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(currentPath))) {
                String line;
                boolean emptyFileFlag = false;
                while ((line = reader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        allLinesFromFiles.add(line);
                        emptyFileFlag = true;
                    }
                }
                if (!emptyFileFlag) {
                    System.out.println("File " + currentPath + " is empty");
                }
            } catch (IOException e) {
                System.out.println("File " + currentPath + " not found");
                continue;
            }
        }
        if (settings.isAppendMode()) {
            updateContent();
        } else {
            return;
        }
    }

    private void updateContent() {
        System.out.println("Введите данные через запятую и нажмите 'enter':");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        if (in.isEmpty()) {
            System.out.println("Вы ничего не ввели. \n");
            return;
        } else {
            String[] words = in.split(",|, ");
            allLinesFromFiles.addAll(Arrays.asList(words));
        }
    }

    public List<String> getAllLinesFromFiles() {
        return allLinesFromFiles;
    }
}


