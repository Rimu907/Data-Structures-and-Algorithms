package com.nd.queue;

import java.util.Scanner;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/23 15:28
 */
public class Queue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
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
class ArrayQueue {
    private int maxsize; //数组最大容量
    private int front; //队列的头
    private int rear; //队列尾部
    private int[] arr; //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxsize){
        maxsize = arrMaxsize;
        arr = new int[maxsize];
        front = -1; //指向队列头部，是指向队列头的前一个位置
        rear = -1; //指向队列尾部，队列最后一个数据
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxsize-1;
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
        rear++; //rear后移
        arr[rear] = n;
    }

    //数据出队列
    public int outQuene(){
        if (isEmpty()){
            throw new RuntimeException("队列空了");
        }
        front++;
        return arr[front];
    }

    //显示所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("空队列，无数据");
            return;
        }
        for (int q: arr){
            System.out.printf(q + "\t");
        }
    }

    //显示头数据
    public int headQuene(){
        if (isEmpty()){
            throw new RuntimeException("队列空了");
        }
        return arr[front+1];
    }
}
