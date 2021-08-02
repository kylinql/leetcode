package cn.kylin.leetcode.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: kylin
 * @Date: 2021/4/16 下午2:18
 */
public class 三数之和_15 {

    public static void main(String[] args) {

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();

        int[] group1 = Arrays.stream(nums).parallel().filter(n -> n > 0).toArray();
        int[] group2 = Arrays.stream(nums).parallel().filter(n -> n == 0).toArray();
        int[] group3 = Arrays.stream(nums).parallel().filter(n -> n < 0).toArray();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for(int num : nums){
            if(num>0){
                list1.add(num);
            }else if(num==0){
                list2.add(num);
            }else {
                list3.add(num);
            }
        }

        return result;
    }
}
