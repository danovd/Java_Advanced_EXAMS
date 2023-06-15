package MY_EXAM_19_02_2022;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Queue_Stack_secondWay {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char [] input = sc.nextLine().toCharArray();
        ArrayDeque<Character> vowels = new ArrayDeque<>();
        for (char c : input) {
            vowels.push(c);
        }

        vowels.forEach(e-> System.out.println(e));


        ArrayDeque<String> consonants = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .forEach(consonants::push);

    }
}
