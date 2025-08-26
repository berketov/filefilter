package shift.berketov.statistics;

import java.util.List;

public interface StatisticsData {
    List<String> getDataFromStatistics();
    String getName();
    void setName(String fileName);
    void setPrefix(String prefix);
}
