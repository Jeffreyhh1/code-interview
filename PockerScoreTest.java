import java.util.*;

public class PockerScoreTest {
    private static final String SUCCESS = "Congradulation!";
    private static final String WRONG = "Wrong!";
    public static void main(String[] args) {

        {
            String str = "34567";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 50) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "3";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 3) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "35";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 8) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "33";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 12) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "444";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 24) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "6666";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 24*3) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "334567";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 53) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "3344567";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            if (maxScore == 57) {
                System.out.println(SUCCESS);
            } else {
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "33444567";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            int expected = 69;
            if (maxScore == expected) {
                System.out.println(SUCCESS);
            } else {
                System.out.println("Expected: " + expected + ", Actual: " + maxScore);
                throw new RuntimeException(WRONG);
            }
        }

        {
            // Combination of max score: 4444, 33, 567
            String str = "334444567";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            int expected = 78;
            if (maxScore == expected) {
                System.out.println(SUCCESS);
            } else {
                System.out.println("Expected: " + expected + ", Actual: " + maxScore);
                throw new RuntimeException(WRONG);
            }
        }

        {
            // Combination of max score: 34567, 333, 4
            String str = "333344567";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            int expected = 72;
            if (maxScore == expected) {
                System.out.println(SUCCESS);
            } else {
                System.out.println("Expected: " + expected + ", Actual: " + maxScore);
                throw new RuntimeException(WRONG);
            }
        }

        {
            String str = "0JKQ";
            List<Character> cards = PockerScore.convertStringToCharList(str);
            int maxScore = PockerScore.maxScore(cards);
            int expected = 46;
            if (maxScore == expected) {
                System.out.println(SUCCESS);
            } else {
                System.out.println("Expected: " + expected + ", Actual: " + maxScore);
                throw new RuntimeException(WRONG);
            }
        }

    }

}
