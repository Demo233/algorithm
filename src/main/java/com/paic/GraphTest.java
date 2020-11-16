package com.paic;

import org.junit.Test;

public class GraphTest {

    private static Graph graph;

    {
        graph = insertTest2();
    }

    /**
     *   A  B   C   D   E
     * A 0	1	1	0	0
     * B 1	0	1	1	1
     * C 1	1	0	0	0
     * D 0	1	0	0	0
     * E 0	1	0	0	0
     * @return
     */
    public Graph insertTest() {

        String[] vertexArr = new String[]{"A","B","C","D","E"};
        Graph graph = new Graph(5);
        for (int i = 0; i < vertexArr.length; i++) {
            graph.insertVertex(vertexArr[i]);
        }

        graph.insertEdge(0, 1, 1);//A-B
        graph.insertEdge(0, 2, 1);//A-C
        graph.insertEdge(1, 2, 1);//B-C
        graph.insertEdge(1, 3, 1);//B-C
        graph.insertEdge(1, 4, 1);//B-C

        graph.show();
        return graph;
    }

    /**
     * 深度优先遍历顺序为: 1->2->4->8->5->3->6->7
     * 广度优先算法的遍历顺序为：1->2->3->4->5->6->7->8
     *
     * @return
     */
    public Graph insertTest2() {

        String[] vertexArr = new String[]{"1","2","3","4","5","6","7","8",};
        Graph graph = new Graph(vertexArr.length);
        for (int i = 0; i < vertexArr.length; i++) {
            graph.insertVertex(vertexArr[i]);
        }

        graph.insertEdge(0, 1, 1);//1-2
        graph.insertEdge(0, 2, 1);//1-3
        graph.insertEdge(1, 3, 1);//2-4
        graph.insertEdge(1, 4, 1);//2-5
        graph.insertEdge(3, 7, 1);//4-8
        graph.insertEdge(4, 7, 1);//5-8
        graph.insertEdge(2, 5, 1);//3-6
        graph.insertEdge(2, 6, 1);//3-7
        graph.insertEdge(5, 6, 1);//6-7


        graph.show();
        return graph;
    }


    @Test
    public void dfsTest() {
        graph.dfs();
    }

    @Test
    public void bfsTest() {
        graph.bfs();
    }
}
