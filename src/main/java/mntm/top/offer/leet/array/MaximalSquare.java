package mntm.top.offer.leet.array;

/**
 * @program: perf
 * @description: 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * @author: yingjun
 * @create: 2020-07-06 19:57
 **/
public class MaximalSquare {

    /**
     * 暴力法
     * 1、遍历矩阵中的每个元素，每次遇到1，将该元素作为正方形左上角
     * 2、
     *
     * @param matrix
     * @return
     */
    public int solution(char[][] matrix) {

        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    maxSide = Math.max(maxSide, 1);
                    int currentMaxSide = Math.min(rows - i, columns - j);

                    for (int k = 1; k < currentMaxSide; k++) {
                        boolean isMax = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }

                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                isMax = false;
                                break;
                            }
                        }

                        if (isMax) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        int maxSquare = maxSide * maxSide;

        return maxSquare;
    }


    /**
     * 动态规划
     *
     * @param matrix
     * @return
     */
    public int dp(char[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxSide = 0;

        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {

                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }

                }

                maxSide = Math.max(maxSide, dp[i][j]);

            }
        }

        return maxSide * maxSide;
    }

}
