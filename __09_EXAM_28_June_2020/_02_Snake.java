package __09_EXAM_28_June_2020;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class _02_Snake {
    static int rowOfSnake = 0;
    static int colOfSnake = 0;
    static int foodEaten = 0;
    static boolean isOut = false;
    static List<int[]> burrows = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeOfMatrix = Integer.parseInt(sc.nextLine());

        char [][] matrix = new char[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = sc.nextLine();
            matrix[i] = new char[sizeOfMatrix];
            for (int j = 0; j < line.length(); j++) {
                    matrix[i][j] = line.charAt(j);
                    if(matrix[i][j] == 'S'){
                        rowOfSnake = i;
                        colOfSnake = j;
                    }
                    if(matrix[i][j] == 'B'){
                        int [] burrowPositions = new int[]{i, j};
                        burrows.add(burrowPositions);
                    }
            }
        }
     //   printMatrix(matrix);

        while (!isOut && foodEaten < 10){
        String command = sc.nextLine();
           move(command, matrix);
           if(isOut || foodEaten >= 10){
               break;
           }
        }
        if(isOut){
            System.out.println("Game over!");
        }else if(foodEaten >= 10){
            System.out.println("You won! You fed the snake.");
        }
        System.out.println("Food eaten: " + foodEaten);

        printMatrix(matrix);
    }

    private static void move(String command, char[][] matrix) {
        int newRow = rowOfSnake;
        int newCol = colOfSnake;

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
            matrix[rowOfSnake][colOfSnake] = '.';
            isOut = true;
        }else{
            if(matrix[newRow][newCol] == '*'){
                foodEaten++;
                changeRows(newRow, newCol, matrix);
            }else if(matrix[newRow][newCol] == '-' || matrix[newRow][newCol] == '.'){
                changeRows(newRow, newCol, matrix);
            }else if(matrix[newRow][newCol] == 'B'){
                for(int[] i : burrows){
                    if(i[0] == newRow && i[1] == newCol){
                        burrows.remove(i);
                    }
                }
                int rowOfOther = burrows.get(0)[0];
                int colOfOther = burrows.get(0)[1];
                matrix[rowOfSnake][colOfSnake] = '.';
                matrix[newRow][newCol] = '.';
                matrix[rowOfOther][colOfOther] = 'S';
                rowOfSnake = rowOfOther;
                colOfSnake = colOfOther;
            }




        }

    }

    private static void changeRows(int newRow, int newCol, char[][] matrix) {
        matrix[newRow][newCol] = 'S';
        matrix[rowOfSnake][colOfSnake] = '.';
        rowOfSnake = newRow;
        colOfSnake = newCol;
    }

    ;


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
