package __02_EXAM_23_October_2021;

import java.util.Scanner;

public class _02_Mouse_And_Cheese {
    static int rowOfMouse = 0;
    static int colOfMouse = 0;
    static int eatenCheese = 0;
    static boolean isOut = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        char [][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            matrix[i] = line.toCharArray();
            if(line.contains("M")){
                rowOfMouse = i;
                colOfMouse = line.indexOf('M');
            }
        }

        String command = sc.nextLine();
        while(!command.equals("end")){
            move(command, matrix);
            if(isOut){
                break;
            }
            command = sc.nextLine();
        }
        if(eatenCheese < 5){
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.\n", 5 - eatenCheese);
        }else{
            System.out.printf("Great job, the mouse is fed %d cheeses!\n", eatenCheese);
        };
        printMatrix(matrix);

    }

    private static void move(String command, char[][] matrix) {
        int oldPositionRow = rowOfMouse;
        int oldPositionCol = colOfMouse;

        if(command.equals("up")){
            rowOfMouse -= 1;
        }else if(command.equals("down")){
            rowOfMouse += 1;
        }else if(command.equals("left")){
            colOfMouse -= 1;
        }else if(command.equals("right")){
            colOfMouse += 1;
        }
        if(isOutOfBounds(rowOfMouse, colOfMouse, matrix)){
            System.out.println("Where is the mouse?");
            isOut = true;
        }else if(matrix[rowOfMouse][colOfMouse] == 'c'){
            eatenCheese++;
            matrix[rowOfMouse][colOfMouse] = 'M';
        }else if(matrix[rowOfMouse][colOfMouse] == 'B'){
            move(command, matrix);
            matrix[rowOfMouse][colOfMouse] = 'M';
        }
        matrix[oldPositionRow][oldPositionCol] = '-';

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
