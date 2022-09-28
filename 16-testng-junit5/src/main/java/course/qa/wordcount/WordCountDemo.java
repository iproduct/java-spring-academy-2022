package course.qa.wordcount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCountDemo {

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
            "after", "before", "most", "off", "mustn", "won't", "must");
    public static final String WIKI_TEXT = """
            Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let programmers write once, run anywhere (WORA),[17] meaning that compiled Java code can run on all platforms that support Java without the need to recompile.[18] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[19][20] particularly for client–server web applications, with a reported 9 million developers.[21]
                
            Java was originally developed by James Gosling at Sun Microsystems and released in May 1995 as a core component of Sun Microsystems' Java platform. The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun had relicensed most of its Java technologies under the GPL-2.0-only license. Oracle offers its own HotSpot Java Virtual Machine, however the official reference implementation is the OpenJDK JVM which is free open-source software and used by most developers and is the default JVM for almost all Linux distributions.
                
            As of March 2022, Java 18 is the latest version, while Java 17, 11 and 8 are the current long-term support (LTS) versions. Oracle released the last zero-cost public update for the legacy version Java 8 LTS in January 2019 for commercial use, although it will otherwise still support Java 8 with public updates for personal use indefinitely. Other vendors have begun to offer zero-cost builds of OpenJDK 8 and 11 that are still receiving security and other upgrades.
                
            Oracle (and others) highly recommend uninstalling outdated and unsupported versions of Java, due to unresolved security issues in older versions.[22] Oracle advises its users to immediately transition to a supported version, such as one of the LTS versions (8, 11, 17).
                
                
            Contents
            History
            See also: Java (software platform) § History
                
            Duke, the Java mascot
                
            James Gosling, the creator of Java, in 2008.
                
            The TIOBE programming language popularity index graph from 2002 to 2018. Java was steadily on the top from mid-2015 to early 2020.
            James Gosling, Mike Sheridan, and Patrick Naughton initiated the Java language project in June 1991.[23] Java was originally designed for interactive television, but it was too advanced for the digital cable television industry at the time.[24] The language was initially called Oak after an oak tree that stood outside Gosling's office. Later the project went by the name Green and was finally renamed Java, from Java coffee, a type of coffee from Indonesia.[25] Gosling designed Java with a C/C++-style syntax that system and application programmers would find familiar.[26]
                
            Sun Microsystems released the first public implementation as Java 1.0 in 1996.[27] It promised write once, run anywhere (WORA) functionality, providing no-cost run-times on popular platforms. Fairly secure and featuring configurable security, it allowed network- and file-access restrictions. Major web browsers soon incorporated the ability to run Java applets within web pages, and Java quickly became popular. The Java 1.0 compiler was re-written in Java by Arthur van Hoff to comply strictly with the Java 1.0 language specification.[28] With the advent of Java 2 (released initially as J2SE 1.2 in December 1998 – 1999), new versions had multiple configurations built for different types of platforms. J2EE included technologies and APIs for enterprise applications typically run in server environments, while J2ME featured APIs optimized for mobile applications. The desktop version was renamed J2SE. In 2006, for marketing purposes, Sun renamed new J2 versions as Java EE, Java ME, and Java SE, respectively.
                
            In 1997, Sun Microsystems approached the ISO/IEC JTC 1 standards body and later the Ecma International to formalize Java, but it soon withdrew from the process.[29][30][31] Java remains a de facto standard, controlled through the Java Community Process.[32] At one time, Sun made most of its Java implementations available without charge, despite their proprietary software status. Sun generated revenue from Java through the selling of licenses for specialized products such as the Java Enterprise System.
                
            On November 13, 2006, Sun released much of its Java virtual machine (JVM) as free and open-source software (FOSS), under the terms of the GPL-2.0-only license. On May 8, 2007, Sun finished the process, making all of its JVM's core code available under free software/open-source distribution terms, aside from a small portion of code to which Sun did not hold the copyright.[33]
                
            Sun's vice-president Rich Green said that Sun's ideal role with regard to Java was as an evangelist.[34] Following Oracle Corporation's acquisition of Sun Microsystems in 2009–10, Oracle has described itself as the steward of Java technology with a relentless commitment to fostering a community of participation and transparency.[35] This did not prevent Oracle from filing a lawsuit against Google shortly after that for using Java inside the Android SDK (see the Android section).
                
            On April 2, 2010, James Gosling resigned from Oracle.[36]
                
            In January 2016, Oracle announced that Java run-time environments based on JDK 9 will discontinue the browser plugin.[37]
                
            Java software runs on everything from laptops to data centers, game consoles to scientific supercomputers.[38]
                
            Principles
            There were five primary goals in the creation of the Java language:[18]
                
            It must be simple, object-oriented, and familiar.
            It must be robust and secure.
            It must be architecture-neutral and portable.
            It must execute with high performance.
            It must be interpreted, threaded, and dynamic.
            """;
    public static void main(String[] args) {
        String[] lines = WIKI_TEXT.split("\n");
        System.out.printf("Lines count: %d%n", lines.length);

        Map<String, Integer> wordCounts = new HashMap<>();

        for(String line : lines) {
            line = line.trim();
            if(line.length() == 0) {
                continue;
            }
            var words = line.split("\\W+");
            for(String word: words){
                word = word.toLowerCase();
                if(word.length() < 3 || STOP_WORDS.contains(word)){
                    continue;
                }
                if(word.endsWith("s")) {
                    word = word.substring(0, word.length() - 1);
                }

                Integer count = wordCounts.get(word);
                if(count != null) {
                    wordCounts.put(word, count + 1);
                } else {
                    wordCounts.put(word, 1);
                }
//                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }
        var wcList = new ArrayList<>(wordCounts.entrySet());
//        wcList.sort(new CountComparator()); // 1)
//        wcList.sort(new Comparator<Map.Entry<String, Integer>>() { // 2)
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o2.getValue().compareTo(o1.getValue());
//            }
//        });
        wcList.sort( (o1,o2) -> o2.getValue().compareTo(o1.getValue()) ); // 3)
        var iter = wcList.iterator();
        int count = 15; // top 15 keywords
        while(iter.hasNext() && count-- > 0){
            var elem = iter.next();
            System.out.printf("%-25s -> %d%n", elem.getKey(), elem.getValue());
        }

    }
}
