package com.nd.stack;

import java.util.Scanner;

/**
 * 数组模拟栈(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/28 17:55
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("s: 显示栈");
            System.out.println("e: 退出");
            System.out.println("push: 添加数据");
            System.out.println("pop: 出栈");
            System.out.println("请输入...");
            key = scanner.next();
            switch (key){
                case "s":
                    stack.list();
                    System.out.println();
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个: ");
                    int v = scanner.nextInt();
                    stack.push(v);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是：%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default: break;
            }
        }
        System.out.println("程序退出");

    }
}

class ArrayStack{
    private int maxsize; //栈大小
    private int[] stack; //数组，模拟栈，存数据
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack(int maxsize){
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }

    //栈满
    public boolean isFull(){
        return top == maxsize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈 从栈顶开始显示
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
