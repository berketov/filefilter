package shift.berketov.statistics;

import java.util.List;

public interface StatisticsData {
    List<String> getDataFromStatistics();
    String getName();
    String getPath();
    void setName(String fileName);
    void setPath(String newPath);


}
