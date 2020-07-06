package mntm.top.offer.leet;

/**
 * @program: perf
 * @description:
 * @author: yingjun
 * @create: 2020-07-06 21:28
 **/
public class IntegerReverse {

    public int solution(int x) {

        int result = 0;
        int i = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;
            //判断是否 大于 最大32位整数
            if (result > 214748364 || (result == 214748364 && temp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (result < -214748364 || (result == -214748364 && temp < -8)) {
                return 0;
            }

            result = result * 10 + temp;

        }

        return result;

    }
}
