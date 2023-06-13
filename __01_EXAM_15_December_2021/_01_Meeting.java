package __01_EXAM_15_December_2021;

import java.util.*;
import java.util.stream.Collectors;

public class _01_Meeting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stack::push);

        ArrayDeque<Integer> queue = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        int matches = 0;
        while(!stack.isEmpty() && !queue.isEmpty()){
            int first = stack.peek();
            int second = queue.peek();
            if(first == 0){
                stack.pop();
            }else if(second == 0){
                queue.poll();
            }else if(first % 25 == 0){
                stack.pop();
                if(!stack.isEmpty()){
                    stack.pop();
                };
            }else if(second % 25 == 0){
                queue.poll();
                if(!queue.isEmpty()){
                    queue.poll();
                };
            }
            else if(first == second){
                stack.pop();
                queue.poll();
                matches++;
            }else if(first != second){
                queue.poll();
                int x = stack.pop();
                x -= 2;
                stack.push(Math.max(x, 0));

            }
        }
        System.out.println("Matches: " + matches);
        if(stack.isEmpty()){
            System.out.println("Males left: none");
        }else{
            List<String> list = new ArrayList<>();
            while(!stack.isEmpty()){
                list.add(stack.pop().toString());
            }
            String result = String.join(", ", list);
            System.out.println("Males left: " + result);
        }
        if(queue.isEmpty()){
            System.out.println("Females left: none");
        }else{
            List<String>list = new ArrayList<>();
            while(!queue.isEmpty()){
                list.add (queue.poll().toString());
            };
            String result = String.join(", ", list);
            System.out.println("Females left: " + result);

        }
    }
}
