package __11_EXAM_17_December_2019;

import java.util.Scanner;

public class _02_Present_Delivery {
    static int rowOfSanta = 0;
    static int colOfSanta = 0;
    static int numberNiceKids = 0;
    static boolean isAllDelivered = false;
    static int deliveredPresents = 0;
    static int countPresents = 0;
    static int leftPresents = 0;
    static boolean isAllNiceKidsReceivedAPresent = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        countPresents = Integer.parseInt(sc.nextLine());
        leftPresents = countPresents;
        int sizeOfMatrix = Integer.parseInt(sc.nextLine());

        char [][] matrix = new char[sizeOfMatrix][sizeOfMatrix];


        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < line.length(); r++) {
                char current = line.charAt(r);
                if(current != ' '){
                    sb.append(current);
                }
            }

            matrix[i] = sb.toString().toCharArray();

            for (int j = 0; j < matrix[i].length; j++) {
                    if(matrix[i][j] == 'S'){
                        rowOfSanta = i;
                        colOfSanta = j;
                    }else if(matrix[i][j] == 'V'){
                        numberNiceKids++;
                    }
            }
        }
        int numberCommands = countPresents;

        //   printMatrix(matrix);
        String command = sc.nextLine();
       while(numberCommands-- > 0 && !command.equals("Christmas morning")){
            move(command, matrix);
            if(isAllDelivered || leftPresents == 0){
                break;
            }
           command = sc.nextLine();
       }

    if(leftPresents == 0){
        System.out.println("Santa ran out of presents!");
    }
    printMatrix(matrix);
    if(isAllNiceKidsReceivedAPresent){
        System.out.printf("Good job, Santa! %d happy nice kid/s.\n", deliveredPresents);
    }else{
        System.out.printf("No presents for %d nice kid/s.", numberNiceKids - deliveredPresents);
    }

    }
    private static void move(String command, char[][] matrix) {
        int newRow = rowOfSanta;
        int newCol = colOfSanta;

        if(command.equals("up")){
            newRow -= 1;
        }else if(command.equals("down")){
            newRow += 1;
        }else if(command.equals("left")){
            newCol -= 1;
        }else if(command.equals("right")){
            newCol += 1;
        };

        if(!isOutOfBounds(newRow, newCol, matrix)){
           if(matrix[newRow][newCol] == '-' || matrix[newRow][newCol] == 'X'){
            changeRows(newRow, newCol, matrix);
           }else if(matrix[newRow][newCol] == 'V'){
               changeRows(newRow, newCol, matrix);
               deliveredPresents++;
               leftPresents--;
               if(deliveredPresents == countPresents){
                   isAllDelivered = true;
               }
               if(deliveredPresents == numberNiceKids){
                   isAllNiceKidsReceivedAPresent = true;
               }
           }else if(matrix[newRow][newCol] == 'C'){
                if(!isOutOfBounds(newRow -1, newCol, matrix)){
                    matrix[newRow-1][newCol] = '-';
                }if(!isOutOfBounds(newRow + 1, newCol, matrix)){
                   matrix[newRow+1][newCol] = '-';
               }if(!isOutOfBounds(newRow, newCol - 1, matrix)){
                   matrix[newRow][newCol - 1] = '-';
               }if(!isOutOfBounds(newRow, newCol + 1, matrix)){
                   matrix[newRow][newCol + 1] = '-';
               }

                matrix[newRow][newCol] = 'S';
                rowOfSanta = newRow;
                colOfSanta = newCol;
           }

        }
    }

    private static void changeRows(int newRow, int newCol, char[][] matrix) {
        matrix[newRow][newCol] = 'S';
        matrix[rowOfSanta][colOfSanta] = '-';
        rowOfSanta = newRow;
        colOfSanta = newCol;
    }


    private static boolean isOutOfBounds(int row, int col, char[][] matrix) {
        return row >= matrix.length || row < 0 || col >= matrix.length || col < 0;
    }
    private static void printMatrix(char[][] matrix){
        for(char[] arr : matrix){
            for(char symbol : arr){
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }
}
