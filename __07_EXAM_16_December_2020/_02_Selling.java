package __07_EXAM_16_December_2020;

import java.util.Scanner;

public class _02_Selling {
    static int startRow = 0;
    static int startCol = 0;
    static int money = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        char [][] matrix = new char[n][n];


        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            matrix[i] = line.toCharArray();
            if(line.contains("S")){
                startRow = i;
                startCol = line.indexOf('S');
            }
        }

        boolean isWithinBakery = true;

        while(money < 50 && isWithinBakery){
            String command = sc.nextLine();
            if(command.equals("up")){
               isWithinBakery = move(startRow - 1, startCol, matrix);
            }else if(command.equals("down")){
               isWithinBakery = move(startRow + 1, startCol, matrix);
            }else if(command.equals("left")){
                isWithinBakery = move(startRow , startCol - 1, matrix);
            }else if(command.equals("right")){
                isWithinBakery = move(startRow, startCol + 1, matrix);
            }
        }

String message = !isWithinBakery
        ? "Bad news, you are out of the bakery."
        : "Good news! You succeeded in collecting enough money!";

        System.out.println(message);
        System.out.println("Money: " + money);
        printMatrix(matrix);
    }

    private static boolean move(int newRol, int newCol, char[][] matrix) {
        matrix[startRow][startCol] = '-';

        if(isOutOfBounds(newRol, newCol, matrix)){
            return false;
        }
        char symbol = matrix[newRol][newCol];
        if(Character.isDigit(symbol)){
            money += symbol - '0';
        }else if(symbol == 'O'){
           matrix[newRol][newCol] = '-';
           int[] secondPillarLocation = findSecondPillar(matrix);
           newRol = secondPillarLocation[0];
           newCol = secondPillarLocation[1];
        }
        matrix[newRol][newCol] = 'S';
        startRow = newRol;
        startCol = newCol;
        return true;
    }

    private static int[] findSecondPillar(char[][] matrix) {
        int [] indexes = null;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 'O'){
                    indexes = new int[]{i, j};
                    break;
                }
            }
            if(indexes != null){
                break;
            }
        }
        return indexes;
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
