package cn.kylin.leetcode.top面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kylin
 * @Date: 2021/1/29 下午4:05
 */
public class 两数之和_1 {

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> needMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            Object find = needMap.getOrDefault(value, null);
            if (null != find) {
                result[0] = i;
                result[1] = (int) find;
                break;
            }
            needMap.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        twoSum(nums, target);
    }
}
