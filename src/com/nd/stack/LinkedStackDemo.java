package com.nd.stack;

/**
 * 链表栈(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/28 18:26
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.showStack();
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        linkedStack.push(node);
        linkedStack.push(node2);
        linkedStack.push(node3);
        linkedStack.showStack();
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
    }
}
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

class LinkedStack{

    private Node head = new Node(0);
    public Node getHead() {
        return head;
    }

    //入栈
    public void push(Node node){
        if (node == null) {
            return;
        }
        Node cur = head;
        if (cur.getNext() == null){
            cur.setNext(node);
            System.out.println("压入node：" + node.getNo());
        }else{
            node.setNext(cur.getNext());
            cur.setNext(node);
            System.out.println("压入node：" + node.getNo());
        }
    }

    //出栈
    public void pop(){
        if (head.getNext() == null){
            System.out.println("栈空");
            return;
        }
        Node cur = head;
        System.out.println("弹出:" + cur.getNext().getNo());
        cur.setNext(cur.getNext().getNext());
    }

    //展示栈
    public void showStack(){
        if (head.getNext() == null){
            System.out.println("栈空");
            return;
        }
        Node cur = head;
        while (cur.getNext() != null) {
            System.out.printf("%d \t", cur.getNext().getNo());
            cur = cur.getNext();
        }
        System.out.println();
    }
}
