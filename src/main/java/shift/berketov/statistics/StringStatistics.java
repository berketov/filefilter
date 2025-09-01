package shift.berketov.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class StringStatistics implements FilteredData {
    private final List<String> dataFromFilter = new ArrayList<>();
    private final String fileName = "strings.txt";
    private String prefix = "";
    private int strCounter;

    public void setLine(String s) {
        dataFromFilter.add(s);
        this.strCounter++;
    }
    @Override
    public String getFullName() {
        return this.prefix + fileName;
    }

    public int getStrCounter() {
        return strCounter;
    }

    public int getShortestString() {
        int shortestString;
        return shortestString = Collections.min(dataFromFilter, comparing(String::length)).length();
    }

    public int getLongestString() {
        int longestString;
        return longestString = Collections.max(dataFromFilter, comparing(String::length)).length();
    }
    @Override
    public List<String> getData() {
        return dataFromFilter;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void printShortStat() {
        if (strCounter == 0) {
            return;
        } else {
            System.out.println("Короткая статистика {" +
                    "Количество элементов в файле " + getFullName() + " = " + getStrCounter() + '}');
        }
    }

    public void printFullStat() {
        if (strCounter == 0) {
            return;
        } else {
            System.out.println("Полная статистика{" +
                    "Имя файла = '" + prefix + fileName + '\'' +
                    ", Общее количество элементов = " + getStrCounter() +
                    ", длинна самой короткой строчки = " + getShortestString() +
                    ", длинна самой длинной строчки = " + getLongestString() +
                    '}');
        }
    }
}
