package com.nd.stack;

/**
 * 栈实现计算器(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/10/28 19:07
 */
public class Calculator {
    public static void main(String[] args) {
        String exp = "7+2*6-4";

        //创建数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //创建扫描索引
        int index = 0;
        int num1, num2, oper, res = 0;
        char ch = ' ';
        String keepNum = "";
        //开始扫描
        while(true){
            //依次得到expression 的每一个字符
            ch = exp.substring(index,index+1).charAt(0);
            //判断ch是什么
            if (operStack.isOper(ch)){ //是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()){
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果入栈
                        numStack.push(res);
                        //当前操作符入栈
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else { //是数
                keepNum += ch;

                if (index == exp.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if (operStack.isOper(exp.substring(index+1,index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index + 1并判断是否扫描到exp最后
            index++;
            if (index >= exp.length()){
                break;
            }
        }

        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有结果
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("%s = %d", exp, res2);
    }
}

//需要扩展功能
class ArrayStack2{
    private int maxsize; //栈大小
    private int[] stack; //数组，模拟栈，存数据
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack2(int maxsize){
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }

    //返回栈顶值
    public int peek() {
        return stack[top];
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

    //返回运算符的优先级
    public int priority(int oper) {
        if(oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
