package com.nd.hashtable;

import java.util.Scanner;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/21 18:19
 */
public class HashTableDemo {
    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }

}

//创建哈希表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        //初始化数组大小
        this.empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        //根据员工的id得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //编写一个散列函数，使用取模发
    public int hashFun(int id){
        return id % size;
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.println("在第"+ empLinkedListNo +"条链表找到该雇员id: " + id);
        } else {
            System.out.println("哈希表中无该雇员");
        }
    }

}

//创建Emplist表示链表
class EmpLinkedList{
    private Emp head;

    //添加雇员
    //1. 假设添加雇员时，id为自增的，即id的分配总是从小到大
    //将该雇员直接加入到本链表的最后一个即可
    public void add(Emp emp){
        if (head == null){
            head = emp;
            return;
        }

        Emp curEmp = head;
        while (curEmp.next!=null){
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null){
            System.out.println("第"+no+"链表为空");
            return;
        }
        System.out.print("第"+no+"链表的信息为：");
        Emp curEmp = head;
        while(true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if(curEmp.next == null) {//说明curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id) {
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp temp = head;
        while(temp.next != null){
            if (temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        if (temp.id == id){
            return temp;
        }
        return null;
    }
}

