package __07_EXAM_16_December_2020;

import java.util.*;
import java.util.stream.Collectors;

public class _01_Cooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // QUEUE
        ArrayDeque<Integer> liquids = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        // STACK
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(ingredients::push);
        Map<Integer, String> cookTable = new HashMap<>();
        cookTable.put(25, "Bread");
        cookTable.put(50, "Cake");
        cookTable.put(75, "Pastry");
        cookTable.put(100, "Fruit Pie");

        Map <String, Integer> productsCooked = new TreeMap<>();

        cookTable.values().stream()
                .forEach(e -> productsCooked.put(e, 0));

        while(!liquids.isEmpty() && !ingredients.isEmpty()){
            int currentLiquid = liquids.poll();
            int currentIngredient = ingredients.pop();

            int sum = currentLiquid + currentIngredient;
            if(ableToCook(sum)){
                String product = cookTable.get(sum);
                productsCooked.put(product, productsCooked.get(product) + 1);
            }else{
                ingredients.push(currentIngredient + 3);
            }
        }
        if(hasCookedEachMeal(productsCooked)){
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        }else{
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }
        System.out.println("Liquids left: " + getElementsInfo(liquids));
        System.out.println("Ingredients left: " + getElementsInfo(ingredients));

        productsCooked.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

    private static String getElementsInfo(ArrayDeque<Integer> deque) {
        return deque.isEmpty()
                ? "none"
                : deque.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    private static boolean hasCookedEachMeal(Map<String, Integer> cookedProducts ) {
        return cookedProducts.values()
                .stream()
                .noneMatch(c -> c == 0);
        //  Еквивалентно на:
      //  for(int count : cookedProducts.values()){
      //      if(count == 0){
       //         return false;
       //     }
      //  }
      //  return true;
    }
    private static boolean ableToCook(int sum) {
        return sum == 25 || sum == 50 || sum == 75 || sum == 100;
    }
}
