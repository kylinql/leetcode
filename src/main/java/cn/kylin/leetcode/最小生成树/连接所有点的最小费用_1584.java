package cn.kylin.leetcode.最小生成树;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.*;

/**
 * @Author: kylin
 * @Date: 2021/1/19 上午10:47
 */
public class 连接所有点的最小费用_1584 {

    /**
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * <p>
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * <p>
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
     * 输出：20
     * 解释：
     * <p>
     * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
     * 注意到任意两个点之间只有唯一一条路径互相到达。
     * 示例 2：
     * <p>
     * 输入：points = [[3,12],[-2,5],[-4,1]]
     * 输出：18
     * 示例 3：
     * <p>
     * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
     * 输出：4
     * 示例 4：
     * <p>
     * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
     * 输出：4000000
     * 示例 5：
     * <p>
     * 输入：points = [[0,0]]
     * 输出：0
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= points.length <= 1000
     * -106 <= xi, yi <= 106
     * 所有点 (xi, yi) 两两不同。
     */

    public static void main(String[] args) {
        String str = "[[3,12],[-2,5],[-4,1]]";
        str = "[[0,0],[2,2],[3,10],[5,2],[7,0]]";
        JSONArray jsonArray = JSON.parseArray(str);
        int[][] points = new int[5][2];
        int i = 0;
        for (Object object : jsonArray) {
            JSONArray jsonObject = (JSONArray) object;
            int x = (Integer) jsonObject.get(0);
            int y = (Integer) jsonObject.get(1);
            points[i][0] = x;
            points[i][1] = y;
            i++;
        }
        System.out.println(minCostConnectPoints(points));
    }

    /**
     * 最小生成树
     * <p>
     * - kruskal
     * - 从最小边开始遍历，遇到重复或者回路则跳过，边数等于n-1则结束
     *
     * @param points
     * @return
     */
    public static int minCostConnectPoints(int[][] points) {
        int length = points.length;

        // 点阵map 记录每个点与其他点的间距，即每条线的距离  map[i][j] = map[j][i]
        int[][] map = new int[length][length];
        int[] fatherId = new int[length];// 每个点的父亲节点


        List<Edge> edgelist = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int xi = points[i][0];
            int yi = points[i][1];
            fatherId[i] = i;

            for (int j = i; j < length; j++) {
                int xj = points[j][0];
                int yj = points[j][1];
                int result = Math.abs(xi - xj) + Math.abs(yi - yj);
                map[i][j] = result;
                map[j][i] = result;

                Edge edge = new Edge(result, i, j);
                edgelist.add(edge);
            }
        }

        edgelist.sort(Comparator.comparingInt(o -> o.len));


        int line = 0;
        int total = 0;
        for (Edge edge : edgelist) {
            int cost = edge.len;
            int i = edge.x; // 第i个点
            int j = edge.y; // 第j个点

            // 判断这两个点是否有相同顶点
            if (getFather(i, fatherId) != getFather(j, fatherId)) {
                // 可以连接 修改某一个键的father为另一个键
                int iFather = getFather(i, fatherId);
                fatherId[iFather] = j;
                line++;
                total += cost;
            }
            if (line == length - 1) {
                break;
            }
        }

        return total;
    }

    private static int getFather(int i, int[] fatherId) {
        if (fatherId[i] == i) {
            return i;
        }
        return getFather(fatherId[i], fatherId);
    }
}

class Edge {
    int len, x, y;

    public Edge(int len, int x, int y) {
        this.len = len;
        this.x = x;
        this.y = y;
    }
}
