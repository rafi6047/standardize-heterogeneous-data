package se.kth.epe.degreeproject.standardizeheterogeneousdata.similarityalgorithms;

import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.Jaccard;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import info.debatty.java.stringsimilarity.SorensenDice;

public class StringSimilarityTest {
    public static void main(String[] args) {

        String s1 = "ssh".toLowerCase();
        String s2 = "sshd".toLowerCase();
//        String s1 = "Microsoft Visual C++ 2008 Redistributable - x64 9.0.30729.6161".toLowerCase();
//        String s2 = "Microsoft Visual C++ 2015 x86 Additional Runtime - 14.0.23026".toLowerCase();
//        String s2 = "Microsoft Visual C++ Redistributable".toLowerCase();

        tryDifferentAlgorithms(s1, s2);

    }

    private static void tryDifferentAlgorithms(String s1, String s2) {

        Jaccard jaccard = new Jaccard(1);
        System.out.println("jaccard similarity: " + jaccard.similarity(s1, s2));

        NormalizedLevenshtein normalizedLevenshtein = new NormalizedLevenshtein();
        System.out.println("normalizedLevenshtein similarity: " + (1 - normalizedLevenshtein.distance(s1, s2)));

        JaroWinkler jaroWinkler = new JaroWinkler();
        System.out.println("JaroWinkler similarity: " + jaroWinkler.similarity(s1, s2));

        Cosine cosine = new Cosine();
        System.out.println("Cosine similarity: " + cosine.similarity(s1, s2));

        SorensenDice sorensenDice = new SorensenDice();
        System.out.println("sorensenDice similarity: " + sorensenDice.similarity(s1, s2));
    }
}
