package cn.kylin.leetcode.top面试题;

/**
 * @Author: kylin
 * @Date: 2021/12/17 上午11:21
 */
public class 无重复字符的最长子串_3 {


    public static void main(String[] args) {
        无重复字符的最长子串_3 obj = new 无重复字符的最长子串_3();
        obj.lengthOfLongestSubstring("adsfasdfcxxsfavihuudgm");
    }

    public int lengthOfLongestSubstring(String s) {
        // 思路 滑动窗口  两个指针，一个指向开始，一个指向结尾，若出现重复内容，则前置指针向后，若指针重合则后置指针继续向后
        int pointA = 0;
        int pointB = 0;
        int max = 0;
        while (pointA < s.length()) {

            if (pointB >= s.length()) {
                break;
            }

            String str = s.substring(pointA, pointB);
            char charB = s.charAt(pointB);
            if (str.contains(charB + "")) {
                pointA++;
                continue;
            }
            max = Math.max(str.length() + 1, max);
            pointB++;
        }
        return max;
    }
}
