package cn.kylin.leetcode.数组;

/**
 * @Author: kylin
 * @Date: 2021/4/15 下午4:30
 */
public class 寻找两个正序数组的中位数_4 {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int sumLength = nums1Length + nums2Length;

        int[] all = new int[sumLength];
        int n1idx = 0;
        int n2idx = 0;
        int idx = 0;
        while (true) {
            if (n1idx >= nums1Length && n2idx >= nums2Length) {
                break;
            }

            if (n1idx >= nums1Length) {
                // 只有n2了
                for (; n2idx < nums2Length; n2idx++) {
                    all[idx++] = nums2[n2idx];
                }
                break;
            }
            if (n2idx >= nums2Length) {
                // 只有n1了
                for (; n1idx < nums1Length; n1idx++) {
                    all[idx++] = nums1[n1idx];
                }
                break;
            }

            // 两者都有
            int v1 = nums1[n1idx];
            int v2 = nums2[n2idx];
            if (v1 >= v2) {
                all[idx++] = v2;
                n2idx++;
            } else {
                all[idx++] = v1;
                n1idx++;
            }
        }

        if (sumLength % 2 == 0) {
            // 中位数为两者取均值
            idx = sumLength / 2 - 1;
            return (all[idx] + all[idx + 1]) / 2.0;
        } else {
            // 中位数为 （sumLength-1)/2+1位置的数
            idx = (sumLength - 1) / 2;
            return all[idx];
        }
    }
}
