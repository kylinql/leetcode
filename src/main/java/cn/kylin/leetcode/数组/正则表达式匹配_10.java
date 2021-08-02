package cn.kylin.leetcode.数组;

/**
 * @Author: kylin
 * @Date: 2021/4/16 上午11:34
 */
public class 正则表达式匹配_10 {

    public static void main(String[] args) {

        // error 失败，暂时挂起
        System.out.println(isMatch("aa", "a"));
    }


    public static boolean isMatch(String s, String p) {

        // a*b*c*
        // abddds

        // 倒序比较
        char[] pChar = p.toCharArray();
        char[] sChar = s.toCharArray();
        int sidx = sChar.length - 1;
        int flagi = -1;
        for (int i = pChar.length - 1; i >= 0; i--) {
            if (pChar[i] != '*') {
                // 判断s串是否有数据
                if (sidx < 0) {
                    return false;
                }
                if (pChar[i] == '.' || pChar[i] == sChar[sidx]) {
                    // 匹配数据
                    sidx--;
                    continue;
                } else {
                    if (flagi != -1) {
                        // 回退到flagi重新是错
                        flagi--;
                        i = flagi;
                        continue;
                    }
                    return false;
                }
            }
            // pChar[i] == *
            i--;
            char pre = pChar[i];
            if (pre != '.') {
                while (sidx >= 0 && sChar[sidx] == pre) {
                    sidx--;
                }
                continue;
            }
            // pre = '.' 此时前方数据可以被任意匹配
            // 按照最小匹配原则，尝试继续匹配，遇到不匹配的操作，则回退到当前节点
            flagi = i;
        }

        if (sidx > 0) {
            return false;
        }
        return true;
    }
}
