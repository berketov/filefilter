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
    private boolean isFileNamePrefix;
    private boolean isNewPathForFile;
    private boolean isShortStatistics;
    private boolean isFullStatistics;

    public Settings(String[] args) {
        this.parseArgs(args);
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> {
                    isNewPathForFile = true;
                    this.setNewPathOut(args[i + 1]);
                }
                case "-p" -> {
                    fileNamePrefix = args[i + 1];
                    isFileNamePrefix = true;
                }
                case "-a" -> appendMode = true;
                case "-s" -> isShortStatistics = true;
                case "-f" -> {
                    isShortStatistics = true;
                    isFullStatistics = true;
                }
            }
            if (args[i].endsWith(".txt") && !isFileExist(args[i])) {
                System.out.printf("Файл %s не найден.", args[i]);
            } else if (args[i].endsWith(".txt") && isFileExist(args[i])) {
                paths.add(args[i]);
            } else {
                throw new IllegalArgumentException("Вы ввели неверный аргумент, обратитесь к инструкции программы.");
            }
        }
    }

    public boolean hasNewPathForFile() {return isNewPathForFile;}
    public boolean hasFileNamePrefix() {return isFileNamePrefix;}
    public boolean isAppendMode() {return appendMode;}
    public boolean hasShortStatistics() {return isShortStatistics;}
    public boolean hasFullStatistics() {
        return isFullStatistics;
    }
    private boolean isFileExist(String path) {return Files.exists(Path.of(path));}

    public String getPathOut() {return pathOut;}
    public String getFileNamePrefix() {
        return fileNamePrefix;
    }
    public List<String> getPaths() {
        return paths;
    }
    private void setNewPathOut(String pathOut) {
        if (Files.exists(Path.of(pathOut))) {
            this.pathOut = pathOut;
        } else {
            System.out.println("Невозможно сохранить файл по указанному Вами пути.");
        }
    }
}

// //    private boolean checkPathOut() {
////        return Files.exists(Path.of(this.pathOut));
////    }//todo перенёс метод проверки в setPathOut


//        if (!this.checkPathOut()) {
//            //TODO error - тут под вопросом, потому что есть путь я реализовал его в setNewPathOut

//        }



//   if (args[i].endsWith(".txt") && isFileExist(args[i])) {
//           paths.add(args[i]);
//           } else if (paths.isEmpty()) {
//           System.out.println("Вы не указали путь или имя текстового файла для фильтрации, или же такого файла не существует.");
//           } else {
//           throw new IllegalArgumentException("Вы ввели неверный аргумент, обратитесь к инструкции программы.");
//           }


//if (args[i].endsWith(".txt") && isFileExist(args[i])) {
//        paths.add(args[i]);
//        } else if (paths.isEmpty()) {
//        System.out.println("Файлы для фильтрации не найдены. Проверьте правильность пути и наличие txt-файлов.");
//        } else {
//        throw new IllegalArgumentException("Вы ввели неверный аргумент, обратитесь к инструкции программы.");
//        }