package __03_EXAM_18_August_2021;

import java.util.Scanner;

public class _02_Formula_One {
    static int rowOfPlayer = 0;
    static int colOfPlayer = 0;
    static boolean isReachFin = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeOfMatrix = Integer.parseInt(sc.nextLine());
        int numberCommands = Integer.parseInt(sc.nextLine());
        char [][] matrix = new char[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = sc.nextLine();
            matrix[i] = line.toCharArray();
            if(line.contains("P")){
                rowOfPlayer = i;
                colOfPlayer = line.indexOf('P');
            }
        }

        for (int i = 0; i < numberCommands; i++) {
            String command = sc.nextLine();
            move(command, matrix);
            if(isReachFin){
                break;
            };
        };
        String output = isReachFin
                ? "Well done, the player won first place!"
                : "Oh no, the player got lost on the track!";
        System.out.println(output);
        printMatrix(matrix);
    }
    private static void move(String command, char[][] matrix) {
int oldRow = rowOfPlayer;
int oldCol = colOfPlayer;

        if(command.equals("up")){
            rowOfPlayer -= 1;
        }else if(command.equals("down")){
            rowOfPlayer += 1;
        }else if(command.equals("left")){
            colOfPlayer -= 1;
        }else if(command.equals("right")){
            colOfPlayer += 1;
        }
        int [] newRowAndCol = modifyRowAndCol(rowOfPlayer, colOfPlayer, matrix);
        rowOfPlayer = newRowAndCol[0];
        colOfPlayer = newRowAndCol[1];


            matrix[oldRow][oldCol] = '.';

        if(matrix[rowOfPlayer][colOfPlayer] == 'F'){
            isReachFin = true;
            matrix[rowOfPlayer][colOfPlayer] = 'P';
        }else if(matrix[rowOfPlayer][colOfPlayer] == '.'){
            matrix[rowOfPlayer][colOfPlayer] = 'P';
        }else if(matrix[rowOfPlayer][colOfPlayer] == 'B'){
            int rowBonus = rowOfPlayer;
            int colBonus = colOfPlayer;
            matrix[oldRow][oldCol] = '.';
            move(command, matrix);
            matrix[rowBonus][colBonus] = 'B';
        }else if(matrix[rowOfPlayer][colOfPlayer] == 'T'){
            rowOfPlayer = oldRow;
            colOfPlayer = oldCol;
            matrix[rowOfPlayer][colOfPlayer] = 'P';
        };
    }

    private static int[] modifyRowAndCol(int row, int col, char[][] matrix) {
        int [] arr = new int[2];
        arr[0] = row;
        arr[1] = col;
        if(row >= matrix.length){
            arr[0] = 0;
        }else if(row == -1){
            arr[0] = matrix.length - 1;
        }else if(col >= matrix[row].length){
            arr[1] = 0;
        }else if(col == - 1){
            arr[1] = matrix[row].length - 1;
        };
        return arr;
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
