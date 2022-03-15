package cn.kylin.leetcode.剑指.剑指Offer51;

import java.util.Arrays;

/**
 * @Author: kylin
 * @Date: 2022/3/14 下午10:33
 */
public class 数组中的逆序对 {

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
     * <p>
     * <p>
     * 示例 1:
     * 输入: [7,5,6,4]
     * 输出: 5
     * <p>
     * <p>
     * 限制：
     * 0 <= 数组长度 <= 50000
     */


    public static void main(String[] args) {
        数组中的逆序对 smq = new 数组中的逆序对();
        smq.reversePairs(new int[]{10, 1, 2, 3, 4, 5});
//        smq.reversePairs1(new int[]{10, 1, 2, 3, 4, 5});
    }

    // 这个方法会超时，最差的情况下，空间复杂度是n*n
    public int reversePairs(int[] nums) {

        // 倒序，计算每个值的逆序对，把计算过的数据进行排序，再计算前一个值的逆序数
        // list逆序，后置小于当前
        DTO list = null;
        int listLength = 0;
        int result = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 当前值
            int value = nums[i];
            if (null == list) {
                list = new DTO(value);
                listLength++;
                continue;
            }

            // value和list中的数据比较，找到比自己小的第一个数，后面都是比自己小的；
            DTO dto = list;
            DTO pre = null;
            int whileSize = 0;
            while (dto != null) {
                if (dto.val < value) {
                    // 插入把数据插入当前位置
                    DTO iDTO = new DTO(value);
                    if (pre == null) {
                        list = iDTO;
                        list.next = dto;
                    } else {
                        pre.next = iDTO;
                        iDTO.next = dto;
                    }
                    break;
                }
                whileSize++;
                pre = dto;
                dto = dto.next;
            }

            if (whileSize == listLength) {
                // 数据中没有比自己小的（自己是最小的）
                if (null == pre) {
                    list.next = new DTO(value);
                } else {
                    pre.next = new DTO(value);
                }
            } else {
                result += (listLength - whileSize);
            }

            listLength++;
        }


        return result;
    }

    public class DTO {
        DTO next;
        int val;

        public DTO(int val) {
            this.val = val;
        }
    }
}
