package se.kth.epe.degreeproject.standardizeheterogeneousdata.similarityalgorithms;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

public class StringSetSimilarityUtil {

    public static double getJaccardSimilarity(Set<String> set1, Set<String> set2) {
        int commonElementsSize = CollectionUtils.intersection(set1, set2).size();
        double jaccardSimilarity = (double) commonElementsSize / (set1.size() + set2.size() - commonElementsSize);


        return Math.round(jaccardSimilarity * 1000.0) / 1000.0;
    }

    public static double getRafiSimilarity(Set<String> set1, Set<String> set2) {
        int commonElementsSize = CollectionUtils.intersection(set1, set2).size();
        double rafiSimilarity = (double) commonElementsSize * 2 / (set1.size() + set2.size());

        return Math.round(rafiSimilarity * 1000.0) / 1000.0;
    }

}
