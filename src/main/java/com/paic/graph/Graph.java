package com.paic.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 使用邻接矩阵实现无向图，并支持DFS（depth first search） 和 BFS (Breadth first search)
 */
public class Graph<T> {
    // 顶点
    private ArrayList<T> vertexs;
    // 边
    private int[][] edge;
    // 已访问
    private boolean[] visited;

    Graph(int n) {
        vertexs = new ArrayList<T>();
        edge = new int[n][n];
        visited = new boolean[n];
    }

    public void insertVertex(T vertex) {
        vertexs.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edge[v1][v2] = weight;
        edge[v2][v1] = weight;
    }

    public void show() {
        System.out.println(vertexs);
        for (int i = 0; i < edge.length; i++) {
            for (int i1 : edge[i]) {
                System.out.print(i1 + "\t");
            }
            System.out.println();
        }
    }


    public void dfs(int i) {
        System.out.print(vertexs.get(i).toString() + "=>");
        visited[i] = true;

        //获取i的邻接点
        int w = firstNeighbor(i);
        while (w != -1) {
            if (!visited[w]) {
                dfs(w);
            }
            w = nextNeighbor(i, w);
        }

    }


    // 深度优先遍历算法
    public void dfs() {

        for (int i = 0; i < vertexs.size(); i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    private int nextNeighbor(int i1, int i2) {

        for (int j = i2 + 1; j < edge[i1].length; j++) {
            if (edge[i1][j] == 1) {
                return j;
            }
        }
        return -1;
    }


    private int firstNeighbor(int index) {
        for (int i = 0; i < edge[index].length; i++) {
            if (edge[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    // 广度优先遍历
    public void bfs(int i) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int w; // 第n个邻接点
        int u; // 头结点
        System.out.print(vertexs.get(i) + "->");
        visited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();

            w = firstNeighbor(i);
            while (w != -1) {
                if (!visited[w]) {
                    queue.addLast(w);
                    System.out.print(vertexs.get(w) + "->");
                    visited[w] = true;
                }
                w = nextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexs.size(); i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }
    }

}
