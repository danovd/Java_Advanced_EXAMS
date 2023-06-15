package __09_EXAM_28_June_2020;

import java.util.*;
import java.util.stream.Collectors;

public class Bombs_Second_Try {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Queue
        ArrayDeque<Integer> effects = Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        // Stack
        ArrayDeque<Integer> casings = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(casings::push);

        Map<Integer, String> map = new LinkedHashMap<>();

        map.put(40, "Datura Bombs");
        map.put(60, "Cherry Bombs");
        map.put(120, "Smoke Decoy Bombs");

        Map <String, Integer> counts = new TreeMap<>();

        map.values().forEach(e -> {
        counts.put(e, 0);
        });
     //   counts.put("Datura Bombs", 0);
     //   counts.put("Cherry Bombs", 0);
     //   counts.put("Smoke Decoy Bombs", 0);


        while(!effects.isEmpty() && !casings.isEmpty()){
            int effect = effects.peek();
            int casing = casings.peek();
            int sum = effect + casing;

            if(sum == 40 || sum == 60 || sum == 120){
                String bombType = map.get(sum);
                Integer amountBombs = counts.get(bombType) + 1;
                counts.put(bombType, amountBombs);
                effects.poll();
                casings.pop();
            }else{
                casing = casings.pop() - 5;
                casings.push(casing);
             //   if(casings.peek() <= 0){
             //       casings.pop();
             //   }
            }
            if(isFullPouch(counts)){
                break;
            }
        }


        if(isFullPouch(counts)){
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        }else{
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        if(effects.isEmpty()){
            System.out.println("Bomb Effects: empty");
        }else{
            String str = "Bomb Effects: " + effects.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println(str);
        }
        if(casings.isEmpty()){
            System.out.println("Bomb Casings: empty");
        }else{
            String str = "Bomb Casings: " + casings.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println(str);
        }

    for(Map.Entry<String, Integer> entry : counts.entrySet()){
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    }
    public static boolean isFullPouch(Map<String, Integer>map){
        for(Integer i : map.values()){
            if(i < 3){
                return false;
            }
        }
        return true;
    }
}
