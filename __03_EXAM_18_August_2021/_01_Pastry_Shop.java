package __03_EXAM_18_August_2021;

import java.util.*;
import java.util.stream.Collectors;

public class _01_Pastry_Shop {
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
    Map<String, Integer> counts = new LinkedHashMap<>();
    counts.put("Biscuit", 0);
    counts.put("Cake", 0);
    counts.put("Pie", 0);
    counts.put("Pastry", 0);

        while(!liquids.isEmpty() && !ingredients.isEmpty()){
            int liquid = liquids.peek();
            int ingredient = ingredients.peek();

            int sum = liquid + ingredient;

            switch(sum){
                case 25:
                    counts.put("Biscuit", counts.get("Biscuit") + 1);
                    liquids.poll();
                    ingredients.pop();

                    break;
                case 50:
                    counts.put("Cake", counts.get("Cake") + 1);
                    liquids.poll();
                    ingredients.pop();
                    break;
                case 75:
                    counts.put("Pie", counts.get("Pie") + 1);
                    liquids.poll();
                    ingredients.pop();
                    break;
                case 100:
                    counts.put("Pastry", counts.get("Pastry") + 1);
                    liquids.poll();
                    ingredients.pop();
                    break;
                default:
                liquids.poll();
                int value = ingredients.pop() + 3;
                ingredients.push(value);
                    break;
            }
        }
if(checkIsThereAtLeastOneOfEachFood(counts)){
    System.out.println("Great! You succeeded in cooking all the food!");
}else{
    System.out.println("What a pity! You didn't have enough materials to cook everything.");
}


        System.out.println("Liquids left: " + getElementsInfo(liquids));
        System.out.println("Ingredients left: " + getElementsInfo(ingredients));

        for(Map.Entry<String, Integer> e : counts.entrySet()){
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    private static boolean checkIsThereAtLeastOneOfEachFood(Map<String, Integer> counts) {
        return counts.values()
                .stream()
                .noneMatch(c -> c == 0);
    }
    private static String getElementsInfo(ArrayDeque<Integer> deque) {
        return deque.isEmpty()
                ? "none"
                : deque.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

}
