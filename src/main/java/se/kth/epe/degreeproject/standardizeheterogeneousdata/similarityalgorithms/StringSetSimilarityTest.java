package se.kth.epe.degreeproject.standardizeheterogeneousdata.similarityalgorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class StringSetSimilarityTest {

    public static void main(String[] args) {

        String regexToSplit = "[ |,|-]";

//        String s1 = "hola amigo".toLowerCase();
//        String s2 = "chao amigo".toLowerCase(); // doesn't do very good job if single word with differences
//        String s1 = "Microsoft Visual C++".toLowerCase();
        String s1 = "Microsoft Windows 7".toLowerCase();
        String s2 = "Windows 7".toLowerCase();
//        String s2 = "Microsoft Visual C++".toLowerCase();

        Set<String> set1 = new HashSet<>(Arrays.asList(s1.split(regexToSplit)));
        System.out.println(set1);
        Set<String> set2 = new HashSet<>(Arrays.asList(s2.split(regexToSplit)));
        System.out.println(set2);

        System.out.println("Jaccard: " + StringSetSimilarityUtil.getJaccardSimilarity(set1, set2));
        System.out.println("Rafi   : " + StringSetSimilarityUtil.getRafiSimilarity(set1, set2));
    }

}