package com.nd.linkedlist;

import java.util.Stack;

/**
 * 演示使用栈的基本使用(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/26 14:32
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //压栈
        stack.add("a");
        stack.add("b");
        stack.add("c");

        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
