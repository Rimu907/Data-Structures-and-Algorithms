package com.nd.queue;
import java.util.Scanner;

/**
 * (业务实现)
 * 环形队列
 * @author NANDI_GUO
 * @date 2022/10/23 18:52
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(4); //队列最大为3， 设置4，有一个为空
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop) {
            System.out.println("s: 显示队列");
            System.out.println("e: 退出程序");
            System.out.println("a: 添加数据");
            System.out.println("g: 取出数据");
            System.out.println("h: 查看头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    System.out.println();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    try {
                        System.out.println("输入一个数:");
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int i = queue.outQuene();
                        System.out.println("取出的数据是: " + i);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int i = queue.headQuene();
                        System.out.println("当前头数据是: " + i);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }

    }
}

//使用数组模拟队列
class CircleArray {
    private int maxsize; //数组最大容量
    private int front; //队列的头 第一个元素 arr[front]
    private int rear; //队列尾部
    private int[] arr; //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxsize){
        maxsize = arrMaxsize;
        arr = new int[maxsize];
        front = 0; //指向队列头部，是指向队列第一个数据
        rear = 0; //指向队列尾部，队列最后一个数据
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxsize == front;
    }

    //判断队列是否空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxsize;
    }

    //数据出队列
    public int outQuene(){
        if (isEmpty()){
            throw new RuntimeException("队列空了");
        }

        //front指向队列的第一个元素
        //1.先把front对应的值保存到临时变量
        //2.再把front后移

        int value = arr[front];
        front = (front + 1) % maxsize;
        return value;
    }

    //显示所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("空队列，无数据");
            return;
        }

        //从front开始遍历， 遍历多少个元素
        for (int i = front; i <  front + size(); i++) {
            System.out.printf(arr[i % maxsize] + "\t");
        }
    }

    //显示头数据
    public int headQuene(){
        if (isEmpty()){
            throw new RuntimeException("队列空了");
        }
        return arr[front];
    }

    //求出当前队列有效数据
    public int size(){
        // rear = 2
        // front = 0
        // max = 3
        return (rear + maxsize - front) % maxsize;
    }
}
