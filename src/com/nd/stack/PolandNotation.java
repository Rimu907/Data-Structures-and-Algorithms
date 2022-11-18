package com.nd.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/2 20:47
 */
public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> strings = infixExpression(expression);
        System.out.println(strings);
        List<String> parseSuffixExpression = parseSuffixExpression(strings);
        System.out.println(parseSuffixExpression);
    }
    public static int calculate(List<String> ls) {
        // ������ջ, ֻ��Ҫһ��ջ����
        Stack<String> stack = new Stack<String>();
        // ���� ls
        for (String item : ls) {
            // ����ʹ���������ʽ��ȡ����
            if (item.matches("\\d+")) { // ƥ����Ƕ�λ��
                // ��ջ
                stack.push(item);
            } else {
                // pop���������������㣬 ����ջ
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("���������");
                }
                //��res ��ջ
                stack.push("" + res);
            }

        }
        //�������stack�е�������������
        return Integer.parseInt(stack.pop());
    }
    public static List<String> parseSuffixExpression(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();
        //因为s2这个栈，整个转换过程中，没有pop操作，并且后面还需要逆序
        //所以直接使用ArrayList
        ArrayList<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item: ls){
            //如果是一个数，入s2
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                //右括号，循环弹出s1直到左括号
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//消除左括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级， s1栈顶弹出并压入s2，循环比较
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item入栈
                s1.push(item);
            }
        }

        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }


    public static List<String> infixExpression(String expression){
        List<String> infixList = new ArrayList<>();
        int i = 0;
        String s;
        char c;
        do {
            if (!Character.isLetterOrDigit(c = expression.charAt(i))){
                infixList.add("" + c);
                i++;
            } else {
              s = "";
              while (i < expression.length() && Character.isDigit(c = expression.charAt(i))){
                  s += c;
                  i++;
              }
              infixList.add(s);
            }
        }while(i<expression.length());
        return infixList;
    }
}

//编写一个类， 返回运算符优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法获取优先级数字
    public static int getValue(String oper){
        int result = 0;
        switch (oper){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("运算符不存在" + oper);
                break;
        }
        return result;
    }
}