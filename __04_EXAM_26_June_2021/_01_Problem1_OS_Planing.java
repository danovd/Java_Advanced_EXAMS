package __04_EXAM_26_June_2021;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _01_Problem1_OS_Planing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       // STACK
        ArrayDeque<Integer> tasks = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tasks::push);
        // QUEUE
        ArrayDeque<Integer> threads = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        int taskToKill = Integer.parseInt(sc.nextLine());

        while(!threads.isEmpty()){
            int task = tasks.peek();
            int thread = threads.peek();
            if(task == taskToKill){
                break;
            }else{
               if(thread >= task){
                   threads.poll();
                   tasks.pop();
               }else{
                   threads.poll();
               }
            }
        }
        System.out.println("Thread with value " + threads.peek() + " killed task " + taskToKill);
        threads.forEach(e -> System.out.print(e + " "));
    }
}
