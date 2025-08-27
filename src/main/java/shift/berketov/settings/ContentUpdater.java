package shift.berketov.settings;

import java.util.List;

public class ContentUpdater {
    public List<String> append (List<String> filesContent, List<String> stringsToAdd) {
        filesContent.addAll(stringsToAdd);
        return filesContent;
    }
}
