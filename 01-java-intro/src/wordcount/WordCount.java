package wordcount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordCount {
    public static final int KEYWORDS_COUNT = 20;

    public static final Set<String> STOP_WORDS = Set.of(
            "when", "didn't", "wasn", "y", "few", "below", "into", "there", "his", "these", "about", "if", "again",
            "too", "were", "then", "doing", "haven", "such", "this", "me", "the", "further", "whom", "having",
            "mightn", "both", "him", "she", "don", "yourselves", "all", "do", "it's", "i", "what", "needn", "had",
            "during", "weren", "wasn't", "up", "will", "just", "isn", "theirs", "own", "ll", "hadn", "aren't",
            "while", "hasn't", "themselves", "re", "in", "she's", "wouldn't", "he", "couldn't", "their", "more",
            "that'll", "ain", "t", "a", "was", "but", "couldn", "only", "been", "so", "until", "have", "from",
            "those", "or", "our", "ourselves", "as", "doesn", "over", "how", "you'd", "where", "itself", "against",
            "very", "you'll", "who", "needn't", "by", "no", "each", "its", "at", "we", "any", "ours", "mightn't",
            "through", "does", "here", "didn", "between", "some", "which", "other", "did", "don't", "hasn", "won",
            "not", "should", "haven't", "on", "now", "with", "you", "them", "himself", "under", "you've", "yours",
            "they", "shouldn", "doesn't", "of", "can", "be", "above", "weren't", "wouldn", "for", "s", "and",
            "hadn't", "herself", "nor", "should've", "am", "once", "ve", "an", "hers", "myself", "to", "her", "is",
            "shan't", "are", "same", "being", "your", "than", "it", "aren", "shan", "d", "m", "isn't", "down",
            "shouldn't", "you're", "why", "o", "yourself", "has", "my", "ma", "out", "that", "mustn't", "because",
            "after", "before", "most", "off", "mustn", "won't");

    public static List<Map.Entry<String, Integer>> findTopKeywords(String filename, int count) throws IOException {
        Map<String, Integer> wordCounts = new HashMap<>();
        Path path = Path.of(filename);
        Files.lines(path).forEach(line -> {
            var words = line.split("\\W+");
            for (var word : words) {
                var wordToLower = word.toLowerCase();
                if (wordToLower.length() < 2 || STOP_WORDS.contains(wordToLower)) {
                    continue;
                }
                wordCounts.put(wordToLower, wordCounts.getOrDefault(wordToLower, 0) + 1);
            }
        });
        List<Map.Entry<String, Integer>> wordsCountList = new ArrayList<>(wordCounts.entrySet());
        wordsCountList.sort(new WordCountComparator());
        return wordsCountList.subList(0, count);
    }

    public static void main(String[] args) throws IOException {
        var sortedKeywordsByCount =
                findTopKeywords("wiki_java.txt", KEYWORDS_COUNT);
        for (var wordCount : sortedKeywordsByCount) {
            System.out.printf("%15s -> %5d%n", wordCount.getKey(), wordCount.getValue());
        }
    }
}
