package com.nd.linkedlist;

/**
 * 双向链表(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/27 16:46
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        HeroNode2 node1 = new HeroNode2(1, "abc", "abc");
        HeroNode2 node2 = new HeroNode2(3, "def", "def");
        HeroNode2 node3 = new HeroNode2(6, "qwe", "qwe");
        HeroNode2 node4 = new HeroNode2(2, "rty", "rty");
        HeroNode2 node5 = new HeroNode2(5, "rty", "rty");
        HeroNode2 node7 = new HeroNode2(7, "rty", "rty");
        list.addNodeByOrder(node1);
        list.addNodeByOrder(node2);
        list.addNodeByOrder(node3);
        list.addNodeByOrder(node4);
        list.addNodeByOrder(node5);
        list.addNodeByOrder(node7);
        list.list();
//        list.delete(node3);
//        list.list();
    }
}

class DoubleLinkedList{
    //创建一个新的头部
    HeroNode2 head = new HeroNode2(0, "","");
    public HeroNode2 getHead() {
        return head;
    }

    //删除
    public void delete(HeroNode2 node){
        if (node == null || head.next == null){
            System.out.println("要删除的节点为空");
            return;
        }

        HeroNode2 cur = head.next;
        while(cur != null){
            if (cur.no == node.no){
                cur.pre.next = cur.next;
                cur.pre.next = cur.next != null ? cur.pre : null;
                return;
            }
            cur = cur.next;
        }
    }

    //修改
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找节点
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickname = node.nickname;
        } else {
            System.out.printf("未找到no为 %d 的节点，不能修改\n", node.no);
        }
    }

    //按顺序添加节点
    public void addNodeByOrder(HeroNode2 node){
        if (node == null){
            return;
        }
        HeroNode2 cur = head;
        while(cur.next != null) {
            if (cur.next.no > node.no){
                break;
            }else if (cur.next.no == node.no) {
                System.out.println(node.toString() + "已经存在");
                break;
            }
            cur = cur.next;
        }
        if (cur.next != null){
            node.next = cur.next;
            cur.next.pre = node;
        }
        cur.next = node;
        node.pre = cur;
    }

    //添加节点
    public void addNode(HeroNode2 node) {
        if (node == null){
            return;
        }
        HeroNode2 temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    //遍历双向
    public void list(){
        if (head.next == null){
            System.out.println("空链表");
            return;
        }

        HeroNode2 temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点, 默认为null
    public HeroNode2 pre;  //指向前一个节点

    public HeroNode2(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + "'}";
    }
}
