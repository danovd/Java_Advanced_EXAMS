package __11_EXAM_17_December_2019;

import java.util.*;
import java.util.stream.Collectors;

public class _01_Santa_Present_Factory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // STACK
        ArrayDeque<Integer> boxMaterials = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(boxMaterials::push);
        // QUEUE
        ArrayDeque<Integer> magicLevel = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Map<Integer, String> presents = new LinkedHashMap<>();
        presents.put(150, "Doll");
        presents.put(250, "Wooden train");
        presents.put(300, "Teddy bear");
        presents.put(400, "Bicycle");

        Map<String, Integer> counts = new TreeMap<>();


        presents.values()
                .forEach(e -> counts.put(e, 0));


while(!boxMaterials.isEmpty() && !magicLevel.isEmpty()){
    int material = boxMaterials.peek();
    int magic = magicLevel.peek();

    int multiply = material * magic;

    if(material == 0 || magic == 0){
        if(material == 0){
            boxMaterials.pop();
        }
        if(magic == 0){
            magicLevel.poll();
        }
        continue;
    }
    if(presents.containsKey(multiply)){
        counts.put(presents.get(multiply), counts.get(presents.get(multiply)) + 1);
        boxMaterials.pop();
        magicLevel.poll();
    }else if(multiply < 0){
        int value = material + magic;
        boxMaterials.pop();
        magicLevel.poll();
        boxMaterials.push(value);
    }else if(multiply > 0){
        magicLevel.poll();
        int newValue = boxMaterials.pop() + 15;
        boxMaterials.push(newValue);
    }
}

boolean isDone = isCompletedTheTask(counts);
String presentOutput = isDone ? "The presents are crafted! Merry Christmas!"
        : "No presents this Christmas!";
        System.out.println(presentOutput);
if(!boxMaterials.isEmpty()){
    String materialsOutput = "Materials left: " +
            boxMaterials.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
    System.out.println(materialsOutput);
}
if(!magicLevel.isEmpty()){
    String magikOutput = "Magic left: " +
            magicLevel.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
    System.out.println(magikOutput);
}

for(Map.Entry<String, Integer> entry : counts.entrySet()){
   if(entry.getValue() > 0)
       System.out.println(entry.getKey() + ": " + entry.getValue());
}

    }

    private static boolean isCompletedTheTask(Map<String, Integer> counts) {
        return (counts.get("Doll") > 0 && counts.get("Wooden train") > 0)
                || (counts.get("Teddy bear") > 0 && counts.get("Bicycle") > 0);
    }
}
