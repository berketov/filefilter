package shift.berketov.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class StringStatistics implements StatisticsData{
    private final List<String> dataFromFilter = new ArrayList<>();
    private String fileName = "strings.txt";
    private int strCounter;

    public void setLine(String s) {
        dataFromFilter.add(s);
        this.strCounter++;
    }
    @Override
    public String getName() {
        return this.fileName;
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
    public List<String> getDataFromStatistics() {
        return dataFromFilter;
    }
    @Override
    public void setName(String name) {
        this.fileName = name;
    }

    public void printFullStat() {
        System.out.println( "Полная статистика{" +
                "Имя файла = '" + fileName + '\'' +
                ", Общее количество элементов = " + getStrCounter() +
                ", длинна самой короткой строчки = " + getShortestString() +
                ", длинна самой длинной строчки = " + getLongestString() +
                '}');
    }
}
