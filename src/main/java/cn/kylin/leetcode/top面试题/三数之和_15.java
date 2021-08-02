package cn.kylin.leetcode.top面试题;

import java.util.*;

/**
 * @Author: kylin
 * @Date: 2021/4/1 下午7:02
 */
public class 三数之和_15 {

    public static void main(String[] args) {


    }


    public List<List<Integer>> threeSum(int[] nums) {
//[-1,0,1,2,-1,-4]
//        -4, -1, -1, -1,  0, 1, 2,
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();

        Integer a = null;
        for (int i = 0; i < nums.length; i++) {
            if (Objects.equals(a, nums[i])) {
                continue;
            }
            a = nums[i];
            if (a > 0) {
                break;
            }
            Integer b = null;
            for (int j = i + 1; j < nums.length; j++) {
                if (Objects.equals(b, nums[j])) {
                    continue;
                }
                b = nums[j];
                if (a + b > 0) {
                    break;
                }
                boolean flag = false;// 标记是否命中
                Integer c = null;
                for (int k = nums.length - 1; k > j; k--) {
                    if (Objects.equals(c, nums[k])) {
                        continue;
                    }
                    c = nums[k];
                    if ((a + b + c) == 0) {
                        flag = true;
                        break;
                    }
                    if ((a + b + c) < 0) {
                        break;
                    }
                }
                if (flag) {
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    result.add(list);
                }
            }
        }

        return result;

//        for (int i = 0; i < nums.length; i++) {
//
//            int valuei = 0 - nums[i];
//
//            for (int j = i + 1; j < nums.length; j++) {
//                int valuej = valuei - nums[j];
//
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (nums[k] == valuej) {
//                        List<Integer> list = new ArrayList<>();
//                        list.add(nums[i]);
//                        list.add(nums[j]);
//                        list.add(nums[k]);
//                        list.sort(Comparator.comparingInt(a -> a));
//                        String numStr = list.get(0).toString() + list.get(1).toString() + list.get(2).toString();
//                        if (strSet.contains(numStr)) {
//                            continue;
//                        }
//                        strSet.add(numStr);
//                        result.add(list);
//                    }
//                }
//            }
//        }
//
//        return result;
    }

}
