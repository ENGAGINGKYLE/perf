package mntm.top.offer.finger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: perf
 * @description: 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * @author: yingjun
 * @create: 2020-04-27 12:18
 **/
public class FindContinuousSequence {

    public int[][] solution1(int target) {

        List<int[]> result = new ArrayList<>();
        int i = 1;

        while (target > 0) {
            target -= i++;
            if (target > 0 && target % i == 0) {
                int[] array = new int[i];
                for (int k = target / i, j = 0; k < target / i + i; k++, j++) {
                    array[j] = k;
                }
                result.add(array);
            }
        }
        Collections.reverse(result);
        return result.toArray(new int[0][]);
    }

    public static int[][] solution2(int target) {

        int low = 1;
        int high = 2;
        int sum = low + high;
        List<int[]> result = new ArrayList<>();

        while (low < high && high < target) {

            if (sum < target) {
                high++;
                sum += high;
            } else if (sum > target) {
                sum -= low;
                low++;
            } else {
                int[] subResult = new int[high - low + 1];
                int j = low;
                for (int i = 0; i < subResult.length; i++, j++) {
                    subResult[i] = j;
                }

                result.add(subResult);
                sum -= low;
                low++;

            }

        }
        int[][] outs = new int[result.size()][];

        return result.toArray(outs);
    }

    public static int[][] solution3(int target) {

        List<int[]> result = new ArrayList<>();

        int i = 1;

        while ((i * i + i) / 2 < target) {

            if ((target - ((i * i + i) / 2)) % (i + 1) == 0) {

                int first = (target - ((i * i + i) / 2)) / (i + 1);

                int[] subResult = new int[i + 1];
                int j = first;
                for (int k = 0; k < subResult.length; k++, j++) {
                    subResult[k] = j;
                }
                result.add(subResult);
            }

            i++;
        }
        Collections.reverse(result);

        int[][] outs = new int[result.size()][];

        return result.toArray(outs);
    }


    public static void main(String[] args) {

        solution3(15);
    }

}
