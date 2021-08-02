package cn.kylin.leetcode.数组;

/**
 * @Author: kylin
 * @Date: 2021/4/13 下午5:28
 */
public class 两数相加_2 {


    public static void main(String[] args) {
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);
        ListNode l11 = new ListNode(1, l1);

        ListNode l6 = new ListNode(6);
        ListNode l5 = new ListNode(5, l6);
        ListNode l4 = new ListNode(4, l5);

        ListNode ll = addTwoNumbers(l11, l4);
        System.out.println("");
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        ListNode nextNext = null;

        int valNext = 0;
        while (null != l1 && null != l2) {
            int val = l1.val + l2.val + valNext;
            if (val >= 10) {
                valNext = val / 10;
                val = val % 10;
            } else {
                valNext = 0;
            }
            if (listNode == null) {
                listNode = nextNext = new ListNode(val);
            } else {
                nextNext.next = new ListNode(val);
                nextNext = nextNext.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        while (null != l1) {
            int val = l1.val + valNext;
            if (val >= 10) {
                valNext = val / 10;
                val = val % 10;
            } else {
                valNext = 0;
            }
            nextNext.next = new ListNode(val);
            nextNext = nextNext.next;
            l1 = l1.next;
        }

        while (null != l2) {
            int val = l2.val + valNext;
            if (val >= 10) {
                valNext = val / 10;
                val = val % 10;
            } else {
                valNext = 0;
            }
            nextNext.next = new ListNode(val);
            nextNext = nextNext.next;
            l2 = l2.next;
        }

        if (valNext > 0) {
            nextNext.next = new ListNode(valNext);
        }

        return listNode;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
