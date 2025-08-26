package shift.berketov.filter;

import shift.berketov.reader.Reader;
import shift.berketov.statistics.StatisticsData;

import java.util.List;

public interface Filter {
    void filtration(List<String> dataForFiltration);
    List<StatisticsData> getAllStatisticsData();
}
