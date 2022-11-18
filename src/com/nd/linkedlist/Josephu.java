package com.nd.linkedlist;

/**
 * 环形链表约瑟夫问题(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/28 16:29
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addNode(5);
        list.list();
        System.out.println();
        //测试节点出圈
        list.countNodes(1,2,5);
    }
}

//创建一个环形单向链表
class CircleSingleLinkedList{
    //创建一个first节点, 当前没编号
    private Node first = new Node(-1);
    //添加小孩节点, 构建成一个环形链表
    public void addNode(int nums){
        //nums 做一个数据校验
        if (nums < 1){
            System.out.println("nums值错误");
            return;
        }
        Node curNode = null;//辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums ; i++) {
            //根据编号创建节点
            Node node = new Node(i);
            //第一个节点特殊考虑
            if (node.getNo() == 1){
                first = node;
                first.setNext(first); //构成环状
                curNode = first; //让curNode指向第一节点
            }else{
                curNode.setNext(node);
                node.setNext(first);
                curNode = node;
            }
        }
    }

    //遍历当前的环形链表
    public void list(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        Node cur = first;
        while (cur.getNext() != first){
            System.out.println("节点编号 = " + cur.getNo());
            cur = cur.getNext();
        }
        System.out.println("节点编号 = " + cur.getNo());
    }

    //根据用户的输入，计算节点出圈顺序
    /**
     * @description:
     * @param startNo 从第几个开始数
     * @param countNum 数几下
     * @param nums 表示有多少个在圈中
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/10/28 17:08
     */
    public void countNodes(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        Node helper = first;
        //将helper指向最后一个节点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        //报数前，first移动到起始位置， helper也移动相应次数
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时, 让first和helper指针同时移动m-1次，然后出圈
        //这是一个循环操作
        while(helper != first){ //说明圈中只有一人
            for (int i = 0; i < countNum - 1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点, 就是要出圈的节点
            System.out.printf("节点%d出圈\n", first.getNo());
            System.out.println();
            //这时first指向的节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后圈中的节点 %d", first.getNo());
    }
}

//创建一个Node类表示节点
class Node{
    private int no;
    private Node next;
    public Node(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}