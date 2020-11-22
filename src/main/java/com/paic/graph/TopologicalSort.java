package com.paic.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 拓扑排序指的是讲一个有向无环图（DAG）进行排序后，形成一个有序的线性序列。
 *
 * 1. 将没有依赖的顶点放到Q队列
 * 2. 如果Q队列不为空，循环一下操作
 * 3. 取出Q队列中的第一个值n(并删除),将n放入T队列
 * 3.1 如果n的邻接点不为空，循环一下操作
 * 3.2	删除邻接点的边n-m，如果顶点m
 * 3.2.1 如果顶点m没有依赖，将m放入到Q队列
 */
public class TopologicalSort {

    private Queue<Integer> queue;

    public int sort(ListDG listDG){
        ListDG.VNode[] vNodes = listDG.getVNodes();

        int index = 0;
        queue = new LinkedList<Integer>();
        char[] res = new char[vNodes.length];
        int[] ins = new int[vNodes.length];

        // 初始化所有顶点的入度
        for (int i = 0; i < vNodes.length; i++) {
            ListDG.ENode edges = vNodes[i].firstEdge;
            while(edges != null){
                ins[edges.index]++;
                edges = edges.next;
            }
        }

        //将入度为0的顶点放入到队列中
        for (int i = 0; i < ins.length; i++) {
            if(ins[i] == 0)
                queue.add(i);
        }

        while(!queue.isEmpty()){
            int v = queue.remove();
            res[index++] = vNodes[v].data;

            // 获取到所有的边
            ListDG.ENode edges = vNodes[v].firstEdge;
            while(edges != null){
                // 这里入度-1，就当做删除边操作了
                ins[edges.index]--;

                if(ins[edges.index] == 0)
                    queue.add(edges.index);

                edges = edges.next;
            }
        }

        if(index != vNodes.length){
            System.out.println("图有环");
            return 1;
        }

        // 遍历T队列
        System.out.print("图的拓扑排序结果为:");
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + "\t");
        }

        return 0;

    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'G'},
                {'B', 'A'},
                {'B', 'D'},
                {'C', 'F'},
                {'C', 'G'},
                {'D', 'E'},
                {'D', 'F'}
        };
        ListDG pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new ListDG();
        // 采用已有的"图"
        pG = new ListDG(vexs, edges);

        pG.print();   // 打印图
        //pG.DFS();     // 深度优先遍历
        //pG.BFS();     // 广度优先遍历
        new TopologicalSort().sort(pG);
    }
}
