package mntm.top.offer.finger;

import java.util.Arrays;

/**
 * @program: perf
 * @description: 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * @author: yingjun
 * @create: 2020-04-27 16:23
 **/
public class ReversePairs {

    //暴力破解
    public int solution1(int[] nums) {

        int result = 0;

        for (int oneLevel = 0; oneLevel < nums.length; oneLevel++) {
            for (int secondLevel = oneLevel + 1; secondLevel < nums.length; secondLevel++) {
                if (nums[oneLevel] > nums[secondLevel]) {
                    result++;
                }
            }
        }

        return result;
    }

    //归并排序的副产物
    public  int solution2(int[] nums) {
        mergingSort(nums);
        return count;
    }


    private int count = 0;
    public  int[] mergingSort(int[] arr) {
        if (arr.length <= 1) return arr;

        int num = arr.length >> 1;
        int[] leftArr = Arrays.copyOfRange(arr, 0, num);
        int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
        System.out.println("split two array: " + Arrays.toString(leftArr) + " And " + Arrays.toString(rightArr));
        return mergeTwoArray(mergingSort(leftArr), mergingSort(rightArr),num);      //不断拆分为最小单元，再排序合并
    }

    private  int[] mergeTwoArray(int[] arr1, int[] arr2, int mid) {
        int i = 0, j = 0, k = 0;
        int[] result = new int[arr1.length + arr2.length];  //申请额外的空间存储合并之后的数组
        while (i < arr1.length && j < arr2.length) {//选取两个序列中的较小值放入新数组

            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
                count = count + mid - i;
            }

        }
        while (i < arr1.length) {     //序列1中多余的元素移入新数组
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {     //序列2中多余的元素移入新数组
            result[k++] = arr2[j++];
        }
        System.out.println("Merging: " + count);
        return result;
    }


    public int solution3(int[] nums){
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7};

    }

}
