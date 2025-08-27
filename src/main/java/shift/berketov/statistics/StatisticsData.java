package shift.berketov.statistics;

import java.util.List;

public interface StatisticsData {
    List<String> getDataFromStatistics();
    String getFullName();
    void setPrefix(String prefix);
}
