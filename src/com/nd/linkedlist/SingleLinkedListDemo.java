package com.nd.linkedlist;

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


    }
}

class SingleLinkedList{
    //先初始化头节点, 不要动, 不存数据
    private HeroNode head = new HeroNode(0,"","");

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
