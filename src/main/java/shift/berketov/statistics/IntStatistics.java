package shift.berketov.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntStatistics implements FilteredData {
    private final List<Long> dataFromFilter = new ArrayList<>();
    private final String fileName = "integers.txt";
    private String prefix = "";
    private long intCounter;
    private long sum;

    public void setDigit(long d) {
        dataFromFilter.add(d);
        this.intCounter++;
    }
    @Override
    public String getFullName() {return this.prefix + fileName;}

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
    public List<String> getData() {
        List<String> strList;
        strList = dataFromFilter.stream().map(Object::toString)
                .collect(Collectors.toList());
        return strList;
    }

    @Override
    public void setPrefix(String prefix) {this.prefix = prefix;}

    public void printShortStat() {
        if (intCounter == 0) {
            return;
        } else {
            System.out.println("Короткая статистика {" +
                    "Количество элементов в файле " + getFullName() + " = " + getIntCounter() + '}');
        }
    }

    public void printFullStat() {
        if (intCounter == 0) {
            return;
        } else {
            System.out.println("Полная статистика{" +
                    "Имя файла = '" + prefix + fileName + '\'' +
                    ", Общее количество элементов = " + intCounter +
                    ", минимальное число = " + getMin() +
                    ", максимальное число = " + getMax() +
                    ", сумма всех чисел = " + getSum() +
                    ", среднее = " + getAverage() +
                    '}');
        }
    }
}
