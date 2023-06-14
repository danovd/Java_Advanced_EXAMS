package __05_EXAM_14_April_2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _02_Cooking_Journey {
    static int rowOfPlayer = 0;
    static int colOfPlayer = 0;
    static List<int[]> listOfPillars = new ArrayList<>();
    static boolean isOut = false;
    static int moneyCollected = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeOfMatrix = Integer.parseInt(sc.nextLine());

        char [][] matrix = new char[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = sc.nextLine();
            matrix[i] = line.toCharArray();
            if(line.contains("S")){
                rowOfPlayer = i;
                colOfPlayer = line.indexOf('S');
            }
            if(line.contains("P")){
                for (int j = 0; j < line.length(); j++) {
                    if(line.charAt(j) == 'P'){
                        int [] positionsOfPillar = new int[]{i, j};
                        listOfPillars.add(positionsOfPillar);
                    }
                }
            }
        }
        while(!isOut && moneyCollected < 50){
            String command = sc.nextLine();
            move(command, matrix);
        }


    if(isOut){
        System.out.println("Bad news! You are out of the pastry shop.");
    }else if(moneyCollected >= 50){
        System.out.println("Good news! You succeeded in collecting enough money!");
    };
        System.out.printf("Money: %d\n", moneyCollected);
        printMatrix(matrix);
    };


    private static void move(String command, char[][] matrix) {
        int newRow = rowOfPlayer;
        int newCol = colOfPlayer;

        if(command.equals("up")){
            newRow -= 1;
        }else if(command.equals("down")){
            newRow += 1;
        }else if(command.equals("left")){
            newCol -= 1;
        }else if(command.equals("right")){
            newCol += 1;
        };
        if(isOutOfBounds(newRow, newCol, matrix)){
            matrix[rowOfPlayer][colOfPlayer] = '-';
            isOut = true;
        }else{
            if(Character.isDigit(matrix[newRow][newCol])){
                String digit = "" + matrix[newRow][newCol];
                moneyCollected += Integer.parseInt(digit);
                matrix[newRow][newCol] = 'S';
                matrix[rowOfPlayer][colOfPlayer] = '-';
                rowOfPlayer = newRow;
                colOfPlayer = newCol;
            }else if(matrix[newRow][newCol] == 'P'){
               int rowOfOtherPillar = newRow;
               int colOfOtherPillar = newCol;
                for(int [] arr : listOfPillars){
                   if(arr[0] != newRow && arr[1] != newCol){
                       rowOfOtherPillar = arr[0];
                       colOfOtherPillar = arr[1];
                   }
               };
               matrix[newRow][newCol] = '-';
               matrix[rowOfPlayer][colOfPlayer] = '-';
               matrix[rowOfOtherPillar][colOfOtherPillar] = 'S';
               rowOfPlayer = rowOfOtherPillar;
               colOfPlayer = colOfOtherPillar;
            }else if(matrix[newRow][newCol] == '-'){
                matrix[newRow][newCol] = 'S';
                matrix[rowOfPlayer][colOfPlayer] = '-';
                rowOfPlayer = newRow;
                colOfPlayer = newCol;
            }


        }

    }

    private static boolean isOutOfBounds(int row, int col, char[][] matrix) {
        return row >= matrix.length || row < 0 || col >= matrix.length || col < 0;
    }

    private static void printMatrix(char[][] matrix){
        for(char[] arr : matrix){
            for(char symbol : arr){
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
