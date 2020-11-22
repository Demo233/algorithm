package com.paic.graph;

import java.util.ArrayList;

/**
 * 使用邻接矩阵实现有向图
 */
public class MatrixDG<T> {

    // 顶点
    private ArrayList<T> vertexs;
    // 存储边
    private int[][] edge;

    MatrixDG(int n){
        vertexs = new ArrayList<T>();
        edge = new int[n][n];
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3,4};
        MatrixDG matrixDG = new MatrixDG<Integer>(arr.length);
        // 插入顶点
        for (int i = 0; i < arr.length; i++)
            matrixDG.insertVertex(arr[i]);

        // 初始化边
        matrixDG.insertEdge(0, 1, 1);
        matrixDG.insertEdge(1, 2, 1);
        matrixDG.insertEdge(2, 0, 1);
        matrixDG.insertEdge(2, 1, 1);
        matrixDG.insertEdge(3, 1, 1);
        matrixDG.insertEdge(3, 2, 1);

        matrixDG.show();

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

    // 有向图和无向图的区别，仅仅是在插入边时做改动
    private void insertEdge(int v1, int v2, int weight) {
        edge[v1][v2] = weight;
    }


    private void insertVertex(T i) {
        vertexs.add(i);
    }

}
