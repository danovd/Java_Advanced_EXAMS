package MY_EXAM_19_02_2022;

import java.util.Collection;
import java.util.Scanner;

public class Matrix {
    static int rowOfWhite = 0;
    static int colOfWhite = 0;
    static int rowOfBlack = 0;
    static int colOfBlack = 0;

    static boolean isWhiteWin = false;
    static boolean isBlackWin = false;
    static boolean isPromotedWhite = false;
    static boolean isPromotedBlack = false;

    public static void main(String[] args) {


        String [][] coordinates = new String[][] { {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
                {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
                {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
                {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
                {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
                {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
                {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
                {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"}


        };


     //   for (int i = 0; i < coordinates.length; i++) {
  //          for (int j = 0; j < coordinates[i].length; j++) {
   //             String coordinate = "";
     //       }
    //    }



        Scanner sc = new Scanner(System.in);
        char [][] matrix = new char[8][8];
        for (int i = 0; i < 8; i++) {
            String line = sc.nextLine();
            matrix[i] = new char[8];
            for (int j = 0; j < line.length(); j++) {
                matrix[i][j] = line.charAt(j);
                if(matrix[i][j] == 'w'){
                    rowOfWhite = i;
                    colOfWhite = j;
                };
                if(matrix[i][j] == 'b'){
                    rowOfBlack = i;
                    colOfBlack = j;
                }
            };
        };

        if(rowOfWhite == 0){
            isPromotedWhite = true;
        }
        if(rowOfBlack == 7){
            isPromotedBlack = true;
        }
        while(!isWhiteWin && !isBlackWin && !isPromotedWhite && !isPromotedBlack){

            moveWhite(matrix);
            if(checkIsThereAWinner()){
                break;
            };

            moveBlack(matrix);


        }

        //    printMatrix(matrix);

        if(isPromotedWhite){
            System.out.printf("Game over! White pawn is promoted to a queen at %s.\n", coordinates[rowOfWhite][colOfWhite]);
        }else if(isPromotedBlack){
            System.out.printf("Game over! Black pawn is promoted to a queen at %s.\n", coordinates[rowOfBlack][colOfBlack]);
        }else if(isWhiteWin){
            System.out.printf("Game over! White capture on %s.\n", coordinates[rowOfWhite][colOfWhite]);
        }else if(isBlackWin){
            System.out.printf("Game over! Black capture on %s.\n", coordinates[rowOfBlack][colOfBlack]);
        }



    }

    private static void moveBlack(char[][] matrix) {
        int newRow = rowOfBlack + 1;
        int colLeft = colOfBlack - 1;
        int colRight = colOfBlack + 1;

        boolean isFind = false;

        if(isInBounds(colLeft, matrix)){
            if(matrix[newRow][colLeft] == 'w'){
                matrix[rowOfBlack][colOfBlack] = '-';
                rowOfBlack = newRow;
                colOfBlack = colLeft;
                matrix[rowOfBlack][colOfBlack] = 'b';
                isBlackWin = true;
                isFind = true;
            }
        }else if(isInBounds(colRight, matrix)){
            if(matrix[newRow][colRight] == 'w'){
                matrix[rowOfBlack][colOfBlack] = '-';
                rowOfBlack = newRow;
                colOfBlack = colRight;
                matrix[rowOfBlack][colOfBlack] = 'b';
                isBlackWin = true;
                isFind = true;
            }
        }
        if(!isFind){
            matrix[rowOfBlack][colOfBlack] = '-';
            rowOfBlack = newRow;
            matrix[rowOfBlack][colOfBlack] = 'b';
        }
        if(rowOfBlack == 7){
            isPromotedBlack = true;
        }


    }

    private static void moveWhite(char[][] matrix) {
        int newRow = rowOfWhite - 1;
        int colLeft = colOfWhite - 1;
        int colRight = colOfWhite + 1;

        boolean isFind = false;

        if(isInBounds(colLeft, matrix)){
            if(matrix[newRow][colLeft] == 'b'){
                matrix[rowOfWhite][colOfWhite] = '-';
                rowOfWhite = newRow;
                colOfWhite = colLeft;
                matrix[rowOfWhite][colOfWhite] = 'w';
                isWhiteWin = true;
                isFind = true;
            }
        }else if(isInBounds(colRight, matrix)){
            if(matrix[newRow][colRight] == 'b'){
                matrix[rowOfWhite][colOfWhite] = '-';
                rowOfWhite = newRow;
                colOfWhite = colRight;
                matrix[rowOfWhite][colOfWhite] = 'w';
                isWhiteWin = true;
                isFind = true;
            }
        }
        if(!isFind){
            matrix[rowOfWhite][colOfWhite] = '-';
            rowOfWhite = newRow;
            matrix[rowOfWhite][colOfWhite] = 'w';
        }
        if(rowOfWhite == 0){
            isPromotedWhite = true;
        }

    }


    private static boolean checkIsThereAWinner() {
        return isWhiteWin || isBlackWin || isPromotedWhite || isPromotedBlack;
    }
    private static boolean isInBounds(int col, char[][] matrix) {
        return col < matrix.length && col >= 0;
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
