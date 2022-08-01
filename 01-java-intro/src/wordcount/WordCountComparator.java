package wordcount;

import java.util.Comparator;
import java.util.Map;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> v1, Map.Entry<String, Integer> v2) {
        return v2.getValue().compareTo(v1.getValue());
    }
}
