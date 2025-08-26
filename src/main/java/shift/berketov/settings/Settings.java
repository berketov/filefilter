package shift.berketov.settings;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Settings {
    private String pathOut = "./";
    private String fileNamePrefix;
    private final List<String> paths = new ArrayList<>();

    private boolean appendMode;
    private boolean isShortStatistics;
    private boolean isFullStatistics;

    public Settings(String[] args) {
        this.parseArgs(args);
    }

    private void parseArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("Ни один аргумент не найден. Повторите попытку ввода в соответствии с инструкцией.");
        } else {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-o" -> {
                        this.setNewPathOut(args[i + 1]);
                        i++;
                        continue;
                    }
                    case "-p" -> {
                        fileNamePrefix = args[i + 1];
                        i++;
                        continue;
                    }
                    case "-a" -> {
                        appendMode = true;
                        continue;
                    }
                    case "-s" -> {
                        isShortStatistics = true;
                        continue;
                    }
                    case "-f" -> {
                        isShortStatistics = true;
                        isFullStatistics = true;
                        continue;
                    }
                }
                if (args[i].endsWith(".txt") && !isFileExist(args[i])) {
                    System.out.printf("Файл %s не найден.", args[i]);
                } else if (args[i].endsWith(".txt") && isFileExist(args[i])) {
                    paths.add(args[i]);
                } else {
                    throw new IllegalArgumentException("Вы ввели неверный аргумент (" + args[i] + ")," +
                            "обратитесь к инструкции программы. Возможно Вы не указали тип файла в конце его имени.");
                }
            }
        }
        if (paths.isEmpty()) {
           throw new IllegalArgumentException("Файлы с типом данных .txt не найдены. Для фильтрации укажите файлы данного типа.");
        }
    }

    private void setNewPathOut(String pathOut) {
        if (Files.exists(Path.of(pathOut))) {
            this.pathOut = pathOut;
        } else {
            throw new IllegalArgumentException("Невозможно сохранить файл по указанному Вами пути.");
        }
    }

    public boolean isAppendMode() {return appendMode;}
    public boolean hasShortStatistics() {return isShortStatistics;}
    public boolean hasFullStatistics() {return isFullStatistics;}
    private boolean isFileExist(String path) {return Files.exists(Path.of(path));}

    public String getPathOut() {return pathOut;}
    public String getFileNamePrefix() {return fileNamePrefix;}
    public List<String> getPaths() {return paths;}
 }

