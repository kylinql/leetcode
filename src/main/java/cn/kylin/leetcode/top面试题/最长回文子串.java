package cn.kylin.leetcode.top面试题;

/**
 * @Author: kylin
 * @Date: 2021/12/17 下午7:01
 */
public class 最长回文子串 {
    public static void main(String[] args) {
        最长回文子串 obj = new 最长回文子串();
        obj.longestPalindrome("sadfas");
    }

    public String longestPalindrome(String s) {
        // 思路， 判断{a,b}是不是取决于a==b && {a-1, b-1}

        char[] chars = s.toCharArray();
        int length = chars.length;

        if (length == 1) {
            return s;
        }
        if (length == 2 && chars[0] == chars[1]) {
            return s;
        } else if (length == 2) {
            return chars[0] + "";
        }
        int[][] dp = new int[length + 5][length + 5];

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {

            }
        }


        return "";
    }
}
