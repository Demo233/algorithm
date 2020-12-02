package com.paic.linkedlist;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author zyh
 * @Description: 单链表实现LRU
 * 当链表满时，按照最近最少使用策略移除链表中的数据。
 * 例如: max capacity 为2,链表当前样子[1->2]，插入数据n以后的样子[n->1]，插入1后的样子[1->n]
 * @date 2020年12月02日22:52:12 下午
 */
public class LRULinkedList<T> {

    // 容量
    private int CAPACITY = 0;

    // 默认容量
    private int DEFAULT_CAPACITY = 5;

    // 设置守卫
    private SNode head;

    // 链表节点
    class SNode<T> {
        // 数据
        T data;
        // 指向下一个节点的指针
        SNode next;

        SNode(){
            this.data = null;
            this.next = null;
        }
        SNode(T data, SNode next){
            this.data = data;
            this.next = next;
        }
    }

    LRULinkedList(){
        this.head = new SNode();
    }

    public static void main(String[] args) throws IOException {
        LRULinkedList list = new LRULinkedList();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int read1 = scanner.nextInt();
            list.add(read1);
            list.printAll();
        }
    }

    // 添加
    public void add(T data) {

        SNode preNode = findPreNode(data);

        if(preNode != null){
            // 删除前一个节点的下一个节点，也就是当前节点有点绕。。
            delete(preNode);
            // 插入到头
            insertBegin(data);
        }else{
            if(CAPACITY > DEFAULT_CAPACITY){
                // 删除最后节点
                deleteEnd();
            }
            insertBegin(data);
        }

    }

    // 删除队尾
    public void deleteEnd() {
        SNode ptr = head;

        // 判断队列是否为空
        if(ptr.next == null){
            return;
        }

        // 找到倒数第二个节点
        while(ptr.next.next != null){
            ptr = ptr.next;
        }

        // 删除最后节点
        SNode tmp = ptr.next;
        ptr.next = null;
        tmp = null;
        CAPACITY--;
    }

    public void insertBegin(T data) {
        // 插入对头
        SNode next = head.next;
        head.next = new SNode(data,next);
        CAPACITY++;
    }

    // 删除当前节点
    public void delete(SNode preNode) {
        SNode tmp = preNode.next;
        preNode.next = tmp.next;
        tmp = null;
        CAPACITY--;

    }

    // 寻找data对应的前一个节点
    public SNode findPreNode(T data) {
        SNode node = head;
        while(node.next != null){
            if(data.equals(node.next.data)){
                return node;
            }
            node = node.next;
        }

        return null;
    }

    // 打印所有链表的节点
    public void printAll(){
        SNode node = head;
        while(node.next != null){
            System.out.print(node.next.data + "\t");
            node = node.next;
        }
        System.out.println();
    }
}
