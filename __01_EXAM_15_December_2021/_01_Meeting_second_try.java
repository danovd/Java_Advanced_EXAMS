package __01_EXAM_15_December_2021;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _01_Meeting_second_try {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
// stack - males
        ArrayDeque<Integer> males = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(males::push);
// queue - females
        ArrayDeque<Integer> females = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        int matches = 0;
while(!females.isEmpty() && !males.isEmpty()){

int female = females.peek();
int male = males.peek();

if(female == 0){
    females.poll();
}else if(male == 0){
    males.pop();
}else if(female == male){
    females.poll();
    males.pop();
    matches++;
}else{
    females.poll();
    int value = males.pop();
    value -= 2;
    if(value < 0){
        value = 0;
    }
    males.push(value);
}



}

        System.out.println("Matches: " + matches);
    String maleOutput = males.isEmpty() ? "Males left: none"
            : "Males left: " +
             males.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
        System.out.println(maleOutput);

        String femaleOutput = females.isEmpty() ? "Females left: none"
                : "Females left: " +
                females.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
        System.out.println(femaleOutput);
    }
}
