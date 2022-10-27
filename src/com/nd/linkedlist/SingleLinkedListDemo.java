package com.nd.linkedlist;

import java.util.Stack;

/**
 * (业务实现)
 *  单链表
 * @author NANDI_GUO
 * @date 2022/10/24 17:30
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "松江","及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用","智多星");
        HeroNode hero4 = new HeroNode(4, "林荣","豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        HeroNode heroNode = new HeroNode(2, "小路", "笑起来~~");
        singleLinkedList.update(heroNode);
        System.out.println();
        singleLinkedList.list();

        System.out.println();
        singleLinkedList.deleteNode(heroNode);
        singleLinkedList.list();

        //求有效个数
        int length = singleLinkedList.getLength(singleLinkedList.getHead());
        System.out.println("有效的个数是: " + length);

        //获取倒数第k个
        int index = 1;
        HeroNode lastIndexNode = singleLinkedList.getLastIndexNode(singleLinkedList.getHead(), index);
        System.out.printf("倒数第%d个节点是%s",index, lastIndexNode);

        System.out.println();
        System.out.println("链表反转");
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("逆序打印");
        singleLinkedList.reversePrint(singleLinkedList.getHead());

        //合并链表
        System.out.println("合并链表");
        SingleLinkedList list1 = new SingleLinkedList();
        list1.add(new HeroNode(1,"a","a"));
        list1.add(new HeroNode(3, "b","b"));
        list1.add(new HeroNode(7, "c","c"));
        SingleLinkedList list2 = new SingleLinkedList();
        list2.add(new HeroNode(2,"d","d"));
        list2.add(new HeroNode(4, "e","e"));
        list2.add(new HeroNode(6, "f","f"));

        HeroNode node = SingleLinkedList.combineList(list1.getHead(), list2.getHead());
        SingleLinkedList.list(node);

    }
}

class SingleLinkedList{
    //先初始化头节点, 不要动, 不存数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //找到当前链表的最后节点
    //将最后的节点的next指向新节点
    public void add(HeroNode node){
        //head不能动，所以需要辅助遍历temp
        HeroNode temp = head;
        while(true){
            if (temp.next == null){
                break;
            }
             temp = temp.next;
        }
        //当前节点
        temp.next = node;
    }

    //按顺序添加节点
    public void addByOrder(HeroNode node){
        //头节点不能动，辅助指针
        HeroNode temp = head;
        boolean flag = false; //添加的编号是否存在
        while(true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > node.no){
                break;
            }else if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            System.out.println("准备添加的已经存在:\t" + node.no);
        }else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    //修改节点，根据编号来修改
    //根据Heronode的no来改
    public void update(HeroNode node){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找节点
        HeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if (temp == null){
                break;
            }
            if (temp.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = node.name;
            temp.nickname = node.nickname;
        } else{
            System.out.printf("未找到no为 %d 的节点，不能修改\n", node.no);
        }

    }

    public void deleteNode(HeroNode node){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;

        while(true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到要删除的: %d \n",node.no);
        }

    }

    //获取单链表的有效节点的个数，带头就去头
    public int getLength(HeroNode head) {
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    //1. 便携一个方法接受head， 同时接受一个index
    //2. index表倒数第几个节点
    //3. 先从头到尾遍历整个链表，得到总长度
    //4. 得到size后，遍历size - index个就行
    public HeroNode getLastIndexNode(HeroNode head, int index){
        if (head.next == null){
            System.out.println("链表为空");
            return null;
        }
        //获取链表个数
        int size = getLength(head);

        //获取节点
        if (index <= 0 || index > size){
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index ; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //反转单链表
    public void reverseList(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义辅助
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点的下一个节点
        HeroNode reversedHead = new HeroNode(0, "","");
        //从头到尾遍历原链表。每遍历一个就去出，并放在reverseHead的最前端
        while(cur != null){
            next = cur.next; //先保存当前节点的下一个节点，后面需要使用
            cur.next = reversedHead.next; //将cur的下一个节点指向新链表的最前端
            reversedHead.next = cur; //将cur连到新的链表上
            cur = next; //cur后移
        }
        head.next = reversedHead.next;

    }

    //逆序打印链表 栈
    public void reversePrint(HeroNode node){
        if(node.next == null){
            return;
        }
        //创建一个栈
        Stack<HeroNode> stack = new Stack();
        HeroNode cur = head.next;
        //压栈
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序链表
    public static HeroNode combineList(HeroNode l1, HeroNode l2){
        //创建一个新的头部
        HeroNode newHead = new HeroNode(0, "","");
        //头部不能动，借助temp
        HeroNode temp = newHead;
        l1 = l1.next;
        l2 = l2.next;

        while(l1 != null && l2 != null){
            if (l1.no < l2.no){ //如果链表1的元素比链表2的元素小
                temp.next = l1; //1加在temp后，也就是
                l1 = l1.next;   //l1往后+位置
            }else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if(l1 != null){
            temp.next = l1;
        }
        if(l2 != null){
            temp.next = l2;
        }

        return newHead;
    }

    //显示链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //显示链表
    public static void list(HeroNode node){
        if (node.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = node.next;
        while(true){
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname){
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
