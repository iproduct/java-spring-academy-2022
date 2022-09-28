package course.qa.wordcount;

import java.util.Comparator;
import java.util.Map;

public class CountComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> wc1, Map.Entry<String, Integer> wc2) {
        return wc2.getValue().compareTo(wc1.getValue());
    }
}
