package mntm.top.offer.leet.array;

/**
 * @program: perf
 * @description: 斐波那契数列
 * @author: yingjun
 * @create: 2020-06-15 18:39
 **/
public class Fib {

    public int solution(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        return solution(n - 1) + solution(n - 2);
    }

    public static int dynamic(int n){
        if (n < 1){
            return 0;
        }

        int[] memorandum = new int[n+1];

        return memo(memorandum, n);
    }

    public static int memo(int[] memorandom, int n){
        if(n == 1 || n == 2){
            return 1;
        }

        if(memorandom[n] != 0) {
            return memorandom[n];
        }

        memorandom[n] = memo(memorandom, n - 1) + memo(memorandom, n - 2);

        return memorandom[n];
    }

    public static void main(String[] args) {
        System.out.println(dynamic(3));
    }

}
