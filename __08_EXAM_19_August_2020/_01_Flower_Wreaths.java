package __08_EXAM_19_August_2020;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _01_Flower_Wreaths {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // STACK
        ArrayDeque<Integer> tulips = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tulips::push);
        // QUEUE
        ArrayDeque<Integer> daffodils = Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));


        int countBouquets = 0;
        int stored = 0;
        while(!tulips.isEmpty() && !daffodils.isEmpty()){
            int tulip = tulips.peek();
            int daffodil = daffodils.peek();

            int sum = tulip + daffodil;

            if(sum == 15){
                countBouquets++;
                tulips.pop();
                daffodils.poll();
            }else if(sum > 15){
                int currentTulip = tulips.pop() - 2;
                tulips.push(Integer.max(currentTulip, 0));
            }else {
                stored += tulips.pop();
                stored += daffodils.pop();
            }
        }
        countBouquets += stored / 15;
        String result = countBouquets >= 5 ? "You made it, you are going to the competition with "
                + countBouquets + " wreaths!"
                : "You didn't make it, you need " + (5 - countBouquets) + " wreaths more!";
        System.out.println(result);
    }
}
