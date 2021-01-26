package cn.kylin.leetcode.最小生成树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 找到最小生成树里的关键边和伪关键边_1489 {

    /**
     * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
     * <p>
     * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
     * <p>
     * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
     * 输出：[[0,1],[2,3,4,5]]
     * 解释：上图描述了给定图。
     * 下图是所有的最小生成树。
     * <p>
     * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
     * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
     * 示例 2 ：
     * <p>
     * <p>
     * <p>
     * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
     * 输出：[[],[0,1,2,3]]
     * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
     *  
     * <p>
     * 提示：
     * <p>
     * 2 <= n <= 100
     * 1 <= edges.length <= min(200, n * (n - 1) / 2)
     * edges[i].length == 3
     * 0 <= fromi < toi < n
     * 1 <= weighti <= 1000
     * 所有 (fromi, toi) 数对都是互不相同的。
     */

    public static void main(String[] args) {

        int n = 5;
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}};
        // [[0,1],[2,3,4,5]]

        n = 6;
        int[][] edges1 = {{0, 1, 1}, {1, 2, 1}, {0, 2, 1}, {2, 3, 4}, {3, 4, 2}, {3, 5, 2}, {4, 5, 2}};
        //预期：
        //[[3],[0,1,2,4,5,6]]

        n = 4;
        int[][] edges2 = {{0, 1, 1}, {0, 3, 1}, {0, 2, 1}, {1, 2, 1}, {1, 3, 1}, {2, 3, 1}};
        //预期：
        //[[],[0,1,2,3,4,5]]

        n = 6;
        int[][] edges3 = {{0, 1, 2}, {0, 2, 5}, {2, 3, 5}, {1, 4, 4}, {2, 5, 5}, {4, 5, 2}};// todo..
        // 预期：
        //[[0,2,3,5],[1,4]]

        n = 14;
        int[][] edges4 = {{0, 1, 13}, {0, 2, 6}, {2, 3, 13}, {3, 4, 4}, {0, 5, 11}, {4, 6, 14}, {4, 7, 8}, {2, 8, 6}, {4, 9, 6}, {7, 10, 4}, {5, 11, 3}, {6, 12, 7}, {12, 13, 9}, {7, 13, 2}, {5, 13, 10}, {0, 6, 4}, {2, 7, 3}, {0, 7, 8}, {1, 12, 9}, {10, 12, 11}, {1, 2, 7}, {1, 3, 10}, {3, 10, 6}, {6, 10, 4}, {4, 8, 5}, {1, 13, 4}, {11, 13, 8}, {2, 12, 10}, {5, 8, 1}, {3, 7, 6}, {7, 12, 12}, {1, 7, 9}, {5, 9, 1}, {2, 13, 10}, {10, 11, 4}, {3, 5, 10}, {6, 11, 14}, {5, 12, 3}, {0, 8, 13}, {8, 9, 1}, {3, 6, 8}, {0, 3, 4}, {2, 9, 6}, {0, 11, 4}, {2, 5, 14}, {4, 11, 2}, {7, 11, 11}, {1, 11, 6}, {2, 10, 12}, {0, 13, 4}, {3, 9, 9}, {4, 12, 3}, {6, 7, 10}, {6, 8, 13}, {9, 11, 3}, {1, 6, 2}, {2, 4, 12}, {0, 10, 3}, {3, 12, 1}, {3, 8, 12}, {1, 8, 6}, {8, 13, 2}, {10, 13, 12}, {9, 13, 11}, {2, 11, 14}, {5, 10, 9}, {5, 6, 10}, {2, 6, 9}, {4, 10, 7}, {3, 13, 10}, {4, 13, 3}, {3, 11, 9}, {7, 9, 14}, {6, 9, 5}, {1, 5, 12}, {4, 5, 3}, {11, 12, 3}, {0, 4, 8}, {5, 7, 8}, {9, 12, 13}, {8, 12, 12}, {1, 10, 6}, {1, 9, 9}, {7, 8, 9}, {9, 10, 13}, {8, 11, 3}, {6, 13, 7}, {0, 12, 10}, {1, 4, 8}, {8, 10, 2}};

        findCriticalAndPseudoCriticalEdges(n, edges4);
    }

    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        // 先通过克鲁斯卡尔 找到最小生成树，再依次去掉一个点，尝试生成新的最小生成树，汇总生成树中的唯一值作为
        // 关键边，其他边为非关键边 [返回的是第几条边，边的编号]
        int lineSize = edges.length; // 边个数
        int lastLineSize = n - 1; // 最后需要的边

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> mastLine = new ArrayList<>();
        List<Integer> otherLine = new ArrayList<>();

        // 全关键边的特殊场景优化
        if (lineSize == lastLineSize) {
            // 所有边都是关键边
            for (int i = 0; i < lastLineSize; i++) {
                mastLine.add(i);
            }
            result.add(mastLine);
            result.add(otherLine);
            return result;
        }

        int[][] newLine = new int[lineSize][4];// 第四位存放当前边的编号
        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < 3; j++) {
                newLine[i][j] = edges[i][j];
            }
            newLine[i][3] = i;
        }

        Arrays.sort(newLine, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // newLine 从小打到排序
        int[] node = new int[n];
        for (int i = 0; i < n; i++) {
            node[i] = i;
        }

        int bast = 0; // 最小树
        int useLine = 0;
        for (int i = 0; i < lineSize; i++) {
            int start = newLine[i][0];
            int end = newLine[i][1];
            int weight = newLine[i][2];

            if (union(start, end, node)) {
                // 可以连线
                bast += weight;
                useLine++;
            }

            if (useLine == lastLineSize) {
                break;
            }

        }

        // 找到最小图的结构 下面就是找关键线和非关键线
        for (int j = 0; j < lineSize; j++) {
            int min = 0;// 过滤掉条线之后的最小生成树
            int jLine = 0;
            for (int i = 0; i < n; i++) {
                node[i] = i;
            }
            // 判断是否是必须线 （不使用该线， 若不能连通或连通指大于最小值，则为必须边）
            for (int i = 0; i < lineSize; i++) {
                if (i == j) {
                    continue;
                }
                if (union(newLine[i][0], newLine[i][1], node)) {
                    min += newLine[i][2];
                    jLine++;
                }
            }

            if (jLine != (lastLineSize)) {
                // 不连通
                mastLine.add(newLine[j][3]);
                continue;
            } else if (min > bast) {
                // 超过了最小值
                mastLine.add(newLine[j][3]);
                continue;
            }

            // 判断是否是非关键边， 使用该边，指连通值不能变大
            for (int i = 0; i < n; i++) {
                node[i] = i;
            }
            jLine = 1;
            union(newLine[j][0], newLine[j][1], node);
            min = newLine[j][2];
            for (int i = 0; i < lineSize; i++) {
                if (i == j) {
                    continue;
                }
                if (union(newLine[i][0], newLine[i][1], node)) {
                    min += newLine[i][2];
                    jLine++;
                }
                if (min > bast) {
                    break;
                }
            }
            if (min == bast) {
                otherLine.add(newLine[j][3]);
            }

        }

        result.add(mastLine);
        result.add(otherLine);
        return result;
    }

    private static boolean union(int start, int end, int[] node) {
        int startFather = getFather(start, node);
        int endFather = getFather(end, node);
        if (startFather == endFather) {
            return false;
        }
        if (startFather > endFather) {
            node[node[end]] = startFather;
        } else {
            node[node[start]] = endFather;
        }
        return true;
    }

    private static int getFather(int i, int[] node) {
        if (node[i] == i) {
            return i;
        }
        return getFather(node[i], node);
    }
}
