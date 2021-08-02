package cn.kylin.leetcode.数组;

/**
 * @Author: kylin
 * @Date: 2021/4/9 下午6:09
 */
public class 寻找旋转排序数组中的最小值_153 {


    public static void main(String[] args) {
        int[] nums = new int[]{5,1,1,2,3,4};



        System.out.println(findMin(nums));
    }


    public static int findMin(int[] nums) {
        int befor = nums[nums.length-1];
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (i == nums.length - 1) {
                // 已经是最后一个元素了
                if (value < befor) {
                    return value;
                } else {
                    return befor;
                }
            }
            if (befor > value) {
                return value;
            }
            befor = value;
        }
        return nums[nums.length-1];
    }
}
