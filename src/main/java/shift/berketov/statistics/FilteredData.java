package shift.berketov.statistics;

import java.util.List;

public interface FilteredData {
    List<String> getData();
    String getFullName();
    void setPrefix(String prefix);
}
