package com.ojas.multithreading;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixIV {
    public static void main(String[] args) {
        int[][] mat = {{1,2,3},
                       {4,5,6},
                       {7,8,9}};

        List<Integer> result = SpiralMatrixIV.spiralMatrix(mat, 3, 3);
        System.out.println(result);
    }
    public static List<Integer> spiralMatrix(int[][] mat, int row, int col) {
        int rStart = 0;
        int rEnd = row - 1;
        int cStart = 0;
        int cEnd = col - 1;

        List<Integer> result = new ArrayList<>();

        while (rStart <= rEnd && cStart <= cEnd) {

            // 1. Traverse the current top row
            for (int i = cStart; i <= cEnd; i++) {
                result.add(mat[rStart][i]);
            }
            rStart++;

            // 2. Traverse the current right column
            for (int i = rStart; i <= rEnd; i++) {
                result.add(mat[i][cEnd]);
            }
            cEnd--;

            // 3. Traverse the current bottom row
            // Check if we still have a valid row to traverse
            if (rStart <= rEnd) {
                for (int i = cEnd; i >= cStart; i--) {
                    result.add(mat[rEnd][i]);
                }
                rEnd--;
            }

            // 4. Traverse the current left column
            // Check if we still have a valid column to traverse
            if (cStart <= cEnd) {
                for (int i = rEnd; i >= rStart; i--) {
                    result.add(mat[i][cStart]);
                }
                cStart++;
            }
        }

        return result;
    }
}
