package shift.berketov.statistics;

import shift.berketov.settings.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntStatistics implements StatisticsData{
    private final List<Long> dataFromFilter = new ArrayList<>();
    private String fileName = "integers.txt";
    private String path = "integers.txt";
    private long intCounter;
    private long sum;

    public void setDigit(long d) {
        dataFromFilter.add(d);
        this.intCounter++;
    }

    public String getName() {
        return this.fileName;
    }

    public String getPath() {return path;}

    public long getIntCounter() {
        return intCounter;
    }

    public long getMin() {
        long min;
        return min = Collections.min(dataFromFilter);
    }

    public long getMax() {
        long max;
        return max = Collections.max(dataFromFilter);
    }

    public long getSum() {
        return this.sum = dataFromFilter.stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public long getAverage () {
        long average;
        return average = this.sum / intCounter;
    }

    @Override
    public List<String> getDataFromStatistics() {
        List<String> strList;
        strList = dataFromFilter.stream().map(Object::toString)
                .collect(Collectors.toList());
        return strList;
    }

    public void setName(String name) {
        this.fileName = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void printFullStat() {
        System.out.println( "Full statistics{" +
                "File name = '" + fileName + '\'' +
                ", Total number of items = " + intCounter +
                ", min = " + getMin() +
                ", max = " + getMax() +
                ", sum = " + getSum() +
                ", average = " + getAverage() +
                '}');
    }

}
