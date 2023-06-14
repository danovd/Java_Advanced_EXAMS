package __02_EXAM_23_October_2021;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Autumn_Cocktails_Second_Try {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> ingredients = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> freshnesses = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(freshnesses::push);

        Map<Integer, String> map = new HashMap<>();
        map.put(150, "Pear Sour");
        map.put(250, "The Harvest");
        map.put(300, "Apple Hinny");
        map.put(400, "High Fashion");

        Map<String, Integer> amounts = new TreeMap<>();

        while(!ingredients.isEmpty() && !freshnesses.isEmpty()){
            int ingredient = ingredients.peek();
            int freshness = freshnesses.peek();

            int multiply = ingredient * freshness;
            if(ingredient == 0){
                ingredients.poll();
            }
            else if(map.containsKey(multiply)){
                String nameOfCocktail = map.get(multiply);
                if(!amounts.containsKey(nameOfCocktail)){
                    amounts.put(nameOfCocktail, 1);
                }else{
                    amounts.put(nameOfCocktail, amounts.get(nameOfCocktail) + 1);
                }
                ingredients.poll();
                freshnesses.pop();
            }else if(!map.containsKey(multiply)){
                freshnesses.pop();
                int value = ingredients.poll();
                ingredients.offer(value + 5);
            }

        }
boolean isAllReady = checkIsAllPrepared(amounts);
        if(isAllReady){
            System.out.println("It's party time! The cocktails are ready!");
        }else{
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }
        if(!ingredients.isEmpty()){
            int sum = ingredients.stream().mapToInt(e -> e).sum();
            System.out.println("Ingredients left: " + sum);
        }
        if(!amounts.isEmpty()){
            amounts.forEach((key, value) -> System.out.printf(" # %s --> %d\n", key, value));
        }

    }

    private static boolean checkIsAllPrepared(Map<String, Integer> amounts) {
       return amounts.size() >= 4;
    }


}
