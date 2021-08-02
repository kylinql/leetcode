package cn.kylin.leetcode.数组;

/**
 * @Author: kylin
 * @Date: 2021/4/14 下午3:04
 */
public class 无重复字符的最长子串_3 {
    /**
     * 解题思路：
     * 遍历字符串，记录当前字符之前是否出现过，若出现过，则前置指针跳到最近一次出现的位置，并计算子串长度
     * @param s
     * @return
     */

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int left = 0;
        int[] codeArray = new int[256];
        for (int i = 0; i < codeArray.length; i++) {
            codeArray[i] = -1;// 初始化
        }

        for (int i = 0; i < chars.length; i++) {
            char iChar = chars[i];
            int arrayValue = codeArray[iChar]; //校验是否出现过，返回最近出现的下标
            if (arrayValue > -1 && left <= arrayValue) {
                // 之前已经出现过
                left = arrayValue + 1;
            }

            codeArray[iChar] = i;

            int length = i - left + 1;
            maxLength = length > maxLength ? length : maxLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";
        int a = lengthOfLongestSubstring(str);
    }
}
