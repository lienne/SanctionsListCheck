package com.screening.sanctions;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ScreeningController {

    private static List<String> sanctionsList = new ArrayList<>(
            Arrays.asList("Kristopher Doe", "Iceland", "Royal Arctic Line"));
    private final LevenshteinDistance lv = new LevenshteinDistance();
    private final String HIT = "Hit";
    private final String NOHIT = "No Hit";
    private final int threshold = 75;
    private boolean matchFound;

    @RequestMapping("/")
    public String index() { return "Greetings!"; }

    @GetMapping(value="/screen") // localhost:8080/screen?input=Kristopher Doe
    public String screenInput(String input) {

        naiveApproach(input);

        // Levenshtein algo below
        if(input == null) {
            return "Invalid input. Please enter a name to be matched against the Sanctions List.";
        }

        checkAgainstSanctionsList(input);

        if(matchFound) return HIT;

        return NOHIT;
    }

    private void checkAgainstSanctionsList(String input) {
        matchFound = false;

        for(int i = 0; i < sanctionsList.size(); i++) {
            double similarityRatio = findSimilarityRatio(input, sanctionsList.get(i));
            if(similarityRatio >= threshold) {
                matchFound = true;
                printSimilarityScoreAndPercentage(input,sanctionsList.get(i));
                break;
            }
        }
    }

    private double findSimilarityRatio(String s1, String s2) {
        return (1 - ((double)lv.apply(s1,s2)) / Math.max(s1.length(), s2.length())) * 100;
    }

    private void printSimilarityScoreAndPercentage(String input, String sanctionsItem) {
        System.out.println("Levenshtein Algorithm Results:");
        System.out.println("Similarity Score: " + lv.apply(input,sanctionsItem));
        System.out.println("Percentage match: " + findSimilarityRatio(input,sanctionsItem) + "%");
    }

    /*
        This naive approach to the algorithm assumes the string being checked will always be the same length
        as the string it is being checked against, and only takes into consideration typos where characters
        are switched or mistyped, and does not consider the cases where characters are missing from a string.
    */
    private void naiveApproach(String input) {
        int count = 0;
        float percent;

        if(input == null || input.equals("")) {
            System.out.println("Invalid input. Please enter a name to be matched against the Sanctions List.");
        }
        else {
            for (int i = 0; i < sanctionsList.size(); i++) {
                if (input.length() == sanctionsList.get(i).length()) {
                    for (int j = 0; j < input.length(); j++) {
                        if (input.charAt(j) == sanctionsList.get(i).charAt(j)) {
                            count++;
                        }
                    }
                }
            }

            percent = ((float)count / (float)input.length())*100;

            System.out.println("Input string: " + input);
            System.out.println("Number of characters matching: " + count);
            System.out.println("Percentage match: " + percent + "%");
            System.out.println();
        }
    }

}
