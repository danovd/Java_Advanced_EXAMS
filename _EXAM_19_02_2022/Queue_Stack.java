package MY_EXAM_19_02_2022;

import java.util.*;
import java.util.stream.Collectors;

public class Queue_Stack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> vowels = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<String> consonants = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .forEach(consonants::push);


        List<String> list = new ArrayList<>();
        list.add("pear");
        list.add("flour");
        list.add("pork");
        list.add("olive");



        while(!consonants.isEmpty()){

            String vowel = vowels.poll();
            String consonant = consonants.pop();
            vowels.offer(vowel);





            for (int i = 0; i < list.size(); i++) {
                String word = list.get(i);
                if(word.contains(vowel)) {
                    String current = removeChar(vowel.charAt(0), list.get(i));
                    list.remove(i);
                    list.add(i, current);
                }
                if(word.contains(consonant)) {
                    String current = removeChar(consonant.charAt(0), list.get(i));
                    list.remove(i);
                    list.add(i, current);
                }
            }
        }
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if(isEverithingBig(list.get(i))){
                num++;
            }
        }
        System.out.println("Words found: " + num);
        list.forEach(e-> {
            if(isEverithingBig(e)){
                System.out.println(e.toLowerCase(Locale.ROOT));
            }
        });

    }

    public static String removeChar(char c, String s){
       StringBuilder sb = new StringBuilder();

       String charToCheck = c + "";
       for (int i = 0; i < s.length(); i++) {
            String currentChar = s.charAt(i) + "";
            if(!charToCheck.equals(currentChar)){
                sb.append(currentChar);
            }else{
                sb.append(currentChar.toUpperCase(Locale.ROOT));
            }
        }
        return sb.toString();
    }
    public static boolean isEverithingBig(String s){
        for (int i = 0; i < s.length(); i++) {
            if(Character.isLowerCase(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
