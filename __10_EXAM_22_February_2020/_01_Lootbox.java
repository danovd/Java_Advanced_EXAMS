package __10_EXAM_22_February_2020;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _01_Lootbox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> queue = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stack::push);
        int itemValue = 0;

        while(!queue.isEmpty() && !stack.isEmpty()){
            int first = queue.peek();
            int second = stack.pop();

            int sum = first + second;

            if(sum % 2 == 0){
                itemValue += sum;
                queue.poll();
            }else{
                queue.offer(second);
            }
        }

        if(queue.isEmpty()){
            System.out.println("First lootbox is empty");
        }else{
            System.out.println("Second lootbox is empty");
        }
        if(itemValue >= 100){
            System.out.println("Your loot was epic! Value: " + itemValue);
        }else{
            System.out.println("Your loot was poor... Value: " + itemValue);
        }
    }
}
