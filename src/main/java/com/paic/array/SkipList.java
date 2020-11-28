package com.paic.array;

/**
 * @author zyh
 * @Description: 跳表
 * @date 2020/11/279:11 下午
 */
public class SkipList {

    // 头结点
    private Node head = new Node();

    // 当前状态的最大层
    private int maxLevel = 1;

    private class Node{

        // 数据
        private int data = -1;
        // 层
        private int level = 0;
        Node(){}
        Node(int level){
            this.level = level;
            forwards = new Node[level];
        }

        // 链条
        private Node[] forwards = new Node[MAX_LEVEL];
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(level);
            builder.append(" }");

            return builder.toString();
        }

    }

    /**
     *  最原始的插入方法
     * @param v
     */
    public void insert(int v){
        int level = head.forwards[0] == null ? 1 : randLevel();
        if(level > maxLevel){
            level = ++maxLevel;
        }
        // 初始化节点
        Node newNode = new Node();
        newNode.level = level;
        newNode.data = v;
        newNode.forwards = new Node[level];

        Node[] update = new Node[level];
        for (int i = 0; i < update.length; i++) {
            update[i] = head;
        }

        // 循环查找每一层中小于插入的值 值
        Node p = head;
        for(int i = level - 1; i >= 0; i--){
            while(p.forwards[i] != null && p.forwards[i].data < v){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // 循环插入到链条中
        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        if(maxLevel < level) maxLevel = level;
    }

    /**
     * 优化后的插入方法
     */
    public void insert2(int v){

        // 获取插入节点的层
        int level = head.forwards[0] == null? 1 : randLevel();

        if(level > maxLevel){
            level = ++maxLevel;
        }

        // 初始化插入节点
        Node newNode = new Node(level);
        newNode.data = v;
        // 寻找最近的那个比插入值小的值
        Node p = head;
        for(int i = level - 1; i >= 0; i --){
            while(p.forwards[i] != null && p.forwards[i].data < v){
                p = p.forwards[i];
            }

            if(p.forwards[i] == null){
                p.forwards[i] = newNode;
            }else{
                Node next = p.forwards[i];
                newNode.forwards[i] = next;
                p.forwards[i] = newNode;
            }
        }

    }


    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;
    // 概率函数
    public int randLevel(){
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }


    // 打印跳表,只需要打印，非索引层的数据即可，就是head.forward[0]
    public void printAll(){

        Node p = head;
        while(p.forwards[0] != null){
            System.out.println(p.forwards[0]);
            p = p.forwards[0];
        }
        System.out.println();

    }

    //查询给定的值
    private Node find(int value) {
        // 从最高层开始查
        Node p = head;
        for (int i = maxLevel -1 ; i >=0 ; i--){
            while(p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
        }

        if(p.forwards[0] != null && p.forwards[0].data == value){
            return p.forwards[0];
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert2(1);
        skipList.insert2(6);
        skipList.insert2(8);
        skipList.insert2(7);
        skipList.insert2(10);
        skipList.insert2(9);
        skipList.insert2(15);
        skipList.insert2(11);

        skipList.printAll();

        // 查找节点
        Node node = skipList.find(15);
        System.out.println(node);
    }
}
