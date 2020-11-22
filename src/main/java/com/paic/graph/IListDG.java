package com.paic.graph;

/**
 * 使用邻接表创建有向图
 */
public class IListDG {

    private VNode[] vNodes;

    // 边节点
    private class ENode{
        // 边的索引
        int index;
        // 边的下一条边
        ENode next;
    }


    // 顶点
    private class VNode{
        // 顶点存储的值
        char data;
        // 顶底相连的第一条边
        ENode firstEdge;

        // 逆邻接表
        ENode firstIEdge;
    }

    /**
     *
     * @param vertexs 所有的顶点
     * @param edges 所有的边
     */
    public IListDG(char[] vertexs, char[][] edges){

        int v1 = vertexs.length;
        vNodes = new VNode[v1];

        // 创建顶点
        for (int i = 0; i < vertexs.length; i++) {
            VNode vNode = new VNode();
            vNode.data = vertexs[i];
            vNode.firstEdge = null;
            vNodes[i] = vNode;
        }

        // 创建边
        for (int i = 0; i < edges.length; i++) {

            // 获取两条边对应的下标
            int p1 = getPos(edges[i][0]);
            int p2 = getPos(edges[i][1]);

            ENode node1 = new ENode();
            node1.index = p2;
            if(vNodes[p1].firstEdge == null)
                vNodes[p1].firstEdge = node1;
            else
                linkLast(vNodes[p1].firstEdge, node1);

            ENode node2 = new ENode();
            node2.index = p1;
            if(vNodes[p2].firstIEdge == null)
                vNodes[p2].firstIEdge = node2;
            else
                linkLast(vNodes[p2].firstIEdge, node2);
        }


    }


    // 将node链接到list最后
    private void linkLast(ENode list, ENode node) {

        if(hasNode(list, node))
            return;

        ENode p = list;
        while(p.next != null)
            p = p.next;

        p.next = node;

    }

    // 单链表的查询功能
    private boolean hasNode(ENode list, ENode node) {
        if(list == null) {
            return false;
        }

        ENode n = list;
        if(n.index == node.index){
            return true;
        }else{
            return hasNode(n.next, node);
        }
    }

    // 根据data查找数组中的下标
    private int getPos(char data) {
        for (int i = 0; i < vNodes.length; i++) {
            if(vNodes[i].data == data){
                return i;
            }
        }
        return -1;
    }

    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < vNodes.length; i++) {
            System.out.printf("%d(%c): ", i, vNodes[i].data);
            ENode node = vNodes[i].firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.index, vNodes[node.index].data);
                node = node.next;
            }
            System.out.printf("\n");
        }

        System.out.printf("Inverse List Graph:\n");
        for (int i = 0; i < vNodes.length; i++) {
            System.out.printf("%d(%c): ", i, vNodes[i].data);
            ENode node = vNodes[i].firstIEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.index, vNodes[node.index].data);
                node = node.next;
            }
            System.out.printf("\n");
        }


    }

    // 已知数据创建图
    public static void main(String[] args) {

        char[] vexs = {'1', '2', '3', '4', '5'};
        char[][] edges = new char[][]{
                {'1', '2'},
                {'2', '3'},
                {'2', '5'},
                {'2', '4'},
                {'4', '1'},
                {'4', '2'},
                {'5', '4'},
                {'5', '3'}
        };
        IListDG pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new ListDG();
        // 采用已有的"图"
        pG = new IListDG(vexs, edges);

        pG.print();

    }

}
