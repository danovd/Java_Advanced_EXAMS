package __04_EXAM_26_June_2021;

import java.util.Scanner;

public class _02_Problem2_Python {
    static int food = 0;
    static int pRow = 0;            // Python Row
    static int pCol = 0;            // Python Col
    static int length = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = Integer.parseInt(sc.nextLine());

        String [] comands = sc.nextLine().split(", ");

        char [][] matrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            String line = sc.nextLine().replaceAll("\\s+", "");
            matrix[i] = line.toCharArray();
            if(line.contains("s")){
                pRow = i;
                pCol = line.indexOf("s");
            }
            for(char c : matrix[i]){
                if(c == 'f'){
                    food++;
                }
            }
        }

        for(String command : comands){
            if(command.equals("up")){
                movePython(matrix, pRow - 1, pCol);
            }else if(command.equals("down")){
                movePython(matrix, pRow + 1, pCol);
            }else if(command.equals("left")){
                movePython(matrix, pRow, pCol - 1);
            }else if(command.equals("right")){
                movePython(matrix, pRow, pCol + 1);
            }

            if(length == -1 || food == 0){
                break;
            }
        }

        if(food == 0){
            System.out.println("You win! Final python length is " + length);
        }else if(food > 0 && length != -1){
            System.out.println("You lose! There is still " + food + " food to be eaten.");
        }else{
            System.out.println("You lose! Killed by an enemy!");
        }

    }
    private static void movePython(char[][] matrix, int newRow, int newCol){
        if(isOutOfBounds(matrix, newRow, newCol)){
        int[] newIndexes = flipSnakeSide(matrix.length, newRow, newCol);
        newRow = newIndexes[0];
        newCol = newIndexes[1];
        }

        if(matrix[newRow][newCol] == 'e'){
            length = -1;
        }else if(matrix[newRow][newCol] == 'f'){
            length++;
            food--;
            matrix[newRow][newCol] = '*';
        }
        pRow = newRow;
        pCol = newCol;
    }

    private static int[] flipSnakeSide(int length, int r, int c) {
        int [] result = new int [2];
        if(r < 0){
            result[0] = length - 1;
            result[1] = c;
        }else if(r >= length){
            result[1] = c;
        }else if(c < 0){
            result[0] = r;
            result[1] = length - 1;
        }else{
            result[0] = r;
        }
        return result;
    }

    private static boolean isOutOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[r].length;
    }


}
