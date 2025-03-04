import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PockerScore {
    private static final Map<Character, Integer> cardToValue = new HashMap<>();

    static {
        cardToValue.put('0', 10);
        cardToValue.put('J', 11);
        cardToValue.put('Q', 12);
        cardToValue.put('K', 13);
    }

    public static List<Character> convertStringToCharList(String str) {
        List<Character> cards = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            cards.add(str.charAt(i));
        }
        return cards;
    }

    public static int maxScore(List<Character> cards) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (char card : cards) {
            int value = 0;
            if (card >= '1' && card <= '9') {
                value = card - '0';
            } else {
                value = cardToValue.get(card);
            }
            countMap.put(value, countMap.getOrDefault(value, 0) + 1);
        }
        List<Integer> values = new ArrayList<>(countMap.keySet());
        Collections.sort(values);

        return backTrack(values, countMap);
    }

    public static int backTrack(List<Integer> values, Map<Integer, Integer> countMap) {
        int maxScore = 0;
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            if (countMap.get(value) >= 1) {
                countMap.put(value, countMap.get(value) - 1);
                int score = value + backTrack(values, countMap);
                maxScore = Math.max(maxScore, score);
                countMap.put(value, countMap.get(value) + 1);
            }

            if (countMap.get(value) >= 2) {
                countMap.put(value, countMap.get(value) - 2);
                int score = value * 2 * 2 + backTrack(values, countMap);
                maxScore = Math.max(maxScore, score);
                countMap.put(value, countMap.get(value) + 2);
            }

            if (countMap.get(value) >= 3) {
                countMap.put(value, countMap.get(value) - 3);
                int score = value * 3 * 2 + backTrack(values, countMap);
                maxScore = Math.max(maxScore, score);
                countMap.put(value, countMap.get(value) + 3);
            }

            if (countMap.get(value) >= 4) {
                countMap.put(value, countMap.get(value) - 4);
                int score = value * 4 * 3 + backTrack(values, countMap);
                maxScore = Math.max(maxScore, score);
                countMap.put(value, countMap.get(value) + 4);
            }

            if (i + 4 < values.size()) {
                boolean isStraight = true;
                for (int j = 0; j < 5; j++) {
                    if (countMap.get(values.get(i + j)) < 1) {
                        isStraight = false;
                        break;
                    }
                }
                if (isStraight) {
                    int score = 0;
                    for (int j = 0; j < 5; j++) {
                        countMap.put(values.get(i + j), countMap.get(values.get(i + j)) - 1);
                        score += values.get(i + j) * 2;
                    }
                    score = score + backTrack(values, countMap);
                    maxScore = Math.max(maxScore, score);
                    for (int j = 0; j < 5; j++) {
                        countMap.put(values.get(i + j), countMap.get(values.get(i + j)) + 1);
                    } 
                }
            }
        }
        return maxScore;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        List<Character> cards = convertStringToCharList(str);
        
        System.out.println(maxScore(cards));
    }
    
}
