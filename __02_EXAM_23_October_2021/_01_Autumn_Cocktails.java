package __02_EXAM_23_October_2021;

import java.util.*;
import java.util.stream.Collectors;

public class _01_Autumn_Cocktails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> queue = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stack::push);

        Map<Integer, String> map = new HashMap<>();
        map.put(150, "Pear Sour");
        map.put(250, "The Harvest");
        map.put(300, "Apple Hinny");
        map.put(400, "High Fashion");

        Map<String, Integer> amounts = new TreeMap<>();
        int count = 0;
        while(!queue.isEmpty() && !stack.isEmpty()){
            int ingredients = queue.peek();
            if(ingredients == 0){
                continue;
            }
            int freshness = stack.peek();

            int multiply = ingredients * freshness;
            if(map.containsKey(multiply)){
                String cocktailName = map.get(multiply);
                if(!amounts.containsKey(cocktailName)){
                    amounts.put(cocktailName, 1);
                }else {
                    int value = amounts.get(cocktailName) + 1;
                    amounts.put(map.get(multiply), value);
                }
                count++;
                queue.poll();
                stack.pop();
            }else {
                stack.pop();
                if(!queue.isEmpty()) {
                    int current = queue.poll() + 5;
                    queue.offer(current);
                }
            }
        }

if(count >= 4){
    System.out.println("It's party time! The cocktails are ready!");
}else{
    System.out.println("What a pity! You didn't manage to prepare all cocktails.");
    int sum = 0;
    while(!queue.isEmpty()){
        sum += queue.poll();
    }
    System.out.println("Ingredients left: " + sum);
}
for(Map.Entry<String, Integer> entry : amounts.entrySet()){
    System.out.println(" # " + entry.getKey() + " --> " + entry.getValue());
}
    }
}
