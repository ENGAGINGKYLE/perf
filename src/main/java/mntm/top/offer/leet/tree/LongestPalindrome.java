package mntm.top.offer.leet.tree;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: perf
 * @description: 最长回文子串
 *  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * @author: yingjun
 * @create: 2020-05-21 18:20
 **/
public class LongestPalindrome {

    public String dynamic(String s) {
        if(s.length() < 2){
            return s;
        }

        char[] chars = s.toCharArray();

        Boolean[][] dp = new Boolean[s.length()][s.length()];

        for(int i = 0; i < s.length(); i++ ){
            dp[i][i] = true;
        }

        int begin = 0;
        int maxLen = 1;

        for(int j = 1; j < s.length(); j++){
            for(int i = 0; i < j; i++){
                if(chars[i] == chars[j]){
                    if(j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                }else{
                    dp[i][j] = false;
                }

                if(dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public String  expandAroundCenter(String s){
        return null;
    }
}
