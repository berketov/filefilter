package shift.berketov.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class StringStatistics implements StatisticsData{
    private final List<String> dataFromFilter = new ArrayList<>();
    private String fileName = "strings.txt";
    private String path = "strings.txt";
    private int strCounter;

    public void setLine(String s) {
        dataFromFilter.add(s);
        this.strCounter++;
    }

    public String getName() {
        return this.fileName;
    }

    public String getPath() {return path;}

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

    public void setName(String name) {
        this.fileName = name;
    }

    public void setPath(String path) {this.path = path;}

    public void printFullStat() {
        System.out.println( "Full statistics{" +
                "File name = '" + fileName + '\'' +
                ", Total number of items = " + getStrCounter() +
                ", smallest string = " + getShortestString() + " number" +
                ", largest string = " + getLongestString() + " number" +
                '}');
    }
}
