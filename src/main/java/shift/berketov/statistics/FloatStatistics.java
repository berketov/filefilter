package shift.berketov.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FloatStatistics implements StatisticsData{
    private final List<Double> dataFromFilter = new ArrayList<>();
    private String fileName = "floats.txt";
    private String path = "floats.txt";
    private int floatCounter;
    private double sum;

    public void setDigit(double d) {
        dataFromFilter.add(d);
        this.floatCounter++;
    }

    public String getName() {
        return this.fileName;
    }

    public String getPath() {return path;}

    public int getFloatCounter() {
        return floatCounter;
    }

    public double getMin() {
        double min;
        return min = Collections.min(dataFromFilter);
    }

    public double getMax() {
        double max;
        return max = Collections.max(dataFromFilter);
    }

    public double getSum() {
        return this.sum = dataFromFilter.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double getAverage () {
        double average;
        return average = this.sum / floatCounter;
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
                ", Total number of items = " + floatCounter +
                ", min = " + getMin() +
                ", max = " + getMax() +
                ", sum = " + getSum() +
                ", average = " + getAverage() +
                '}');
    }
}
