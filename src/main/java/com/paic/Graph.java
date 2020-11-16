package com.paic;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 使用邻接矩阵实现无向图，并支持DFS（depth first search） 和 BFS (Breadth first search)
 */
public class Graph {

    // 邻接矩阵，存储边的连通信息
    private int[][] edges;
    // 顶点信息
    private ArrayList<String> vertexs;
    // 边的数量
    private int edgeSize;

    // 顶点是否访问过
    private boolean[] visited;

    Graph(int n){
        edges = new int[n][n];
        vertexs = new ArrayList<String>(n);
        edgeSize = 0;
        visited = new boolean[n];
    }

    /**
     * 获取邻接点,在遍历图时，需要拿到第一个邻接点
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexs.size(); i++) {
            if(edges[index][i] > 0) return i;
        }
        return -1;
    }

    //获取下一个邻接点
    public int getNextNeighbor(int v1, int v2){
        // 跳过第一个邻接点，直接从第二个开始
        for (int i = v2 + 1; i < vertexs.size(); i++) {
            if(edges[v1][i] > 0) return i;
        }
        return -1;
    }

    // 获取顶点个数
    public int getVertexSize(){
        return vertexs.size();
    }
    // 获取边个数
    public int getEdgeSize(){
        return edgeSize;
    }
    // 获取权重
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }
    // 插入顶点
    public void insertVertex(String vertex){
        vertexs.add(vertex);
    }
    // 插入边
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeSize ++;
    }
    // 打印图
    public void show(){
        for (int i = 0; i < edges.length; i++) {
            for (int i1 = 0; i1 < edges[i].length; i1++) {
                System.out.print(edges[i][i1] + "\t");
            }
            System.out.println();

        }
    }

    public String getValueByIndex(int index){
        return vertexs.get(index);
    }
    // 深度优先遍历算法
    public void dfs(boolean[] visited, int index){
        visited[index] = true;
        System.out.print(getValueByIndex(index) + "=>");
        int w = getFirstNeighbor(index);
        while( w != -1){
            if(!visited[w]){
                dfs(visited, w);
            }
            w = getNextNeighbor(index, w);
        }
    }

    public void dfs(){
        for (int i = 0; i < vertexs.size(); i++) {
            if(!visited[i]){
                dfs(visited, i);
            }
        }
    }

    // 广度优先遍历算法,利用队列的特点，将除当前节点的其他节点存入其中，下次以队列内的节点为依据，一次寻找邻接点
    public void bfs(boolean[] visited, int index){

        int u; // 头
        int w; // 第n个邻接点

        visited[index] = true;
        System.out.print(getValueByIndex(index) + "->");
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(index);

        while(!queue.isEmpty()){
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while(w != -1){
                if(!visited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    visited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs(){
        for (int i = 0; i < vertexs.size(); i++) {
            if(!visited[i]){
                bfs(visited, i);
            }
        }
    }


}
